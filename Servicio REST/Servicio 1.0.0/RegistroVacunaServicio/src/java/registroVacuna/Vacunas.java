/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registroVacuna;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "vacunas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vacunas.findAll", query = "SELECT v FROM Vacunas v"),
    @NamedQuery(name = "Vacunas.findByIdVacuna", query = "SELECT v FROM Vacunas v WHERE v.idVacuna = :idVacuna"),
    @NamedQuery(name = "Vacunas.findByCantidadDosis", query = "SELECT v FROM Vacunas v WHERE v.cantidadDosis = :cantidadDosis"),
    @NamedQuery(name = "Vacunas.findByEnfermedadPreviene", query = "SELECT v FROM Vacunas v WHERE v.enfermedadPreviene = :enfermedadPreviene"),
    @NamedQuery(name = "Vacunas.findByNombre", query = "SELECT v FROM Vacunas v WHERE v.nombre = :nombre")})
public class Vacunas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_vacuna")
    private Integer idVacuna;
    @Column(name = "cantidad_dosis")
    private Integer cantidadDosis;
    @Size(max = 2147483647)
    @Column(name = "enfermedad_previene")
    private String enfermedadPreviene;
    @Size(max = 32)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idVacuna")
    private Collection<Registros> registrosCollection;

    public Vacunas() {
    }

    public Vacunas(Integer idVacuna) {
        this.idVacuna = idVacuna;
    }

    public Integer getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(Integer idVacuna) {
        this.idVacuna = idVacuna;
    }

    public Integer getCantidadDosis() {
        return cantidadDosis;
    }

    public void setCantidadDosis(Integer cantidadDosis) {
        this.cantidadDosis = cantidadDosis;
    }

    public String getEnfermedadPreviene() {
        return enfermedadPreviene;
    }

    public void setEnfermedadPreviene(String enfermedadPreviene) {
        this.enfermedadPreviene = enfermedadPreviene;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idVacuna != null ? idVacuna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vacunas)) {
            return false;
        }
        Vacunas other = (Vacunas) object;
        if ((this.idVacuna == null && other.idVacuna != null) || (this.idVacuna != null && !this.idVacuna.equals(other.idVacuna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "registroVacuna.Vacunas[ idVacuna=" + idVacuna + " ]";
    }
    
}
