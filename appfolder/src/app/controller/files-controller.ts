import { Injectable } from "@angular/core";
import { FilesComponent } from "../componentes/files/files.component";
import { Documentos } from "../modelos/documentos";
import { FileRequest } from "../modelos/file-request";
import { DocumentosService } from "../servicios/documentos.service";
import { ServidorService } from "../servicios/servidor.service";

@Injectable({
    providedIn: 'any'
})
export class FilesController {

    constructor(
        private documentos: DocumentosService,
        private servidor: ServidorService
    ) { }
    
    usu: any = JSON.parse(atob(sessionStorage.getItem("usuario") || ""));

    init(view: FilesComponent) {
        this.servidor.getServidor().subscribe(resp => {
            view.servidor = resp;
            this.documentos.cosultarByRuta(view.servidor.carpetaRaiz).subscribe(resp => {
                view.listaDocumentos = resp;
            });
        })
    }

    crearCarpeta(view: FilesComponent) {

        view.carpeta.fkTipo.id = 1;
        view.carpeta.codigoU = view.rutaActual.codigoP;
        view.carpeta.ruta = view.rutaActual.ruta;
        view.carpeta.fkUsuario.id=this.usu.id;
        view.controlModalLoad();
        this.documentos.agregarCarpeta(view.carpeta).subscribe(resp => {
            view.controlModalLoad();
            if (resp.rest === "200") {
                view.msg = resp.msg;
                view.showSuccess();
                this.cargarDocumentos(view);
                view.carpeta = new Documentos();
                view.controlModalCarpeta();
            } else {
                view.msg = resp.msg;
                view.showError();
            }
        }, err => {
            view.controlModalLoad();
            view.msg = err;
            view.showError();
        });
    }

    cargarDocumentos(view: FilesComponent) {
        if (view.rutaActual.codigoU === null || view.rutaActual.codigoU === undefined || view.rutaActual.codigoU === "") {
            this.servidor.getServidor().subscribe(resp => {
                view.servidor = resp;
                this.documentos.cosultarByRuta(view.servidor.carpetaRaiz).subscribe(resp => {
                    view.listaDocumentos = resp;
                });
            })
        } else {
            this.documentos.cosultarByRuta(view.rutaActual.codigoP).subscribe(resp => {
                view.listaDocumentos = resp;
            });
        }
    }

    openFolder(view: FilesComponent) {
        view.controlModalLoad();
        this.documentos.cosultarByRuta(view.rutaActual.codigoP).subscribe(resp => {
            view.listaDocumentos = resp;
            view.controlModalLoad();
        });
    }

    backFolder(view: FilesComponent) {
        if (view.rutaActual.codigoU !== "") {
            view.controlModalLoad();
            if (view.rutaActual.codigoU === view.servidor.carpetaRaiz) {
                view.rutaActual = new Documentos();
                this.documentos.cosultarByRuta(view.servidor.carpetaRaiz).subscribe(resp => {
                    view.listaDocumentos = resp;
                    view.controlModalLoad();
                });
            } else {
                this.documentos.consultarByCode(view.rutaActual.codigoU).subscribe(resp => {
                    view.rutaActual = resp;
                    view.controlModalLoad();
                    this.documentos.cosultarByRuta(resp.codigoP).subscribe(resp => {
                        view.listaDocumentos = resp;
                    },err=>{
                        view.controlModalLoad();
                    });
                },err=>{
                    view.controlModalLoad();
                });
            }
        }

    }

    changeImage(view: FilesComponent) {
        view.controlModalLoad();
        let reader = new FileReader();
        if (view.imagen !== undefined || view.imagen !== null) {
            reader.readAsDataURL(view.imageBlob);
            reader.onload = function (e) {
                if (reader.result !== null) {
                    view.createFile.base = reader.result.toString().split(",")[1];
                    view.controlModalLoad();
                }
            }
            reader.onerror = function (e) {
                view.createFile.base = "";
                view.controlModalLoad();
            }
        }else{
            view.controlModalLoad();
        }
    }

    cargarDocumento(view: FilesComponent) {
        view.createFile.carpeta = view.rutaActual.ruta;
        view.createFile.codigoU = view.rutaActual.codigoU;
        view.createFile.ruta = view.rutaActual.ruta;
        view.createFile.codigoP = view.rutaActual.codigoP;
        view.createFile.usuario = this.usu.id;
        view.controlModalLoad();
        this.documentos.agregarDocumento(view.createFile).subscribe(resp => {
            view.controlModalLoad();
            if (resp.rest === "200") {
                view.msg = resp.msg;
                view.showSuccess();
                this.cargarDocumentos(view);
                view.createFile = new FileRequest();
                view.imagen = "";
                view.controlModalArchivo();
            } else {
                view.msg = resp.msg;
                view.showError();
            }
        }, err => {
            view.controlModalLoad();
            view.msg = err;
            view.showError();
        });
    }

    consultarDescarga(view: FilesComponent) {
        view.controlModalLoad();
        this.documentos.consultarDescarga(view.idDocumento).subscribe(resp => {
            view.controlModalLoad();
            if (resp.rest === "200") {
                view.downloadFile(resp.name, resp.base,resp.formato);
                view.msg = resp.msg;
                view.showSuccess();
            } else {
                view.msg = resp.msg;
                view.showError();
            }
        }, err => {
            view.controlModalLoad();
            view.msg = err;
            view.showError();
        });
    }

}
