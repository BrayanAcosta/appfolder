import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { UsuarioController } from 'src/app/controller/usuario-controller';
import { Usuario } from 'src/app/modelos/usuario';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css'],
  providers: [ConfirmationService, MessageService]
})
export class UsuariosComponent implements OnInit {

  msg:string="";
  usuario:Usuario=new Usuario();
  pass:string="";
  listaUsuario:Usuario[]=[];

  constructor(
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private usuarioController:UsuarioController
  ) { }

  ngOnInit(): void {
    this.usuarioController.init(this);
  }

  agregarUsuario() {
    this.confirmationService.confirm({
      message: 'Desea agregar el usuario?',
      header: 'Confirmar',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.usuarioController.ingresar(this);
      },
      reject: () => {

      }
    });
  }

  showMessageSuccess(){
    this.messageService.add({severity:'success', summary:'Rejected', detail:this.msg});
  }

  showMessageError(){
    this.messageService.add({severity:'error', summary:'Rejected', detail:this.msg});
  }

}
