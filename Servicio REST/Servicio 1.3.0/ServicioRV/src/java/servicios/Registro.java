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
public class Registro {
    Integer id_registro;
    Integer id_vacuna;
    Integer cod_cedula;
    Integer edad_meses;
    Integer dosis;
    Date fecha;
    String lote;
    String responsable;
    String estado;

    public Registro() {
        id_registro = 0;
        id_vacuna = 0;
        cod_cedula = 0;
        edad_meses = 0;
        dosis = 0;
        fecha = new Date(0);
        lote = "";
        responsable = "";
        estado = "";
    }

    public Registro(Integer id_registro, Integer id_vacuna, Integer cod_cedula,
                    Integer edad_meses, Integer dosis, Date fecha, String lote,
                    String responsable, String estado) {
        this.id_registro = id_registro;
        this.id_vacuna = id_vacuna;
        this.cod_cedula = cod_cedula;
        this.edad_meses = edad_meses;
        this.dosis = dosis;
        this.fecha = fecha;
        this.lote = lote;
        this.responsable = responsable;
        this.estado = estado;
    }

    public Integer getId_registro() {
        return id_registro;
    }

    public void setId_registro(Integer id_registro) {
        this.id_registro = id_registro;
    }

    public Integer getId_vacuna() {
        return id_vacuna;
    }

    public void setId_vacuna(Integer id_vacuna) {
        this.id_vacuna = id_vacuna;
    }

    public Integer getCod_cedula() {
        return cod_cedula;
    }

    public void setCod_cedula(Integer cod_cedula) {
        this.cod_cedula = cod_cedula;
    }

    public Integer getEdad_meses() {
        return edad_meses;
    }

    public void setEdad_meses(Integer edad_meses) {
        this.edad_meses = edad_meses;
    }

    public Integer getDosis() {
        return dosis;
    }

    public void setDosis(Integer dosis) {
        this.dosis = dosis;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
