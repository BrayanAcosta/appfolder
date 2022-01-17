/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.filesService.modelos;

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
 * @author Brayan
 */
@Entity
@Table(name = "fil_servidor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FilServidor.findAll", query = "SELECT f FROM FilServidor f")
    , @NamedQuery(name = "FilServidor.findById", query = "SELECT f FROM FilServidor f WHERE f.id = :id")
    , @NamedQuery(name = "FilServidor.findByDireccionFuente", query = "SELECT f FROM FilServidor f WHERE f.direccionFuente = :direccionFuente")
    , @NamedQuery(name = "FilServidor.findByCarpetaRaiz", query = "SELECT f FROM FilServidor f WHERE f.carpetaRaiz = :carpetaRaiz")})
public class FilServidor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DIRECCION_FUENTE")
    private String direccionFuente;
    @Column(name = "CARPETA_RAIZ")
    private String carpetaRaiz;

    public FilServidor() {
    }

    public FilServidor(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccionFuente() {
        return direccionFuente;
    }

    public void setDireccionFuente(String direccionFuente) {
        this.direccionFuente = direccionFuente;
    }

    public String getCarpetaRaiz() {
        return carpetaRaiz;
    }

    public void setCarpetaRaiz(String carpetaRaiz) {
        this.carpetaRaiz = carpetaRaiz;
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
        if (!(object instanceof FilServidor)) {
            return false;
        }
        FilServidor other = (FilServidor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service.filesService.modelos.FilServidor[ id=" + id + " ]";
    }
    
}
