/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Fernando A
 */
@Entity
@Table(name = "MOVILIZACIONES")

@NamedQueries({
    @NamedQuery(name = "Movilizaciones.findAll", query = "SELECT m FROM Movilizaciones m"),
    @NamedQuery(name = "Movilizaciones.findByIdmovilizacion", query = "SELECT m FROM Movilizaciones m WHERE m.idmovilizacion = :idmovilizacion")})
public class Movilizaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDMOVILIZACION")
    private BigDecimal idmovilizacion;
    @JoinColumn(name = "NUMEROTELF", referencedColumnName = "NUMEROTELF")
    @ManyToOne(optional = false)
    private Llamadas numerotelf;
    @JoinColumn(name = "NUMEROUNIDAD", referencedColumnName = "NUMEROUNIDAD")
    @ManyToOne(optional = false)
    private Unidades numerounidad;

    public Movilizaciones() {
    }

    public Movilizaciones(BigDecimal idmovilizacion) {
        this.idmovilizacion = idmovilizacion;
    }

    public BigDecimal getIdmovilizacion() {
        return idmovilizacion;
    }

    public void setIdmovilizacion(BigDecimal idmovilizacion) {
        this.idmovilizacion = idmovilizacion;
    }

    public Llamadas getNumerotelf() {
        return numerotelf;
    }

    public void setNumerotelf(Llamadas numerotelf) {
        this.numerotelf = numerotelf;
    }

    public Unidades getNumerounidad() {
        return numerounidad;
    }

    public void setNumerounidad(Unidades numerounidad) {
        this.numerounidad = numerounidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmovilizacion != null ? idmovilizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movilizaciones)) {
            return false;
        }
        Movilizaciones other = (Movilizaciones) object;
        if ((this.idmovilizacion == null && other.idmovilizacion != null) || (this.idmovilizacion != null && !this.idmovilizacion.equals(other.idmovilizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.llamadasemergencia.Movilizaciones[ idmovilizacion=" + idmovilizacion + " ]";
    }
    
}
