/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.filesService.service;

import com.service.filesService.dao.IUsuarioDao;
import com.service.filesService.modelos.FilEstadoUsuario;
import com.service.filesService.modelos.FilUsuarios;
import com.service.filesService.util.Hash;
import java.util.Date;
import java.util.List;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brayan
 */
@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;
    @Autowired
    private Hash hash = new Hash();

    @Override
    public JSONObject ingresar(JSONObject body) throws Exception {
        JSONObject obj = new JSONObject();
        try {
            if (body.get("user") == null) {
                obj.put("rest", "400");
                obj.put("msg", "Usuario Vacio");
                return obj;
            }

            if (body.get("user").toString().equals("")) {
                obj.put("rest", "400");
                obj.put("msg", "Usuario Vacio");
                return obj;
            }

            if (body.get("pass") == null) {
                obj.put("rest", "400");
                obj.put("msg", "Contraseña Vacia");
                return obj;
            }

            if (body.get("pass").toString().equals("")) {
                obj.put("rest", "400");
                obj.put("msg", "Contraseña Vacia");
                return obj;
            }

            FilUsuarios filUsuarios = getUsuarioDao().consultarByUser(body.get("user").toString().toUpperCase());
            if (filUsuarios != null) {
                if (filUsuarios.getFkEstado().getId() == 2) {
                    obj.put("rest", "400");
                    obj.put("msg", "El usuario se encuentra deshabilitado");
                    return obj;
                }
                String pass = getHash().encript(body.get("pass").toString());
                if (filUsuarios.getUsuario().equals(body.get("user").toString().toUpperCase()) && filUsuarios.getPassword().equals(pass)) {
                    obj.put("rest", "200");
                    obj.put("msg", "Ingresando");
                    obj.put("user", filUsuarios);
                } else {
                    if (!filUsuarios.getPassword().equals(pass)) {
                        obj.put("rest", "400");
                        obj.put("msg", "Contraseña Incorrecta");
                    }
                }
            } else {
                obj.put("rest", "400");
                obj.put("msg", "Contraseña Vacia");
                return obj;
            }

        } catch (Exception e) {
            obj.put("rest", "500");
            obj.put("msg", e.getMessage());
        }
        return obj;
    }

    @Override
    public JSONObject registrar(FilUsuarios usuario) throws Exception {
        JSONObject obj = new JSONObject();
        try {
            if (usuario.getNombre() == null) {
                obj.put("rest", "400");
                obj.put("msg", "Nombre vacio");
                return obj;
            }

            if (usuario.getNombre().equals("")) {
                obj.put("rest", "400");
                obj.put("msg", "Nombre vacio");
                return obj;
            }

            if (usuario.getUsuario() == null) {
                obj.put("rest", "400");
                obj.put("msg", "Usuario vacio");
                return obj;
            }

            if (usuario.getUsuario().equals("")) {
                obj.put("rest", "400");
                obj.put("msg", "Usuario vacio");
                return obj;
            }

            if (usuario.getPassword() == null) {
                obj.put("rest", "400");
                obj.put("msg", "Contraseña vacia");
                return obj;
            }

            if (usuario.getPassword().equals("")) {
                obj.put("rest", "400");
                obj.put("msg", "Contraseña vacia");
                return obj;
            }

            FilUsuarios filUsuarios = getUsuarioDao().consultarByUser(usuario.getUsuario());
            if (filUsuarios != null) {
                obj.put("rest", "400");
                obj.put("msg", "El nombre de usuario ya existe en el sistema");
                return obj;
            }

            usuario.setFechaIngreso(new Date());
            usuario.setFkEstado(new FilEstadoUsuario(1));

            String pass = getHash().encript(usuario.getPassword());
            usuario.setPassword(pass);

            getUsuarioDao().save(usuario);

            obj.put("rest", "200");
            obj.put("msg", "Usuario Registrado");

        } catch (Exception e) {
            obj.put("rest", "500");
            obj.put("msg", e.getMessage());
        }
        return obj;
    }

    @Override
    public List<FilUsuarios> listar() throws Exception {
        return getUsuarioDao().listar();
    }

    public IUsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(IUsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Hash getHash() {
        return hash;
    }

    public void setHash(Hash hash) {
        this.hash = hash;
    }

}
