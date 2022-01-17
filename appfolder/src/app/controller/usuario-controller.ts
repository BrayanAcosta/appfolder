import { Injectable, ViewChild } from "@angular/core";
import { UsuariosComponent } from "../componentes/usuarios/usuarios.component";
import { Usuario } from "../modelos/usuario";
import { UsuarioService } from "../servicios/usuario.service";

@Injectable({
    providedIn: 'any'
})
export class UsuarioController {

    constructor(private usuario: UsuarioService) { }

    init(view: UsuariosComponent){
        this.usuario.listar().subscribe(resp=>{
            view.listaUsuario=resp;
        });
    }

    ingresar(view: UsuariosComponent) {

        if (view.usuario.password !== view.pass) {
            view.msg = "Las contraseña no coinciden";
            view.showMessageError();
            return;
        }

        this.usuario.registrar(view.usuario).subscribe(resp => {
            if (resp.rest === "200") {
                view.usuario=new Usuario();
                view.pass="";
                view.msg = resp.msg;
                this.usuario.listar().subscribe(resp=>{
                    view.listaUsuario=resp;
                });
                view.showMessageSuccess();
            } else {
                view.msg = resp.msg;
                view.showMessageError();
            }
        }, err => {
            view.msg = err;
            view.showMessageError();
        });
    }

}
