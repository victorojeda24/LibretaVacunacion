package pol.apprv.data;

import android.content.Context;
import android.database.Cursor;
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

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Método que necesitaba poner por la extensión SQLiteOpenHelper
    }

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
                                    + HijoEntry.COD_CEDULA + " SERIAL NOT NULL,"
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
                                    "id_vacuna serial not null, " +
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
            System.out.println("Error al cargar los datos en la tabla");
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
                                                listaHijo.get(i).getSeguro_medico(),
                                                listaHijo.get(i).getContraindicacion(),
                                                listaHijo.get(i).getId_usuario()
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
            Log.e("ServicioRest", ">>>>>Error[obtenerHijos]<<<<<", e);
        }
    }

    private long insertarHijo(SQLiteDatabase sqLiteDatabase, Hijo hijo){
        return sqLiteDatabase.insert(HijoEntry.TABLE_NAME, null,
                                        hijo.toContentValues()
                                    );
    }

    private void cargarVacuna(SQLiteDatabase sqLiteDatabase){
        obtenerVacunas();
        for (int i=0; i < listaVacuna.size(); i++){
            insertarVacuna(sqLiteDatabase, new Vacuna(listaVacuna.get(i).getId_vacuna(),
                                                        listaVacuna.get(i).getNombre(),
                                                        listaVacuna.get(i).getId_hijo(),
                                                        listaVacuna.get(i).getFecha_aplicacion(),
                                                        listaVacuna.get(i).getAplicada()
                                                    )
                            );
        }
    }

    private void obtenerVacunas(){
        String linkService = servidor + "/RestService/webresources/usuario/getregistro?userId=";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost linkPost = new HttpPost(linkService);
            linkPost.setHeader("Accept", "application/json");
            linkPost.setHeader("Content-type", "application/json");

            JSONObject jsonParametro = new JSONObject();
            jsonParametro.put("id_usuario", id_usuario);
            StringEntity cadenaEntity = new StringEntity(jsonParametro.toString());

            linkPost.setEntity(cadenaEntity);

            HttpResponse response = httpClient.execute(linkPost);

            String respuestaCadena = EntityUtils.toString(response.getEntity());

            JSONArray jsonRespuesta = new JSONArray(respuestaCadena);

            for (int i=0; i < jsonRespuesta.length(); i++){
                JSONObject jsonObjeto = jsonRespuesta.getJSONObject(i);

                Vacuna vacuna = new Vacuna();

                vacuna.setId_vacuna(jsonObjeto.getInt("id_vacuna"));
                vacuna.setNombre(jsonObjeto.getString("nombre"));
                vacuna.setId_hijo(jsonObjeto.getInt("id_hijo"));
                vacuna.setFecha_aplicacion(jsonObjeto.getString("fecha_aplicacion"));
                vacuna.setAplicada(jsonObjeto.getString("aplicada"));

                listaVacuna.add(vacuna);
            }
        }catch (Exception e){
            Log.e("ServicioRest", ">>>>>Error[obtenerVacunas]<<<<<", e);
        }
    }

    private long insertarVacuna(SQLiteDatabase sqLiteDatabase, Vacuna vacuna){
        return sqLiteDatabase.insert(VacunaPlantilla.VacunaEntry.TABLE_NAME,
                                        null, vacuna.toContentValues()
                                    );
    }

    public Cursor getAllHijos(){
        return getReadableDatabase().query(HijoEntry.TABLE_NAME, null, null,
                                            null, null, null, null
                                            );
    }

    public Cursor getHijoPorId(String id){
        return getReadableDatabase().query(HijoEntry.TABLE_NAME, null,
                                            HijoEntry.COD_CEDULA + " LIKE ?",
                                            new String[]{id}, null, null, null
                                            );
    }

    public ArrayList llenarListaVacuna(int orden, String idHijo){
        ArrayList<Vacuna> listaVacuna = new ArrayList<>();
        String query;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor registros;

        switch (orden){
            case 0:
                query = "SELECT * \n" +
                        "FROM vacunas \n" +
                        "WHERE id_hijo=?;";
                registros= database.rawQuery(query, new String[]{idHijo});
                break;
            case 1:
                query = "SELECT * \n" +
                        "FROM vacunas \n" +
                        "WHERE aplicada='?' AND id_hijo=?;";
                registros = database.rawQuery(query, new String[]{"1", idHijo});
                break;
            case 2:
                query = "SELECT * \n" +
                        "FROM vacunas \n" +
                        "WHERE aplicada='?' AND id_hijo=?;";
                registros = database.rawQuery(query, new String[]{"1", idHijo});
                break;
            case 3:
                query = "SELECT * \n" +
                        "FROM vacunas \n" +
                        "WHERE id_hijo=? \n" +
                        "ORDER BY nombre;";
                registros = database.rawQuery(query, new String[]{idHijo});
                break;
            default:
                query = "SELECT * \n" +
                        "FROM vacunas \n" +
                        "WHERE id_hijo=? \n" +
                        "ORDER BY fecha_aplicacion;";
                registros = database.rawQuery(query, new String[]{idHijo});
                break;
        }

        Vacuna vacuna;

        if(registros.moveToFirst()){
            do {
                vacuna=new Vacuna();
                vacuna.setNombre(registros.getString(4));
                vacuna.setFecha_aplicacion(registros.getString(6));
                listaVacuna.add(vacuna);
            }while (registros.moveToNext());
        }
        return listaVacuna;
    }

    public ArrayList obtenerFechas(){
        ArrayList<String> fechas = new ArrayList<>();
        String query;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor registros;

        try {
            query = "SELECT fecha_aplicacion \n" +
                    "FROM vacunas \n" +
                    "ORDER BY fecha_aplicacion;";
            registros = database.rawQuery(query, null);
            if (registros.moveToFirst()){
                do {
                    fechas.add(registros.getString(0));
                }while (registros.moveToNext());
            }
        }catch (Exception e){
            e.getMessage();
        }
        return fechas;
    }
}
