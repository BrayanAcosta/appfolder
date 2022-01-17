/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.filesService.dao;

import com.service.filesService.modelos.FilUsuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Brayan
 */
public interface IUsuarioDao extends JpaRepository<FilUsuarios, Integer>{
 
    @Query(value = "select * from fil_usuarios where USUARIO=:uss",nativeQuery = true)
    FilUsuarios consultarByUser(@Param("uss") String user) throws Exception;
    
    @Query(value = "select * from fil_usuarios",nativeQuery = true)
    List<FilUsuarios> listar() throws Exception;
    
    @Query(value = "select * from fil_usuarios where USUARIO=:ussr",nativeQuery = true)
    FilUsuarios consultarByUsuario(@Param("ussr") String usuario) throws Exception;
}