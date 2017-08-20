/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registroVacuna;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "registros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registros.findAll", query = "SELECT r FROM Registros r"),
    @NamedQuery(name = "Registros.findByIdRegistro", query = "SELECT r FROM Registros r WHERE r.idRegistro = :idRegistro"),
    @NamedQuery(name = "Registros.findByEdadMeses", query = "SELECT r FROM Registros r WHERE r.edadMeses = :edadMeses"),
    @NamedQuery(name = "Registros.findByDosis", query = "SELECT r FROM Registros r WHERE r.dosis = :dosis"),
    @NamedQuery(name = "Registros.findByFecha", query = "SELECT r FROM Registros r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "Registros.findByLote", query = "SELECT r FROM Registros r WHERE r.lote = :lote"),
    @NamedQuery(name = "Registros.findByResponsable", query = "SELECT r FROM Registros r WHERE r.responsable = :responsable"),
    @NamedQuery(name = "Registros.findByEstado", query = "SELECT r FROM Registros r WHERE r.estado = :estado")})
public class Registros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_registro")
    private Integer idRegistro;
    @Column(name = "edad_meses")
    private Integer edadMeses;
    @Column(name = "dosis")
    private Integer dosis;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 2147483647)
    @Column(name = "lote")
    private String lote;
    @Size(max = 2147483647)
    @Column(name = "responsable")
    private String responsable;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "cod_cedula", referencedColumnName = "cod_cedula")
    @ManyToOne
    private Hijos codCedula;
    @JoinColumn(name = "id_vacuna", referencedColumnName = "id_vacuna")
    @ManyToOne
    private Vacunas idVacuna;

    public Registros() {
    }

    public Registros(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Integer getEdadMeses() {
        return edadMeses;
    }

    public void setEdadMeses(Integer edadMeses) {
        this.edadMeses = edadMeses;
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

    public Hijos getCodCedula() {
        return codCedula;
    }

    public void setCodCedula(Hijos codCedula) {
        this.codCedula = codCedula;
    }

    public Vacunas getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(Vacunas idVacuna) {
        this.idVacuna = idVacuna;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistro != null ? idRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registros)) {
            return false;
        }
        Registros other = (Registros) object;
        if ((this.idRegistro == null && other.idRegistro != null) || (this.idRegistro != null && !this.idRegistro.equals(other.idRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "registroVacuna.Registros[ idRegistro=" + idRegistro + " ]";
    }
    
}
