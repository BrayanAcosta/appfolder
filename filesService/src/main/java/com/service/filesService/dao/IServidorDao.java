/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.filesService.dao;

import com.service.filesService.modelos.FilServidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Brayan
 */
public interface IServidorDao extends JpaRepository<FilServidor, Integer>{
    
    @Query(value = "select * from fil_servidor",nativeQuery = true)
    FilServidor consultar() throws Exception;
    
}
