/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.filesService.controller;

import com.service.filesService.modelos.FilDocumentos;
import com.service.filesService.service.IDocumentosService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/v1/rest/api/documentos")
public class DocumentosController {

    @Autowired
    private IDocumentosService documentosService;

    @GetMapping("/listar/ruta/{ruta}")
    public List<FilDocumentos> listarByRuta(@PathVariable("ruta") String ruta) throws Exception {
        return getDocumentosService().consultarByCodeU(ruta);
    }
    
    @PostMapping("/save/folder")
    public JSONObject saveFolder(@RequestBody FilDocumentos filDocumentos) throws Exception{
        return getDocumentosService().agregarCapeta(filDocumentos);
    }
    
    @GetMapping("/consultar/codigoP/{cod}")
    public Optional<FilDocumentos> consultarByCodigoP(@PathVariable("cod") String cod) throws Exception{
        return getDocumentosService().consultarByCodigoP(cod);
    }
    
    @PostMapping("/save/file")
    public JSONObject saveFile(@RequestBody JSONObject body) throws Exception{
        return getDocumentosService().guardarArchivo(body);
    }
    
    @GetMapping("/download/{id}")
    public JSONObject download(@PathVariable("id") Integer id) throws Exception{
        return getDocumentosService().download(id);
    }

    public IDocumentosService getDocumentosService() {
        return documentosService;
    }

    public void setDocumentosService(IDocumentosService documentosService) {
        this.documentosService = documentosService;
    }

}
