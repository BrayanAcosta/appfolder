import { identifierName } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { BLOCK_MARKER } from '@angular/localize/src/utils';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { map } from 'rxjs';
import { FilesController } from 'src/app/controller/files-controller';
import { Documentos } from 'src/app/modelos/documentos';
import { FileRequest } from 'src/app/modelos/file-request';
import { Servidor } from 'src/app/modelos/servidor';

@Component({
  selector: 'app-files',
  templateUrl: './files.component.html',
  styleUrls: ['./files.component.css'],
  providers: [MessageService]
})
export class FilesComponent implements OnInit {

  listaDocumentos: Documentos[] = [];
  servidor: Servidor = new Servidor();

  rutaActual: Documentos = new Documentos();

  viewModalCarpeta: boolean = false;
  msg: string = "";

  carpeta: Documentos = new Documentos();

  viewModalArchivo: boolean = false;

  imageBlob: Blob = new Blob();
  imagen: string = "";

  createFile: FileRequest = new FileRequest();

  idDocumento: number = 0;

  viewModalLoad: boolean = false;

  constructor(
    private filesController: FilesController,
    private messageService: MessageService,
    public router: Router
  ) { }

  ngOnInit(): void {
    this.filesController.init(this);
  }

  crearCapeta() {
    this.filesController.crearCarpeta(this);
  }

  openFolder(index: any) {

    if (this.listaDocumentos[index].fkTipo.id === 1) {
      this.rutaActual = this.listaDocumentos[index];
      this.filesController.openFolder(this);
    }

  }

  backFolder() {
    this.filesController.backFolder(this);
  }

  changeFile(e: any) {
    if (this.imagen !== undefined || this.imagen !== null) {
      let size=e.target.files[0].size;
      if (size <= 20000000) {
        this.imageBlob = e.target.files[0];
        this.createFile.nombre = e.target.files[0].name;
        this.createFile.peso = e.target.files[0].size;
        this.createFile.formato = e.target.files[0].type;
        this.filesController.changeImage(this);
      } else {
        this.createFile.nombre = "";
        this.msg = "Tamaño del archivo superado";
        this.imagen="";
        this.showError();
      }

    } else {
      this.createFile.nombre = "";
    }
  }

  cargarDocumento() {
    this.filesController.cargarDocumento(this);
  }

  showSuccess() {
    this.messageService.add({ severity: 'success', summary: 'Exito', detail: this.msg });
  }

  showError() {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: this.msg });
  }

  controlModalCarpeta() {
    if (this.viewModalCarpeta == true) {
      this.viewModalCarpeta = false;
    } else {
      this.viewModalCarpeta = true;
    }
  }

  controlModalArchivo() {
    if (this.viewModalArchivo == true) {
      this.viewModalArchivo = false;
    } else {
      this.viewModalArchivo = true;
    }
  }

  controlModalLoad() {
    if (this.viewModalLoad === true) {
      this.viewModalLoad = false;
    } else {
      this.viewModalLoad = true;
    }
  }

  cosultarDescarga(id: any) {
    this.idDocumento = id;
    this.filesController.consultarDescarga(this);
  }

  downloadFile(fileName: string, data: any, fileFormat: string): void {
    const linkSource = 'data:' + fileFormat + ';base64,' + data;
    const downloadLink = document.createElement("a");
    downloadLink.href = linkSource;
    downloadLink.download = fileName;
    downloadLink.click();
  }


}
