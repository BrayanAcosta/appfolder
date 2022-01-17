import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { LoginController } from 'src/app/controller/login-controller';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [MessageService]
})
export class LoginComponent implements OnInit {

  user: string = "";
  pass: string = "";
  msg: string = "";

  constructor(
    private loginController: LoginController,
    private messageService: MessageService,
    public router: Router
  ) { }

  ngOnInit(): void {
  }

  ingresar() {
    this.loginController.ingresar(this);
  }

  showSuccess() {
    this.messageService.add({ severity: 'success', summary: 'Exito', detail: this.msg });
  }

  showError() {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: this.msg });
  }

}
