/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.filesService.modelos;

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
 * @author Brayan
 */
@Entity
@Table(name = "fil_documentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FilDocumentos.findAll", query = "SELECT f FROM FilDocumentos f")
    , @NamedQuery(name = "FilDocumentos.findById", query = "SELECT f FROM FilDocumentos f WHERE f.id = :id")
    , @NamedQuery(name = "FilDocumentos.findByNombre", query = "SELECT f FROM FilDocumentos f WHERE f.nombre = :nombre")
    , @NamedQuery(name = "FilDocumentos.findByRuta", query = "SELECT f FROM FilDocumentos f WHERE f.ruta = :ruta")
    , @NamedQuery(name = "FilDocumentos.findByPeso", query = "SELECT f FROM FilDocumentos f WHERE f.peso = :peso")
    , @NamedQuery(name = "FilDocumentos.findByFechaRegistro", query = "SELECT f FROM FilDocumentos f WHERE f.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "FilDocumentos.findByCodigoU", query = "SELECT f FROM FilDocumentos f WHERE f.codigoU = :codigoU")
    , @NamedQuery(name = "FilDocumentos.findByCodigoP", query = "SELECT f FROM FilDocumentos f WHERE f.codigoP = :codigoP")})
public class FilDocumentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "RUTA")
    private String ruta;
    @Column(name = "PESO")
    private String peso;
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "CODIGO_U")
    private String codigoU;
    @Column(name = "CODIGO_P")
    private String codigoP;
    @JoinColumn(name = "FK_ESTADO", referencedColumnName = "ID")
    @ManyToOne
    private FilEstadoDocumento fkEstado;
    @JoinColumn(name = "FK_TIPO", referencedColumnName = "ID")
    @ManyToOne
    private FilTipoDocumento fkTipo;
    @JoinColumn(name = "FK_USUARIO", referencedColumnName = "ID")
    @ManyToOne
    private FilUsuarios fkUsuario;
    @Column(name = "FORMATO")
    private String formato;

    public FilDocumentos() {
    }

    public FilDocumentos(Integer id) {
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCodigoU() {
        return codigoU;
    }

    public void setCodigoU(String codigoU) {
        this.codigoU = codigoU;
    }

    public String getCodigoP() {
        return codigoP;
    }

    public void setCodigoP(String codigoP) {
        this.codigoP = codigoP;
    }

    public FilEstadoDocumento getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(FilEstadoDocumento fkEstado) {
        this.fkEstado = fkEstado;
    }

    public FilTipoDocumento getFkTipo() {
        return fkTipo;
    }

    public void setFkTipo(FilTipoDocumento fkTipo) {
        this.fkTipo = fkTipo;
    }

    public FilUsuarios getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(FilUsuarios fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
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
        if (!(object instanceof FilDocumentos)) {
            return false;
        }
        FilDocumentos other = (FilDocumentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service.filesService.modelos.FilDocumentos[ id=" + id + " ]";
    }
    
}
