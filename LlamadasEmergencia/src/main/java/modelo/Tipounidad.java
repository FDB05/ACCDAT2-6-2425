/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Fernando A
 */
@Entity
@Table(name = "TIPOUNIDAD")

@NamedQueries({
    @NamedQuery(name = "Tipounidad.findAll",
            query = "SELECT t FROM Tipounidad t"),

    @NamedQuery(name = "Tipounidad.findByTipounidad",
            query = "SELECT t FROM Tipounidad t WHERE t.tipounidad = :tipounidad"),

    @NamedQuery(name = "Tipounidad.findByNombreunidad",
            query = "SELECT t FROM Tipounidad t WHERE t.nombreunidad = :nombreunidad"),

    @NamedQuery(name = "Tipounidad.findByTelefono",
            query = "SELECT t FROM Tipounidad t WHERE t.numerotelefono = :telefono"),

    @NamedQuery(name = "Tipounidad.findByLocalidad",
            query = "SELECT t FROM Tipounidad t WHERE t.localidad = :localidad"),

    @NamedQuery(name = "Tipounidad.findByTelefonoYLocalidad",
            query = "SELECT t FROM Tipounidad t WHERE t.numerotelefono = :telefono AND t.localidad = :localidad")})
public class Tipounidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "TIPOUNIDAD")
    private String tipounidad;
    @Size(max = 20)
    @Column(name = "NUMEROTELEFONO")
    private String numerotelefono;

    @Size(max = 50)
    @Column(name = "LOCALIDAD")
    private String localidad;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBREUNIDAD")
    private String nombreunidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipounidad")
    private Collection<Unidades> unidadesCollection;

    public Tipounidad() {
    }

    public Tipounidad(String tipounidad) {
        this.tipounidad = tipounidad;
    }

    public Tipounidad(String tipounidad, String nombreunidad) {
        this.tipounidad = tipounidad;
        this.nombreunidad = nombreunidad;
    }
    public Tipounidad(String tipounidad, String nombreunidad, String numerotelefono, String localidad) {
        this.tipounidad = tipounidad;
        this.nombreunidad = nombreunidad;
        this.numerotelefono = numerotelefono;
        this.localidad = localidad;
    }


    public String getTipounidad() {
        return tipounidad;
    }

    public void setTipounidad(String tipounidad) {
        this.tipounidad = tipounidad;
    }

    public String getNombreunidad() {
        return nombreunidad;
    }

    public void setNombreunidad(String nombreunidad) {
        this.nombreunidad = nombreunidad;
    }

    public String getNumerotelefono() {
        return numerotelefono;
    }

    public void setNumerotelefono(String numerotelefono) {
        this.numerotelefono = numerotelefono;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Collection<Unidades> getUnidadesCollection() {
        return unidadesCollection;
    }

    public void setUnidadesCollection(Collection<Unidades> unidadesCollection) {
        this.unidadesCollection = unidadesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipounidad != null ? tipounidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipounidad)) {
            return false;
        }
        Tipounidad other = (Tipounidad) object;
        if ((this.tipounidad == null && other.tipounidad != null) || (this.tipounidad != null && !this.tipounidad.equals(other.tipounidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.llamadasemergencia.Tipounidad[ tipounidad=" + tipounidad + " ]";
    }
    
}
