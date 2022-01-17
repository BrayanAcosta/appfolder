/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.filesService.service;

import com.service.filesService.modelos.FilDocumentos;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minidev.json.JSONObject;

/**
 *
 * @author Brayan
 */
public interface IDocumentosService extends Serializable{
    
    List<FilDocumentos> consultarByCodeU(String ruta) throws Exception;
    
    JSONObject agregarCapeta(FilDocumentos documento) throws Exception;
    
    Optional<FilDocumentos> consultarByCodigoP(String codigo) throws Exception;
    
    JSONObject guardarArchivo(JSONObject body) throws Exception;
    
    JSONObject download(Integer id) throws Exception;
    
}
