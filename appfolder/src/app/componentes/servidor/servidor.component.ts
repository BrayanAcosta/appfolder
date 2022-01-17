import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { ServidorController } from 'src/app/controller/servidor-controller';
import { Servidor } from 'src/app/modelos/servidor';

@Component({
  selector: 'app-servidor',
  templateUrl: './servidor.component.html',
  styleUrls: ['./servidor.component.css'],
  providers:[MessageService]
})
export class ServidorComponent implements OnInit {

  servidor:Servidor=new Servidor();
  msg:string="";

  constructor(
    private servidorController:ServidorController,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.servidorController.init(this);
  }

  guardar(){
    this.servidorController.guardar(this);
  }

  showMessageSuccess(){
    this.messageService.add({severity:'success', summary:'Rejected', detail:this.msg});
  }

  showMessageError(){
    this.messageService.add({severity:'error', summary:'Rejected', detail:this.msg});
  }

}
