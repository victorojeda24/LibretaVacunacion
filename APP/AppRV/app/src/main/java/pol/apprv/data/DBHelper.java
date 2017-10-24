package pol.apprv.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import pol.apprv.data.HijoPlantilla.HijoEntry;

/**
 * Created by ari
 * Manejador de la base de datos
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "hijos.db";
    private ArrayList<Hijo> listaHijo = new ArrayList<>();
    private ArrayList<Vacuna> listaVacuna = new ArrayList<>();
    private static String id_usuario;

    //AQUI HAY QUE CAMBIAR LA IP Y EL PUERTO DONDE SE EJECUTA EL WEBSERVICE
    private String servidor = "http://192.168.1.72:8084";
    //RECORDAR CAMBIAR LINEA DE ARRIBA Y EN MainActivity.java


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory
                    factory, int version, String id) {
        super(context, name, factory, version);
        this.id_usuario = id;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL("CREATE TABLE" + HijoEntry.TABLE_NAME + "("
                                    + HijoEntry._ID + " TEXT PRIMARY KEY,"
                                    + HijoEntry.COD_CEDULA + " TEXT NOT NULL,"
                                    + HijoEntry.NOMBRE + " TEXT NOT NULL,"
                                    + HijoEntry.APELLIDO + " TEXT NOT NULL,"
                                    + HijoEntry.FECHA_NACIMIENTO + " TEXT NOT NULL,"
                                    + HijoEntry.LUGAR_NACIMIENTO + " TEXT NOT NULL,"
                                    + HijoEntry.SEXO + " TEXT NOT NULL,"
                                    + HijoEntry.NACIONALIDAD + " TEXT NOT NULL,"
                                    + HijoEntry.DIRECCION + " TEXT NOT NULL,"
                                    + HijoEntry.DEPARTAMENTO + " TEXT NOT NULL,"
                                    + HijoEntry.MUNICIPIO + " TEXT NOT NULL,"
                                    + HijoEntry.BARRIO + " TEXT NOT NULL,"
                                    + HijoEntry.REFERENCIA_UBICACION + " TEXT NOT NULL,"
                                    + HijoEntry.TELEFONO + " TEXT NOT NULL,"
                                    + HijoEntry.SEGURO_MEDICO + " TEXT NOT NULL,"
                                    + HijoEntry.CONTRAINDICACION + " TEXT NOT NULL,"
                                    + HijoEntry.ID_USUARIO + " TEXT NOT NULL,"
                                    + "UNIQUE (" + HijoEntry.COD_CEDULA + "))"
                                    );

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS vacunas (" +
                                    "id_vacuna integer not null, " +
                                    "nombre text not null, " +
                                    "id_hijo integer not null, " +
                                    "fecha_aplicacion text not null, " +
                                    "aplicada text not null, " +
                                    "PRIMARY KEY (id_vacuna), " +
                                    "FOREIGN KEY (id_hijo) REFERENCES hijos(cod_cedula));"
                                    );
            //Cargar los datos
            cargarHijos(sqLiteDatabase);
            cargarVacuna(sqLiteDatabase);
        }catch (Exception e){
            System.out.println("Error al cargar los datos");
        }
    }

    public void cargarHijos(SQLiteDatabase sqLiteDatabase){
        obtenerHijos();
        for (int i=0; i< listaHijo.size(); i++){
            insertarHijo(sqLiteDatabase, new Hijo(listaHijo.get(i).getCod_cedula(),
                                                listaHijo.get(i).getNombre(),
                                                listaHijo.get(i).getApellido(),
                                                listaHijo.get(i).getFecha_nacimiento(),
                                                listaHijo.get(i).getLugar_nacimiento(),
                                                listaHijo.get(i).getSexo(),
                                                listaHijo.get(i).getNacionalidad(),
                                                listaHijo.get(i).getDireccion(),
                                                listaHijo.get(i).getDepartamento(),
                                                listaHijo.get(i).getMunicipio(),
                                                listaHijo.get(i).getBarrio(),
                                                listaHijo.get(i).getReferencia_ubicacion(),
                                                listaHijo.get(i).getTelefono(),
                                                listaHijo.get
                    ));
        }
    }

    private void obtenerHijos(){
        String linkService = servidor + "/RestService/webresources/usuario/gethijos";

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost linkpost = new HttpPost(linkService);
            linkpost.setHeader("Accept", "application/json");
            linkpost.setHeader("Content-type", "application/json");
            JSONObject jsonParametro = new JSONObject();
            jsonParametro.put("id_usuario", id_usuario);
            StringEntity cadenaEntity = new StringEntity(jsonParametro.toString());
            linkpost.setEntity(cadenaEntity);

            HttpResponse response = httpClient.execute(linkpost);
            String respuestaCadena = EntityUtils.toString(response.getEntity());
            JSONArray respuestaJson = new JSONArray(respuestaCadena);

            for (int i = 0; i < respuestaJson.length(); i++){
                JSONObject objeto = respuestaJson.getJSONObject(i);
                Hijo hijo;
                hijo = new Hijo();
                hijo.setCod_cedula(objeto.getString("cod_cedula"));
                hijo.setNombre(objeto.getString("nombre"));
                hijo.setApellido(objeto.getString("apellido"));
                hijo.setFecha_nacimiento(objeto.getString("fecha_nacimiento"));
                hijo.setLugar_nacimiento(objeto.getString("lugar_nacimiento"));
                hijo.setSexo(objeto.getString("sexo"));
                hijo.setNacionalidad(objeto.getString("nacionalidad"));
                hijo.setDireccion(objeto.getString("direccion"));
                hijo.setDepartamento(objeto.getString("departamento"));
                hijo.setMunicipio(objeto.getString("municipio"));
                hijo.setBarrio(objeto.getString("barrio"));
                hijo.setReferencia_ubicacion(objeto.getString("referencia_ubicacion"));
                hijo.setTelefono(objeto.getString("telefono"));
                hijo.setSeguro_medico(objeto.getString("seguro_medico"));
                hijo.setContraindicacion(objeto.getString("contraindicacion"));
                hijo.setId_usuario(objeto.getString("id_usuario"));
                listaHijo.add(i, hijo);
            }
        }catch (Exception e){
            Log.e("ServicioRest", ">>>>>Error<<<<<", e);
        }
    }

    private void cargarVacuna(SQLiteDatabase sqLiteDatabase){
        obtenerVacunas();
    }
}
