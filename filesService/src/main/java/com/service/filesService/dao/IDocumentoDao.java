/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.filesService.dao;

import com.service.filesService.modelos.FilDocumentos;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Brayan
 */
public interface IDocumentoDao extends JpaRepository<FilDocumentos, Integer>{
    
    @Query(value = "select * from fil_documentos where RUTA=:ruta order by FK_TIPO asc",nativeQuery = true)
    List<FilDocumentos> listarByRuta(@Param("ruta") String ruta) throws Exception;
    
    @Query(value = "select * from fil_documentos where CODIGO_U=:cod and NOMBRE=:nom order by FK_TIPO asc",nativeQuery = true)
    FilDocumentos consultarByNombreAndCodigoU(@Param("cod") String code,@Param("nom") String nom) throws Exception;
    
    @Query(value = "select * from fil_documentos where CODIGO_U=:cod order by FK_TIPO asc",nativeQuery = true)
    List<FilDocumentos> listarByCodeU(@Param("cod") String ruta) throws Exception;
    
    @Query(value = "select * from fil_documentos where CODIGO_P=:cod",nativeQuery = true)
    Optional<FilDocumentos> consultarByCodigoP(@Param("cod") String cod) throws Exception;
    
    @Query(value = "select * from fil_documentos where ID=:id",nativeQuery = true)
    FilDocumentos consultarById(@Param("id") Integer id) throws Exception;
}
