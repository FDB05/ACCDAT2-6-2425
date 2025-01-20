/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.UnidadesJpaController;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author Fernando A
 */
@Entity
@Table(name = "UNIDADES")

@NamedQueries({
    @NamedQuery(name = "Unidades.findAll", query = "SELECT u FROM Unidades u"),
    @NamedQuery(name = "Unidades.findByNumerounidad", query = "SELECT u FROM Unidades u WHERE u.numerounidad = :numerounidad"),
    @NamedQuery(name = "Unidades.findByDisponibilidad", query = "SELECT u FROM Unidades u WHERE u.disponibilidad = :disponibilidad")})
public class Unidades implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMEROUNIDAD")
    private BigDecimal numerounidad;
    @Column(name = "DISPONIBILIDAD")
    private Boolean disponibilidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numerounidad")
    private Collection<Movilizaciones> movilizacionesCollection;
    @JoinColumn(name = "TIPOUNIDAD", referencedColumnName = "TIPOUNIDAD")
    @ManyToOne(optional = false)
    private Tipounidad tipounidad;

    public Unidades() {
    }

    public Unidades(BigDecimal numerounidad) {
        this.numerounidad = numerounidad;
    }

    public BigDecimal getNumerounidad() {
        return numerounidad;
    }

    public void setNumerounidad(BigDecimal numerounidad) {
        this.numerounidad = numerounidad;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    
    public Collection<Movilizaciones> getMovilizacionesCollection() {
        return movilizacionesCollection;
    }

    public void setMovilizacionesCollection(Collection<Movilizaciones> movilizacionesCollection) {
        this.movilizacionesCollection = movilizacionesCollection;
    }

    public Tipounidad getTipounidad() {
        return tipounidad;
    }

    public void setTipounidad(Tipounidad tipounidad) {
        this.tipounidad = tipounidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numerounidad != null ? numerounidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidades)) {
            return false;
        }
        Unidades other = (Unidades) object;
        if ((this.numerounidad == null && other.numerounidad != null) || (this.numerounidad != null && !this.numerounidad.equals(other.numerounidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.llamadasemergencia.Unidades[ numerounidad=" + numerounidad + " ]";
    }
    
    //--------------------------------------------------------------------------
    //METODOS INSERTAR, BORRAR MODIFICAR Y LEER(LLAMADAS)
    //--------------------------------------------------------------------------
    
        
        
        
    
    
}
