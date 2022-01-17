/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.filesService.modelos;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Brayan
 */
@Entity
@Table(name = "fil_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FilUsuarios.findAll", query = "SELECT f FROM FilUsuarios f")
    , @NamedQuery(name = "FilUsuarios.findById", query = "SELECT f FROM FilUsuarios f WHERE f.id = :id")
    , @NamedQuery(name = "FilUsuarios.findByNombre", query = "SELECT f FROM FilUsuarios f WHERE f.nombre = :nombre")
    , @NamedQuery(name = "FilUsuarios.findByApellidos", query = "SELECT f FROM FilUsuarios f WHERE f.apellidos = :apellidos")
    , @NamedQuery(name = "FilUsuarios.findByUsuario", query = "SELECT f FROM FilUsuarios f WHERE f.usuario = :usuario")
    , @NamedQuery(name = "FilUsuarios.findByPassword", query = "SELECT f FROM FilUsuarios f WHERE f.password = :password")
    , @NamedQuery(name = "FilUsuarios.findByFechaIngreso", query = "SELECT f FROM FilUsuarios f WHERE f.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "FilUsuarios.findByFechaActualizacion", query = "SELECT f FROM FilUsuarios f WHERE f.fechaActualizacion = :fechaActualizacion")})
public class FilUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;
    @JoinColumn(name = "FK_ESTADO", referencedColumnName = "ID")
    @ManyToOne
    private FilEstadoUsuario fkEstado;

    public FilUsuarios() {
    }

    public FilUsuarios(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public FilEstadoUsuario getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(FilEstadoUsuario fkEstado) {
        this.fkEstado = fkEstado;
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
        if (!(object instanceof FilUsuarios)) {
            return false;
        }
        FilUsuarios other = (FilUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service.filesService.modelos.FilUsuarios[ id=" + id + " ]";
    }
    
}
