import { EstadoUsuario } from "./estado-usuario";

export class Usuario {

    public id: number = 0;
    public nombre: string = "";
    public apellidos: string = "";
    public usuario: string = "";
    public password: string = "";
    public fechaIngreso = new Date();
    public fechaActualizacion = new Date();
    public fkEstado:EstadoUsuario=new EstadoUsuario();

}
