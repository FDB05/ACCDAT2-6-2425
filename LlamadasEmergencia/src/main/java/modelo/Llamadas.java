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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Fernando A
 */
@Entity
@Table(name = "LLAMADAS")

@NamedQueries({
    @NamedQuery(name = "Llamadas.findAll", query = "SELECT l FROM Llamadas l"),
    @NamedQuery(name = "Llamadas.findByNumerotelf", query = "SELECT l FROM Llamadas l WHERE l.numerotelf = :numerotelf"),
    @NamedQuery(name = "Llamadas.findByFechahora", query = "SELECT l FROM Llamadas l WHERE l.fechahora = :fechahora"),
    @NamedQuery(name = "Llamadas.findByUbicacion", query = "SELECT l FROM Llamadas l WHERE l.ubicacion = :ubicacion"),
    @NamedQuery(name = "Llamadas.findByDescripcion", query = "SELECT l FROM Llamadas l WHERE l.descripcion = :descripcion")})
public class Llamadas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMEROTELF")
    private BigDecimal numerotelf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAHORA")
    @Temporal(TemporalType.DATE)
    private Date fechahora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "UBICACION")
    private String ubicacion;
    @Size(max = 255)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "ESTADO", referencedColumnName = "TIPOESTADO")
    @ManyToOne(optional = false)
    private Estado estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numerotelf")
    private Collection<Movilizaciones> movilizacionesCollection;

    public Llamadas() {
    }

    public Llamadas(BigDecimal numerotelf) {
        this.numerotelf = numerotelf;
    }

    public Llamadas(BigDecimal numerotelf, Date fechahora, String ubicacion) {
        this.numerotelf = numerotelf;
        this.fechahora = fechahora;
        this.ubicacion = ubicacion;
    }

    public BigDecimal getNumerotelf() {
        return numerotelf;
    }

    public void setNumerotelf(BigDecimal numerotelf) {
        this.numerotelf = numerotelf;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    
    public Collection<Movilizaciones> getMovilizacionesCollection() {
        return movilizacionesCollection;
    }

    public void setMovilizacionesCollection(Collection<Movilizaciones> movilizacionesCollection) {
        this.movilizacionesCollection = movilizacionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numerotelf != null ? numerotelf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Llamadas)) {
            return false;
        }
        Llamadas other = (Llamadas) object;
        if ((this.numerotelf == null && other.numerotelf != null) || (this.numerotelf != null && !this.numerotelf.equals(other.numerotelf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.llamadasemergencia.Llamadas[ numerotelf=" + numerotelf + " ]";
    }
    
    //--------------------------------------------------------------------------
    //METODOS INSERTAR, BORRAR MODIFICAR Y LEER(JULIAN)
    //--------------------------------------------------------------------------
    public static void insertarLlamada(int numeroTelf, Date fechaHora, String ubicacion, String estado, String descripcion) {
        entitymanger.getTransaction().begin();
        Llamadas llamada = new Llamadas();
        llamada.setNumeroTelf(numeroTelf);
        llamada.setFechaHora(fechaHora);
        llamada.setUbicacion(ubicacion);
        llamada.setEstado(estado);
        llamada.setDescripcion(descripcion);
        entitymanger.persist(llamada);
        entitymanger.getTransaction().commit();
        System.out.println("Llamada insertada correctamente.");
    }

    public static void borrarLlamada(int numeroTelf) {
        entitymanger.getTransaction().begin();
        Llamadas llamada = entitymanger.find(Llamadas.class, numeroTelf);
        if (llamada != null) {
            entitymanger.remove(llamada);
            System.out.println("Llamada eliminada correctamente.");
        } else {
            System.out.println("No se encontró una llamada con ese número de teléfono.");
        }
        entitymanger.getTransaction().commit();
    }

    public static void modificarLlamada(int numeroTelf, String nuevaUbicacion, String nuevoEstado, String nuevaDescripcion) {
        entitymanger.getTransaction().begin();
        Llamadas llamada = entitymanger.find(Llamadas.class, numeroTelf);
        if (llamada != null) {
            llamada.setUbicacion(nuevaUbicacion);
            llamada.setEstado(nuevoEstado);
            llamada.setDescripcion(nuevaDescripcion);
            System.out.println("Llamada actualizada correctamente.");
        } else {
            System.out.println("No se encontró una llamada con ese número de teléfono.");
        }
        entitymanger.getTransaction().commit();
    }

    public static void leerLlamadas() {
        Query query = entitymanger.createQuery("SELECT l FROM Llamadas l");
        List<Llamadas> llamadas = query.getResultList();
        for (Llamadas llamada : llamadas) {
            System.out.println("Número: " + llamada.getNumeroTelf());
            System.out.println("Fecha y Hora: " + llamada.getFechaHora());
            System.out.println("Ubicación: " + llamada.getUbicacion());
            System.out.println("Estado: " + llamada.getEstado());
            System.out.println("Descripción: " + llamada.getDescripcion());
            System.out.println("----------------------------");
        }
    }

    
    
}

