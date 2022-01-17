import { EstadoDocumento } from "./estado-documento";
import { TipoDocumento } from "./tipo-documento";
import { Usuario } from "./usuario";

export class Documentos {

    public id: number=0;
    public nombre: string="";
    public ruta: string="";
    public peso: string="";
    public fechaRegistro: Date=new Date();
    public codigoU: string="";
    public codigoP: string="";
    public fkEstado:EstadoDocumento=new EstadoDocumento();
    public fkTipo:TipoDocumento=new TipoDocumento();
    public fkUsuario:Usuario=new Usuario();
    public formato:string="";

}
