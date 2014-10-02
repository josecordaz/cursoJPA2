/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.lobos.entities;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author joseo
 */
@Entity
@Table(name = "partidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partidos.findAll", query = "SELECT p FROM Partidos p"),
    @NamedQuery(name = "Partidos.findById", query = "SELECT p FROM Partidos p WHERE p.id = :id"),
    @NamedQuery(name = "Partidos.findByFase", query = "SELECT p FROM Partidos p WHERE p.fase = :fase"),
    @NamedQuery(name = "Partidos.findByFecha", query = "SELECT p FROM Partidos p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Partidos.findByEstadio", query = "SELECT p FROM Partidos p WHERE p.idEstadio = :estadio")})
public class Partidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fase")
    private boolean fase;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "id_estadio", referencedColumnName = "id_estadio")
    @ManyToOne(optional = false)
    private Estadios idEstadio;
    @JoinColumn(name = "id_equipo1", referencedColumnName = "id_equipo")
    @ManyToOne(optional = false)
    private Equipos idEquipo1;
    @JoinColumn(name = "id_equipo2", referencedColumnName = "id_equipo")
    @ManyToOne(optional = false)
    private Equipos idEquipo2;
    public Partidos() {
    }
    public Partidos(Integer id) {
        this.id = id;
    }
    public Partidos(Integer id, boolean fase, Date fecha) {
        this.id = id;
        this.fase = fase;
        this.fecha = fecha;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public boolean getFase() {
        return fase;
    }
    public void setFase(boolean fase) {
        this.fase = fase;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Estadios getIdEstadio() {
        return idEstadio;
    }
    public void setIdEstadio(Estadios idEstadio) {
        this.idEstadio = idEstadio;
    }
    public Equipos getIdEquipo1() {
        return idEquipo1;
    }
    public void setIdEquipo1(Equipos idEquipo1) {
        this.idEquipo1 = idEquipo1;
    }
    public Equipos getIdEquipo2() {
        return idEquipo2;
    }
    public void setIdEquipo2(Equipos idEquipo2) {
        this.idEquipo2 = idEquipo2;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partidos)) {
            return false;
        }
        Partidos other = (Partidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "mx.com.lobos.entities.Partidos[ id=" + id + " ]";
    }
    
}
