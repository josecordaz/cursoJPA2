/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.lobos.entities;
import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author joseo
 */
@Entity
@Table(name = "equipos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipos.findAll", query = "SELECT e FROM Equipos e"),
    @NamedQuery(name = "Equipos.findByIdEquipo", query = "SELECT e FROM Equipos e WHERE e.idEquipo = :idEquipo"),
    @NamedQuery(name = "Equipos.findByAbreviatura", query = "SELECT e FROM Equipos e WHERE e.abreviatura = :abreviatura"),
    @NamedQuery(name = "Equipos.findByNombre", query = "SELECT e FROM Equipos e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Equipos.findByIdGrupo", query = "SELECT e FROM Equipos e WHERE e.idGrupo = :idGrupo")})
public class Equipos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_equipo")
    private Integer idEquipo;
    @Basic(optional = false)
    @Column(name = "abreviatura")
    private String abreviatura;
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    @ManyToOne(optional = false)
    private Grupos idGrupo;
    public Equipos() {
    }
    public Equipos(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }
    public Equipos(Integer idEquipo, String abreviatura) {
        this.idEquipo = idEquipo;
        this.abreviatura = abreviatura;
    }
    public Integer getIdEquipo() {
        return idEquipo;
    }
    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }
    public String getAbreviatura() {
        return abreviatura;
    }
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Grupos getIdGrupo() {
        return idGrupo;
    }
    public void setIdGrupo(Grupos idGrupo) {
        this.idGrupo = idGrupo;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipo != null ? idEquipo.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipos)) {
            return false;
        }
        Equipos other = (Equipos) object;
        if ((this.idEquipo == null && other.idEquipo != null) || (this.idEquipo != null && !this.idEquipo.equals(other.idEquipo))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "mx.com.lobos.entities.Equipos[ idEquipo=" + idEquipo + " ]";
    }
    
}
