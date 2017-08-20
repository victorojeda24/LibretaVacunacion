/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registroVacuna;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @NotNull
    @Column(name = "cod_cedula")
    private Integer codCedula;
    @Size(max = 32)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 32)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 2147483647)
    @Column(name = "lugar_nacimiento")
    private String lugarNacimiento;
    @Size(max = 1)
    @Column(name = "sexo")
    private String sexo;
    @Size(max = 2147483647)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Size(max = 100)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 2147483647)
    @Column(name = "departamento")
    private String departamento;
    @Size(max = 2147483647)
    @Column(name = "municipio")
    private String municipio;
    @Size(max = 2147483647)
    @Column(name = "barrio")
    private String barrio;
    @Size(max = 2147483647)
    @Column(name = "referencia_ubicacion")
    private String referenciaUbicacion;
    @Size(max = 2147483647)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 2147483647)
    @Column(name = "seguro_medico")
    private String seguroMedico;
    @Size(max = 2147483647)
    @Column(name = "contraindicacion")
    private String contraindicacion;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne
    private Usuarios idUsuario;
    @OneToMany(mappedBy = "codCedula")
    private Collection<Registros> registrosCollection;

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

    @XmlTransient
    public Collection<Registros> getRegistrosCollection() {
        return registrosCollection;
    }

    public void setRegistrosCollection(Collection<Registros> registrosCollection) {
        this.registrosCollection = registrosCollection;
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
        return "registroVacuna.Hijos[ codCedula=" + codCedula + " ]";
    }
    
}
