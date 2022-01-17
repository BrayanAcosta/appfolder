/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.filesService.service;

import com.service.filesService.dao.IDocumentoDao;
import com.service.filesService.dao.IServidorDao;
import com.service.filesService.modelos.FilDocumentos;
import com.service.filesService.modelos.FilEstadoDocumento;
import com.service.filesService.modelos.FilServidor;
import com.service.filesService.modelos.FilTipoDocumento;
import com.service.filesService.modelos.FilUsuarios;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brayan
 */
@Service
public class DocumentoServiceImpl implements IDocumentosService {

    @Autowired
    private IDocumentoDao documentoDao;
    @Autowired
    private IServidorDao servidorDao;

    @Override
    public List<FilDocumentos> consultarByCodeU(String ruta) throws Exception {
        return getDocumentoDao().listarByCodeU(ruta);
    }

    @Override
    public JSONObject agregarCapeta(FilDocumentos documento) throws Exception {
        JSONObject obj = new JSONObject();
        FilDocumentos doc = new FilDocumentos();
        try {
            if (documento.getNombre().equals("")) {
                obj.put("rest", "400");
                obj.put("msg", "Nombre de la carpeta vacio");
                return obj;
            }

            FilServidor filServidor = getServidorDao().consultar();
            if (filServidor == null) {
                obj.put("rest", "400");
                obj.put("msg", "La ruta destino no esta configurada");
                return obj;
            }

            File folderRoot = new File(filServidor.getDireccionFuente());
            if (!folderRoot.exists()) {
                folderRoot.mkdir();
            }

            File folder = null;
            String dir = filServidor.getDireccionFuente();
            String code = "UB" + new java.util.Date().getTime();
            if (documento.getCodigoU().equals("")) {
                doc.setCodigoU(filServidor.getCarpetaRaiz());
                doc.setCodigoP(code);
                FilDocumentos filDocumentos = getDocumentoDao().consultarByNombreAndCodigoU(documento.getNombre(), filServidor.getCarpetaRaiz());
                if (filDocumentos != null) {
                    obj.put("rest", "400");
                    obj.put("msg", "Ya existe una carpeta con el nombre ingresado");
                    return obj;
                }
                dir += "\\" + documento.getNombre();
            } else {
                FilDocumentos filDocumentos = getDocumentoDao().consultarByNombreAndCodigoU(documento.getNombre(), documento.getCodigoU());
                if (filDocumentos != null) {
                    obj.put("rest", "400");
                    obj.put("msg", "Ya existe una carpeta con el nombre ingresado");
                    return obj;
                }
                dir += "\\" + documento.getRuta() + "\\" + documento.getNombre();
                doc.setCodigoU(documento.getCodigoU());
                doc.setCodigoP(code);
            }
            folder = new File(dir);
            folder.mkdir();

            doc.setNombre(folder.getName());
            if (!documento.getRuta().equals("")) {
                doc.setRuta(documento.getRuta() + "\\" + documento.getNombre());
            } else {
                doc.setRuta(documento.getNombre());
            }

            doc.setFkTipo(documento.getFkTipo());
            doc.setFechaRegistro(new Date());
            doc.setFkEstado(new FilEstadoDocumento(1));
            if (documento.getFkUsuario() != null) {
                doc.setFkUsuario(new FilUsuarios(documento.getFkUsuario().getId()));
            }
            getDocumentoDao().save(doc);

            obj.put("rest", "200");
            obj.put("msg", "Carpeta creada");
            return obj;

        } catch (Exception e) {
            obj.put("rest", "500");
            obj.put("msg", e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Optional<FilDocumentos> consultarByCodigoP(String codigo) throws Exception {
        return getDocumentoDao().consultarByCodigoP(codigo);
    }

    @Override
    public JSONObject guardarArchivo(JSONObject body) throws Exception {
        JSONObject obj = new JSONObject();
        DecimalFormat df = new DecimalFormat("0.00");

        float sizeKb = 1024.0f;
        float sizeMb = sizeKb * sizeKb;
        float sizeGb = sizeMb * sizeKb;
        float sizeTerra = sizeGb * sizeKb;

        try {

            FilServidor filServidor = getServidorDao().consultar();
            if (filServidor == null) {
                obj.put("rest", "400");
                obj.put("msg", "La ruta destino no esta configurada");
                return obj;
            }

            File folderRoot = new File(filServidor.getDireccionFuente());
            if (!folderRoot.exists()) {
                folderRoot.mkdir();
            }

            if (body.get("nombre") == null) {
                obj.put("rest", "400");
                obj.put("msg", "El nombre del documento esta vacio");
                return obj;
            }

            if (body.get("nombre").toString().equals("")) {
                obj.put("rest", "400");
                obj.put("msg", "El nombre del documento esta vacio");
                return obj;
            }

            if (body.get("peso") == null) {
                obj.put("rest", "400");
                obj.put("msg", "El peso del archivo se envio vacio");
                return obj;
            }

            if (body.get("peso").toString().equals("")) {
                obj.put("rest", "400");
                obj.put("msg", "El peso del archivo se envio vacio");
                return obj;
            }

            if (body.get("base") == null) {
                obj.put("rest", "400");
                obj.put("msg", "El documento no se envio");
                return obj;
            }

            boolean isDir = true;
            if (body.get("codigoU") == null) {
                isDir = false;
            } else if (body.get("codigoU").equals("")) {
                isDir = false;
            }

            String dir = filServidor.getDireccionFuente();
            String code = "UBF" + new java.util.Date().getTime();
            if (isDir == true) {
                FilDocumentos filDocumentos = getDocumentoDao().consultarByNombreAndCodigoU(body.get("nombre").toString(), body.get("codigoU").toString());
                if (filDocumentos != null) {
                    obj.put("rest", "400");
                    obj.put("msg", "Ya existe una documento con el mismo nombre");
                    return obj;
                }
                dir += "\\" + body.get("ruta").toString();
            } else {
                FilDocumentos filDocumentos = getDocumentoDao().consultarByNombreAndCodigoU(body.get("nombre").toString(), filServidor.getCarpetaRaiz());
                if (filDocumentos != null) {
                    obj.put("rest", "400");
                    obj.put("msg", "Ya existe una documento con el mismo nombre");
                    return obj;
                }

            }

            File file = new File(dir + "\\" + body.get("nombre").toString());
            Path path = Paths.get(file.getPath());

            byte[] decodedString = Base64.getDecoder().decode(body.get("base").toString().getBytes());

            if (path != null) {
                Files.write(path, decodedString);
            } else {
                obj.put("rest", "400");
                obj.put("msg", "El documento no se cargo correctamente");
                return obj;
            }

            FilDocumentos filDocumentos = new FilDocumentos();
            filDocumentos.setNombre(body.get("nombre").toString());
            if (isDir == true) {
                filDocumentos.setRuta(body.get("ruta").toString());
            }

            String peso = "";
            Integer size = Integer.parseInt(body.get("peso").toString());

            if (size < sizeMb) {
                peso = df.format(size / sizeKb) + " KB";
            } else if (size < sizeGb) {
                peso = df.format(size / sizeMb) + " MB";
            } else if (size < sizeTerra) {
                peso = df.format(size / sizeGb) + " GB";
            }

            filDocumentos.setPeso(peso);
            filDocumentos.setFkTipo(new FilTipoDocumento(2));
            filDocumentos.setFechaRegistro(new Date());
            filDocumentos.setFkEstado(new FilEstadoDocumento(1));
            if(body.get("usuario") != null){
                if(!body.get("usuario").equals("")){
                    Integer idUsuario=Integer.parseInt(body.get("usuario").toString());
                    filDocumentos.setFkUsuario(new FilUsuarios(idUsuario));
                }                
            }
            
            if (isDir == true) {
                filDocumentos.setCodigoU(body.get("codigoP").toString());
            } else {
                filDocumentos.setCodigoU(filServidor.getCarpetaRaiz());
            }
            filDocumentos.setCodigoP(code);
            filDocumentos.setFormato(body.get("formato").toString());

            getDocumentoDao().save(filDocumentos);
            obj.put("rest", "200");
            obj.put("msg", "Documento Ingresado");

        } catch (Exception e) {
            obj.put("rest", "500");
            obj.put("msg", e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public JSONObject download(Integer id) throws Exception {
        JSONObject obj = new JSONObject();
        try {
            FilServidor filServidor = getServidorDao().consultar();
            if (filServidor == null) {
                obj.put("rest", "400");
                obj.put("msg", "La ruta destino no esta configurada");
                return obj;
            }
            FilDocumentos filDocumentos = getDocumentoDao().consultarById(id);
            if (filDocumentos != null) {
                String dir = "";
                if (filDocumentos.getCodigoU().equals(filServidor.getCarpetaRaiz())) {
                    dir = filServidor.getDireccionFuente() + "\\" + filDocumentos.getNombre();
                } else {
                    dir = filServidor.getDireccionFuente() + "\\" + filDocumentos.getRuta() + "\\" + filDocumentos.getNombre();
                }
                File file = new File(dir);
                if (file.exists()) {
                    byte[] fileContent = Files.readAllBytes(file.toPath());
                    String base = Base64.getEncoder().encodeToString(fileContent);

                    obj.put("rest", "200");
                    obj.put("base", base);
                    obj.put("formato", filDocumentos.getFormato());
                    obj.put("name", filDocumentos.getNombre());
                } else {
                    obj.put("rest", "400");
                    obj.put("msg", "No se encontro la ruta del archivo");
                }
            } else {
                obj.put("rest", "400");
                obj.put("msg", "Error consultando la ruta del archivo");
            }
        } catch (Exception e) {
            obj.put("rest", "500");
            obj.put("msg", e.getMessage());
        }
        return obj;
    }

    public IDocumentoDao getDocumentoDao() {
        return documentoDao;
    }

    public void setDocumentoDao(IDocumentoDao documentoDao) {
        this.documentoDao = documentoDao;
    }

    public IServidorDao getServidorDao() {
        return servidorDao;
    }

    public void setServidorDao(IServidorDao servidorDao) {
        this.servidorDao = servidorDao;
    }

}
