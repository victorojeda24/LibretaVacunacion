package pol.apprv;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import pol.apprv.hijo.HijoActividad;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private static final String CLASS_NAME = "MainActivity";
    private static final int REQ_CODE = 9001;

//    private LinearLayout photoSeccion;
//    private Button signSalida;
//    private SignInButton signIngreso;
    private TextView estadoTextView, emailTextView;
//    private ImageView photoPie;
    private ProgressDialog dialogo;
    private GoogleApiClient googleApiClient;

    String correoUsuario;

    //AQUI HAY QUE CAMBIAR LA IP Y EL PUERTO DONDE SE EJECUTA EL WEB SERVICE
    public static final String servidor="http://192.168.1.72:8084";
    //RECORDAR CAMBIAR LINEA DE ARRIBA Y EN DBHelper.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Vistas
        estadoTextView = (TextView)findViewById(R.id.estado);
        emailTextView = (TextView)findViewById(R.id.correo);

        //Botones
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.siguiente).setOnClickListener(this);
        findViewById(R.id.desconectar).setOnClickListener(this);

/*        photoSeccion = (LinearLayout)findViewById(R.id.photo_seccion);
        signSalida = (Button)findViewById(R.id.botonLogOut);
        signIngreso = (SignInButton)findViewById(R.id.botonLogin);
        nombre = (TextView)findViewById(R.id.nombre);
        email = (TextView)findViewById(R.id.email);
        photoPie = (ImageView)findViewById(R.id.photo_pie);
        signIngreso.setOnClickListener(this);
        signSalida.setOnClickListener(this);
        photoSeccion.setVisibility(View.GONE);*/

        GoogleSignInOptions signIngresoOpcion = new
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();


        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,signIngresoOpcion).build();

        //Personalizar el boton de Google
        //Dimensión para el boton de google
        SignInButton signInButton = (SignInButton)findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
    }

    @Override
    public void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr =
                Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(CLASS_NAME, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(CLASS_NAME, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            assert acct != null;
            estadoTextView.setText(getString(R.string.conectado_con, acct.getDisplayName()));
            emailTextView.setText(getString(R.string.email_es, acct.getEmail()));
            correoUsuario = String.valueOf(acct.getEmail());
            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    private void signIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);
    }

    private void signOut(){
        //Botón Siguiente, va a la actividad HijoActividad
        ObtenerHijoIDUsuario obtenerHijoUsuario = new ObtenerHijoIDUsuario();
        obtenerHijoUsuario.execute();
/*        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });*/
    }

    private void revocarAcceso() {
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        updateUI(false);
                    }
                }
        );
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(CLASS_NAME, "onConnectionFailed: " + connectionResult);
    }

    private void showProgressDialog() {
        if (dialogo == null) {
            dialogo = new ProgressDialog(this);
            dialogo.setMessage(getString(R.string.cargando));
            dialogo.setIndeterminate(true);
        }
        dialogo.show();
    }

    private void hideProgressDialog() {
        if (dialogo != null && dialogo.isShowing()) {
            dialogo.hide();
        }
    }

    private void updateUI(boolean isLogin){
        if (isLogin){
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.siguienteYdesconectar).setVisibility(View.VISIBLE);
            findViewById(R.id.correo).setVisibility(View.VISIBLE);
        }else {
            estadoTextView.setText(R.string.desconectado);
            emailTextView.setText(R.string.email_es);
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.correo).setVisibility(View.GONE);
            findViewById(R.id.siguienteYdesconectar).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.siguiente:
                signOut();
                break;
            case R.id.desconectar:
                revocarAcceso();
                break;
        }
    }

    /*private void handleResult(GoogleSignInResult result){
        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String correo = account.getEmail();

            String imagenURL = account.getPhotoUrl().toString();
            nombre.setText(name);
            email.setText(correo);
            Glide.with(this).load(imagenURL).into(photoPie);
            updateUI(true);
        }else {
            updateUI(false);
        }
    }*/

    private class ObtenerHijoIDUsuario extends AsyncTask <String,Integer,Boolean> {
        static final String linkServicio = servidor+"/ServicioRV/webresources/usuario/isuser";
        private String id_usuario;
        protected Boolean doInBackground(String... params) {
            JSONObject jsonParam = new JSONObject();
            boolean resultado = false;
            try {
                jsonParam.put("correo", correoUsuario);
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost del = new HttpPost(linkServicio);
                del.setHeader("Accept", "application/json");
                del.setHeader("Content-type", "application/json");
                StringEntity se = new StringEntity(jsonParam.toString());
                del.setEntity(se);
                // La repuesta del Http
                HttpResponse resp = httpClient.execute(del);
                String respStr = EntityUtils.toString(resp.getEntity());
                JSONObject obj = new JSONObject(respStr);
                if (obj != null) {
                    id_usuario = obj.getString("id");
                    resultado = true;
                }
                else {
                    resultado = false;
                }
            } catch (Exception ex) {
                Log.e("ServicioRest", "Error!", ex);
            }
            return resultado;
        }

        protected void onPostExecute(Boolean resultado) {
            if (resultado) {
                Intent intent = new Intent(MainActivity.this, HijoActividad.class);
                intent.putExtra("id_usuario", id_usuario);
                startActivity(intent);
            }else{
                Toast toast1 = Toast.makeText(getApplicationContext(),
                                                "Correo no registrado",
                                                Toast.LENGTH_SHORT);
                toast1.show();
            }
        }
    }
}
