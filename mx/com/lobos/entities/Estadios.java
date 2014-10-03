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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author joseo
 */
@Entity
@Table(name = "estadios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadios.findAll", query = "SELECT e FROM Estadios e"),
    @NamedQuery(name = "Estadios.findByIdEstadio", query = "SELECT e FROM Estadios e WHERE e.idEstadio = :idEstadio"),
    @NamedQuery(name = "Estadios.findByNombre", query = "SELECT e FROM Estadios e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estadios.findByCiudad", query = "SELECT e FROM Estadios e WHERE e.ciudad = :ciudad")})
public class Estadios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estadio")
    private Integer idEstadio;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "ciudad")
    private String ciudad;
    public Estadios() {
    }
    public Estadios(Integer idEstadio) {
        this.idEstadio = idEstadio;
    }
    public Estadios(Integer idEstadio, String nombre, String ciudad) {
        this.idEstadio = idEstadio;
        this.nombre = nombre;
        this.ciudad = ciudad;
    }
    public Integer getIdEstadio() {
        return idEstadio;
    }
    public void setIdEstadio(Integer idEstadio) {
        this.idEstadio = idEstadio;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadio != null ? idEstadio.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadios)) {
            return false;
        }
        Estadios other = (Estadios) object;
        if ((this.idEstadio == null && other.idEstadio != null) || (this.idEstadio != null && !this.idEstadio.equals(other.idEstadio))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "mx.com.lobos.entities.Estadios[ idEstadio=" + idEstadio + " ]";
    }
    
}
