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
@Table(name = "ESTADO")
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e"),
    @NamedQuery(name = "Estado.findByTipoestado", query = "SELECT e FROM Estado e WHERE e.tipoestado = :tipoestado"),
    @NamedQuery(name = "Estado.findByNombreestado", query = "SELECT e FROM Estado e WHERE e.nombreestado = :nombreestado")})
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "TIPOESTADO")
    private String tipoestado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBREESTADO")
    private String nombreestado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private Collection<Llamadas> llamadasCollection;

    public Estado() {
    }

    public Estado(String tipoestado) {
        this.tipoestado = tipoestado;
    }

    public Estado(String tipoestado, String nombreestado) {
        this.tipoestado = tipoestado;
        this.nombreestado = nombreestado;
    }

    public String getTipoestado() {
        return tipoestado;
    }

    public void setTipoestado(String tipoestado) {
        this.tipoestado = tipoestado;
    }

    public String getNombreestado() {
        return nombreestado;
    }

    public void setNombreestado(String nombreestado) {
        this.nombreestado = nombreestado;
    }

    
    public Collection<Llamadas> getLlamadasCollection() {
        return llamadasCollection;
    }

    public void setLlamadasCollection(Collection<Llamadas> llamadasCollection) {
        this.llamadasCollection = llamadasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoestado != null ? tipoestado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.tipoestado == null && other.tipoestado != null) || (this.tipoestado != null && !this.tipoestado.equals(other.tipoestado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.llamadasemergencia.Estado[ tipoestado=" + tipoestado + " ]";
    }
    
}
