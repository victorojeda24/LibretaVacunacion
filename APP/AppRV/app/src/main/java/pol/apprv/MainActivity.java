package pol.apprv;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.apache.http.client.HttpClient;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private LinearLayout photoSeccion;
    private Button signSalida;
    private SignInButton signIngreso;
    private TextView nombre, email;
    private ImageView photoPie;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;

    //AQUI HAY QUE CAMBIAR LA IP Y EL PUERTO DONDE SE EJECUTA EL WEB SERVICE
    public static final String servidor="http://192.168.1.72:8084";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photoSeccion = (LinearLayout)findViewById(R.id.photo_seccion);
        signSalida = (Button)findViewById(R.id.botonLogOut);
        signIngreso = (SignInButton)findViewById(R.id.botonLogin);
        nombre = (TextView)findViewById(R.id.nombre);
        email = (TextView)findViewById(R.id.email);
        photoPie = (ImageView)findViewById(R.id.photo_pie);
        signIngreso.setOnClickListener(this);
        signSalida.setOnClickListener(this);
        photoSeccion.setVisibility(View.GONE);
        GoogleSignInOptions signIngresoOpcion = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new  GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signIngresoOpcion).build();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.botonLogin:
                signIn();
                break;
            case R.id.botonLogOut:
                signOut();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);
    }

    private void signOut(){
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }

    private void handleResult(GoogleSignInResult result){
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
    }

    private void updateUI(boolean isLogin){
        if (isLogin){
            photoSeccion.setVisibility(View.VISIBLE);
            signIngreso.setVisibility(View.GONE);
        }else {
            photoSeccion.setVisibility(View.GONE);
            signIngreso.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }

    private class isUser extends AsyncTask <String,Integer,Boolean> {
        static final String linkServicio = servidor+"/ServicioRV/webresources/usuario/isuser";
        private String id_usuario;
        protected Boolean doInBackground(String... params) {
            JSONObject jsonParam = new JSONObject();
            boolean resultado = false;
            try {
                jsonParam.put("correo", email);
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost del = new HttpPost(linkService);
                del.setHeader("Accept", "application/json");
                del.setHeader("Content-type", "application/json");
                StringEntity se = new StringEntity(jsonParam.toString());
                del.setEntity(se);
                //
                HttpResponse resp = httpClient.execute(del);
                String respStr = EntityUtils.toString(resp.getEntity());
                JSONObject obj = new JSONObject(respStr);
                if (obj != null) {
                    id_usuario = obj.getString("id_usuario");
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
    }
}
