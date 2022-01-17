import { Injectable } from "@angular/core";
import { ServidorComponent } from "../componentes/servidor/servidor.component";
import { ServidorService } from "../servicios/servidor.service";

@Injectable({
    providedIn: 'any'
})
export class ServidorController {

    constructor(
        private servidor: ServidorService
    ) { }

    init(view: ServidorComponent) {
        this.servidor.getServidor().subscribe(resp => {
            view.servidor = resp;
        });
    }

    guardar(view: ServidorComponent) {
        this.servidor.guardar(view.servidor).subscribe(resp => {
            if (resp.rest === "200") {
                this.servidor.getServidor().subscribe(resp=>{
                    view.servidor=resp;
                });
                view.msg = resp.msg;
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
