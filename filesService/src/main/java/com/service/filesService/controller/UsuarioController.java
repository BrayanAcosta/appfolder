/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.filesService.controller;

import com.service.filesService.modelos.FilUsuarios;
import com.service.filesService.service.IUsuarioService;
import java.util.List;
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
@RequestMapping("/v1/rest/api/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/login")
    public JSONObject ingresar(@RequestBody JSONObject body) throws Exception {
        return getUsuarioService().ingresar(body);
    }
    
    @PostMapping("/save")
    public JSONObject save(@RequestBody FilUsuarios usuarios) throws Exception{
        return getUsuarioService().registrar(usuarios);
    }
    
    @GetMapping("/list")
    public List<FilUsuarios> listar() throws Exception{
        return getUsuarioService().listar();
    }

    public IUsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

}
