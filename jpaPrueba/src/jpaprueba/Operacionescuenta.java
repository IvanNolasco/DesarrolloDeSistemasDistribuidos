/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaprueba;

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

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "operacionescuenta")
@NamedQueries({
    @NamedQuery(name = "Operacionescuenta.findAll", query = "SELECT o FROM Operacionescuenta o")
    , @NamedQuery(name = "Operacionescuenta.findByIdoperacionescliente", query = "SELECT o FROM Operacionescuenta o WHERE o.idoperacionescliente = :idoperacionescliente")
    , @NamedQuery(name = "Operacionescuenta.findByFechaoperacion", query = "SELECT o FROM Operacionescuenta o WHERE o.fechaoperacion = :fechaoperacion")
    , @NamedQuery(name = "Operacionescuenta.findByTipodemovimiento", query = "SELECT o FROM Operacionescuenta o WHERE o.tipodemovimiento = :tipodemovimiento")
    , @NamedQuery(name = "Operacionescuenta.findByCantidad", query = "SELECT o FROM Operacionescuenta o WHERE o.cantidad = :cantidad")})
public class Operacionescuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idoperacionescliente")
    private Long idoperacionescliente;
    @Column(name = "fechaoperacion")
    @Temporal(TemporalType.DATE)
    private Date fechaoperacion;
    @Column(name = "tipodemovimiento")
    private Character tipodemovimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @JoinColumn(name = "idcuenta", referencedColumnName = "idcuenta")
    @ManyToOne
    private Cuenta idcuenta;

    public Operacionescuenta() {
    }

    public Operacionescuenta(Long idoperacionescliente) {
        this.idoperacionescliente = idoperacionescliente;
    }

    public Long getIdoperacionescliente() {
        return idoperacionescliente;
    }

    public void setIdoperacionescliente(Long idoperacionescliente) {
        this.idoperacionescliente = idoperacionescliente;
    }

    public Date getFechaoperacion() {
        return fechaoperacion;
    }

    public void setFechaoperacion(Date fechaoperacion) {
        this.fechaoperacion = fechaoperacion;
    }

    public Character getTipodemovimiento() {
        return tipodemovimiento;
    }

    public void setTipodemovimiento(Character tipodemovimiento) {
        this.tipodemovimiento = tipodemovimiento;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Cuenta getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(Cuenta idcuenta) {
        this.idcuenta = idcuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idoperacionescliente != null ? idoperacionescliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operacionescuenta)) {
            return false;
        }
        Operacionescuenta other = (Operacionescuenta) object;
        if ((this.idoperacionescliente == null && other.idoperacionescliente != null) || (this.idoperacionescliente != null && !this.idoperacionescliente.equals(other.idoperacionescliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaprueba.Operacionescuenta[ idoperacionescliente=" + idoperacionescliente + " ]";
    }
    
}
