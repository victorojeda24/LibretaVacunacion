package pol.apprv.data;

import android.content.ContentValues;
import android.database.Cursor;

import pol.apprv.data.HijoPlantilla.HijoEntry;

/**
 * Clase hijo, cursor y getters
 */

public class Hijo {
    private String cod_cedula;
    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
    private String lugar_nacimiento;
    private String sexo;
    private String nacionalidad;
    private String direccion;
    private String departamento;
    private String municipio;
    private String barrio;
    private String referencia_ubicacion;
    private String telefono;
    private String seguro_medico;
    private String contraindicacion;
    private String id_usuario;

    public Hijo() {
        this.cod_cedula = "";
        this.nombre = "";
        this.apellido = "";
        this.fecha_nacimiento = "";
        this.lugar_nacimiento = "";
        this.sexo = "";
        this.nacionalidad = "";
        this.direccion = "";
        this.departamento = "";
        this.municipio = "";
        this.barrio = "";
        this.referencia_ubicacion = "";
        this.telefono = "";
        this.seguro_medico = "";
        this.contraindicacion = "";
        this.id_usuario = "";
    }

    public Hijo(String id, String nombre, String apellido,
                String fecha_nacimiento, String lugar_nacimiento, String sexo,
                String nacionalidad, String direccion, String departamento,
                String municipio, String barrio, String referencia_domicilio,
                String responsable, String telefono_contacto,
                String seguro_medico, String id_usuario) {
        this.cod_cedula = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.lugar_nacimiento = lugar_nacimiento;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
        this.direccion = direccion;
        this.departamento = departamento;
        this.municipio = municipio;
        this.barrio = barrio;
        this.referencia_ubicacion = referencia_domicilio;
        this.telefono = responsable;
        this.seguro_medico = telefono_contacto;
        this.contraindicacion = seguro_medico;
        this.id_usuario = id_usuario;
    }

    public Hijo(Cursor cursor) {
        cod_cedula = cursor.getString(cursor.getColumnIndex(HijoEntry.COD_CEDULA));
        nombre = cursor.getString(cursor.getColumnIndex(HijoEntry.NOMBRE));
        apellido = cursor.getString(cursor.getColumnIndex(HijoEntry.APELLIDO));
        fecha_nacimiento = cursor.getString(cursor.getColumnIndex(HijoEntry.FECHA_NACIMIENTO));
        lugar_nacimiento = cursor.getString(cursor.getColumnIndex(HijoEntry.LUGAR_NACIMIENTO));
        sexo = cursor.getString(cursor.getColumnIndex(HijoEntry.SEXO));
        nacionalidad = cursor.getString(cursor.getColumnIndex(HijoEntry.NACIONALIDAD));
        direccion = cursor.getString(cursor.getColumnIndex(HijoEntry.DIRECCION));
        departamento = cursor.getString(cursor.getColumnIndex(HijoEntry.DEPARTAMENTO));
        municipio = cursor.getString(cursor.getColumnIndex(HijoEntry.MUNICIPIO));
        barrio = cursor.getString(cursor.getColumnIndex(HijoEntry.BARRIO));
        referencia_ubicacion = cursor.getString(cursor.getColumnIndex(HijoEntry.REFERENCIA_UBICACION));
        telefono = cursor.getString(cursor.getColumnIndex(HijoEntry.TELEFONO));
        seguro_medico = cursor.getString(cursor.getColumnIndex(HijoEntry.SEGURO_MEDICO));
        contraindicacion = cursor.getString(cursor.getColumnIndex(HijoEntry.CONTRAINDICACION));
        id_usuario = cursor.getString(cursor.getColumnIndex(HijoEntry.ID_USUARIO));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(HijoEntry.COD_CEDULA, cod_cedula);
        values.put(HijoEntry.NOMBRE, nombre);
        values.put(HijoEntry.APELLIDO, apellido);
        values.put(HijoEntry.FECHA_NACIMIENTO, fecha_nacimiento);
        values.put(HijoEntry.LUGAR_NACIMIENTO, lugar_nacimiento);
        values.put(HijoEntry.SEXO, sexo);
        values.put(HijoEntry.NACIONALIDAD, nacionalidad);
        values.put(HijoEntry.DIRECCION, direccion);
        values.put(HijoEntry.DEPARTAMENTO, departamento);
        values.put(HijoEntry.MUNICIPIO, municipio);
        values.put(HijoEntry.BARRIO, barrio);
        values.put(HijoEntry.REFERENCIA_UBICACION, referencia_ubicacion);
        values.put(HijoEntry.TELEFONO, telefono);
        values.put(HijoEntry.SEGURO_MEDICO, seguro_medico);
        values.put(HijoEntry.CONTRAINDICACION, contraindicacion);
        values.put(HijoEntry.ID_USUARIO, id_usuario);
        return values;
    }

    public String getCod_cedula() {
        return cod_cedula;
    }

    public void setCod_cedula(String cod_cedula) {
        this.cod_cedula = cod_cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getLugar_nacimiento() {
        return lugar_nacimiento;
    }

    public void setLugar_nacimiento(String lugar_nacimiento) {
        this.lugar_nacimiento = lugar_nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getReferencia_ubicacion() {
        return referencia_ubicacion;
    }

    public void setReferencia_ubicacion(String referencia_ubicacion) {
        this.referencia_ubicacion = referencia_ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSeguro_medico() {
        return seguro_medico;
    }

    public void setSeguro_medico(String seguro_medico) {
        this.seguro_medico = seguro_medico;
    }

    public String getContraindicacion() {
        return contraindicacion;
    }

    public void setContraindicacion(String contraindicacion) {
        this.contraindicacion = contraindicacion;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "Hijo{" +
                "cod_cedula='" + cod_cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fecha_nacimiento='" + fecha_nacimiento + '\'' +
                ", lugar_nacimiento='" + lugar_nacimiento + '\'' +
                ", sexo='" + sexo + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", direccion='" + direccion + '\'' +
                ", departamento='" + departamento + '\'' +
                ", municipio='" + municipio + '\'' +
                ", barrio='" + barrio + '\'' +
                ", referencia_ubicacion='" + referencia_ubicacion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", seguro_medico='" + seguro_medico + '\'' +
                ", contraindicacion='" + contraindicacion + '\'' +
                ", id_usuario='" + id_usuario + '\'' +
                '}';
    }
}
