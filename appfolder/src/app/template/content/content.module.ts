import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FilesComponent } from 'src/app/componentes/files/files.component';
import {DialogModule} from 'primeng/dialog';
import {ToastModule} from 'primeng/toast';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import { UsuariosComponent } from 'src/app/componentes/usuarios/usuarios.component';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import { ServidorComponent } from 'src/app/componentes/servidor/servidor.component';

const routes:Routes=[
  {path:"",redirectTo:"files"},
  {path:"files",component:FilesComponent},
  {path:"usuarios",component:UsuariosComponent},
  {path:"servidor",component:ServidorComponent}
];

@NgModule({
  declarations: [
    FilesComponent,
    UsuariosComponent,
    ServidorComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    FormsModule,
    NgbModule,
    DialogModule,
    ToastModule,
    ProgressSpinnerModule,
    ConfirmDialogModule
  ]
})
export class ContentModule { }
