/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.filesService.service;

import com.service.filesService.modelos.FilUsuarios;
import java.io.Serializable;
import java.util.List;
import net.minidev.json.JSONObject;

/**
 *
 * @author Brayan
 */
public interface IUsuarioService extends Serializable{
    
    JSONObject ingresar(JSONObject body) throws Exception;
    
    JSONObject registrar(FilUsuarios usuario) throws Exception;
    
    List<FilUsuarios> listar() throws Exception;
}
