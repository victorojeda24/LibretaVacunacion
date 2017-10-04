/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "hijos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hijos.findAll", query = "SELECT h FROM Hijos h"),
    @NamedQuery(name = "Hijos.findByCodCedula", query = "SELECT h FROM Hijos h WHERE h.codCedula = :codCedula"),
    @NamedQuery(name = "Hijos.findByNombre", query = "SELECT h FROM Hijos h WHERE h.nombre = :nombre"),
    @NamedQuery(name = "Hijos.findByApellido", query = "SELECT h FROM Hijos h WHERE h.apellido = :apellido"),
    @NamedQuery(name = "Hijos.findByFechaNacimiento", query = "SELECT h FROM Hijos h WHERE h.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Hijos.findByLugarNacimiento", query = "SELECT h FROM Hijos h WHERE h.lugarNacimiento = :lugarNacimiento"),
    @NamedQuery(name = "Hijos.findBySexo", query = "SELECT h FROM Hijos h WHERE h.sexo = :sexo"),
    @NamedQuery(name = "Hijos.findByNacionalidad", query = "SELECT h FROM Hijos h WHERE h.nacionalidad = :nacionalidad"),
    @NamedQuery(name = "Hijos.findByDireccion", query = "SELECT h FROM Hijos h WHERE h.direccion = :direccion"),
    @NamedQuery(name = "Hijos.findByDepartamento", query = "SELECT h FROM Hijos h WHERE h.departamento = :departamento"),
    @NamedQuery(name = "Hijos.findByMunicipio", query = "SELECT h FROM Hijos h WHERE h.municipio = :municipio"),
    @NamedQuery(name = "Hijos.findByBarrio", query = "SELECT h FROM Hijos h WHERE h.barrio = :barrio"),
    @NamedQuery(name = "Hijos.findByReferenciaUbicacion", query = "SELECT h FROM Hijos h WHERE h.referenciaUbicacion = :referenciaUbicacion"),
    @NamedQuery(name = "Hijos.findByTelefono", query = "SELECT h FROM Hijos h WHERE h.telefono = :telefono"),
    @NamedQuery(name = "Hijos.findBySeguroMedico", query = "SELECT h FROM Hijos h WHERE h.seguroMedico = :seguroMedico"),
    @NamedQuery(name = "Hijos.findByContraindicacion", query = "SELECT h FROM Hijos h WHERE h.contraindicacion = :contraindicacion")})
public class Hijos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_cedula")
    private Integer codCedula;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "lugar_nacimiento")
    private String lugarNacimiento;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "departamento")
    private String departamento;
    @Column(name = "municipio")
    private String municipio;
    @Column(name = "barrio")
    private String barrio;
    @Column(name = "referencia_ubicacion")
    private String referenciaUbicacion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "seguro_medico")
    private String seguroMedico;
    @Column(name = "contraindicacion")
    private String contraindicacion;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne
    private Usuarios idUsuario;

    public Hijos() {
    }

    public Hijos(Integer codCedula) {
        this.codCedula = codCedula;
    }

    public Integer getCodCedula() {
        return codCedula;
    }

    public void setCodCedula(Integer codCedula) {
        this.codCedula = codCedula;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
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

    public String getReferenciaUbicacion() {
        return referenciaUbicacion;
    }

    public void setReferenciaUbicacion(String referenciaUbicacion) {
        this.referenciaUbicacion = referenciaUbicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSeguroMedico() {
        return seguroMedico;
    }

    public void setSeguroMedico(String seguroMedico) {
        this.seguroMedico = seguroMedico;
    }

    public String getContraindicacion() {
        return contraindicacion;
    }

    public void setContraindicacion(String contraindicacion) {
        this.contraindicacion = contraindicacion;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCedula != null ? codCedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hijos)) {
            return false;
        }
        Hijos other = (Hijos) object;
        if ((this.codCedula == null && other.codCedula != null) || (this.codCedula != null && !this.codCedula.equals(other.codCedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Hijos[ codCedula=" + codCedula + " ]";
    }
    
}
