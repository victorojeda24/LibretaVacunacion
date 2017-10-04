/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.sql.Date;

/**
 *
 * @author ari
 */
public class Hijo {
    Integer cod_cedula ;
    String nombre;
    String apellido;
    Date fecha_nacimiento;
    String lugar_nacimiento;
    String sexo;
    String nacionalidad;
    String direccion;
    String departamento;
    String municipio;
    String barrio;
    String referencia_ubicacion;
    String telefono;
    String seguro_medico;
    String contraindicacion;
    Integer id_usuario;

    public Hijo(Integer cod_cedula, String nombre, String apellido,
                Date fecha_nacimiento, String lugar_nacimiento, String sexo,
                String nacionalidad, String direccion, String departamento,
                String municipio, String barrio, String referencia_ubicacion,
                String telefono, String seguro_medico, String contraindicacion,
                Integer id_usuario) {
        this.cod_cedula = cod_cedula;
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
        this.referencia_ubicacion = referencia_ubicacion;
        this.telefono = telefono;
        this.seguro_medico = seguro_medico;
        this.contraindicacion = contraindicacion;
        this.id_usuario = id_usuario;
    }

    public Hijo() {
        cod_cedula = 0;
        nombre = "";
        apellido = "";
        fecha_nacimiento = new Date(0);
        lugar_nacimiento = "";
        sexo = "";
        nacionalidad = "";
        direccion = "";
        departamento = "";
        municipio = "";
        barrio = "";
        referencia_ubicacion = "";
        telefono = "";
        seguro_medico = "";
        contraindicacion = "";
        id_usuario = 0;
    }

    public Integer getCod_cedula() {
        return cod_cedula;
    }

    public void setCod_cedula(Integer cod_cedula) {
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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
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

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "Hijo{" + "cod_cedula=" + cod_cedula + ", nombre=" + nombre + 
                ", apellido=" + apellido + ", fecha_nacimiento=" + 
                fecha_nacimiento + ", lugar_nacimiento=" + lugar_nacimiento + 
                ", sexo=" + sexo + ", nacionalidad=" + nacionalidad + 
                ", direccion=" + direccion + ", departamento=" + departamento +
                ", municipio=" + municipio + ", barrio=" + barrio + 
                ", referencia_ubicacion=" + referencia_ubicacion + ", telefono="
                + telefono + ", seguro_medico=" + seguro_medico + 
                ", contraindicacion=" + contraindicacion + ", id_usuario=" +
                id_usuario + '}';
    }
    
    
}
