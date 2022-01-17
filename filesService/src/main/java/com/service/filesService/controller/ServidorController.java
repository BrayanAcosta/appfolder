/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.filesService.controller;

import com.service.filesService.modelos.FilServidor;
import com.service.filesService.service.IServidorService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Brayan
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/v1/rest/api/servidor")
public class ServidorController {
    
    @Autowired
    private IServidorService servidorService;
    
    @GetMapping("/consultar")
    public FilServidor consultar() throws Exception{
        return getServidorService().consultar();
    }
    
    @PostMapping("/save")
    public JSONObject guardar(@RequestBody FilServidor servidor) throws Exception{
        return getServidorService().guardar(servidor);
    }

    public IServidorService getServidorService() {
        return servidorService;
    }

    public void setServidorService(IServidorService servidorService) {
        this.servidorService = servidorService;
    }
    
    
    
}
