/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.filesService.service;

import com.service.filesService.dao.IServidorDao;
import com.service.filesService.modelos.FilServidor;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brayan
 */
@Service
public class ServidorServiceImpl implements IServidorService {

    @Autowired
    private IServidorDao servidorDao;

    @Override
    public FilServidor consultar() throws Exception {
        return getServidorDao().consultar();
    }

    @Override
    public JSONObject guardar(FilServidor servidor) throws Exception {
        JSONObject obj = new JSONObject();
        try {
            getServidorDao().save(servidor);
            
            obj.put("rest", "200");
            obj.put("msg", "Servidor actualizado");
        } catch (Exception e) {
            obj.put("rest", "500");
            obj.put("msg", e.getMessage());
        }
        return obj;
    }

    public IServidorDao getServidorDao() {
        return servidorDao;
    }

    public void setServidorDao(IServidorDao servidorDao) {
        this.servidorDao = servidorDao;
    }

}
