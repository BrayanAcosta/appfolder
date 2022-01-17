/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.filesService.service;

import com.service.filesService.modelos.FilServidor;
import java.io.Serializable;
import net.minidev.json.JSONObject;

/**
 *
 * @author Brayan
 */
public interface IServidorService extends Serializable{
    
    FilServidor consultar() throws Exception;
    
    JSONObject guardar(FilServidor servidor) throws Exception;
    
}
