import { Injectable } from "@angular/core";
import { LoginComponent } from "../componentes/login/login.component";
import { UsuarioService } from "../servicios/usuario.service";

@Injectable({
    providedIn: 'any'
})
export class LoginController {

    constructor(private login: UsuarioService) { }


    ingresar(view: LoginComponent) {
        const usuario = {
            user: view.user,
            pass: view.pass
        };
        this.login.login(usuario).subscribe(resp => {
            if (resp.rest === "200") {

                let usuario = btoa(JSON.stringify(resp.user));
                sessionStorage.setItem("usuario", usuario);


                view.msg = resp.msg;
                view.showSuccess();
                view.router.navigate(["/content/files"]);
            } else {
                view.msg = resp.msg;
                view.showError();
            }
        }, err => {
            view.msg = err;
            view.showError;
        });
    }

}
