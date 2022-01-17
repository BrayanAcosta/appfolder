import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Header } from '../util/header';
import { Documentos } from '../modelos/documentos';
import { FileRequest } from '../modelos/file-request';

@Injectable({
  providedIn: 'root'
})
export class DocumentosService {

  

  head:Header=new Header();

  constructor(private http:HttpClient) { }

  cosultarByRuta(ruta:any):Observable<Documentos[]>{
    return this.http.get<Documentos[]>(this.head.url.concat("documentos/listar/ruta/").concat(ruta),{
      headers:this.head.header
    });
  }

  agregarCarpeta(folder:Documentos):Observable<any>{
    return this.http.post<any>(this.head.url.concat("documentos/save/folder"),folder,{
      headers:this.head.header
    });
  }

  consultarByCode(codigo:any):Observable<Documentos>{
    return this.http.get<Documentos>(this.head.url.concat("documentos/consultar/codigoP/").concat(codigo),{
      headers:this.head.header
    });
  }

  agregarDocumento(file:FileRequest):Observable<any>{
    return this.http.post<any>(this.head.url.concat("documentos/save/file"),file,{
      headers:this.head.header
    });
  }

  consultarDescarga(id:any):Observable<any>{
    return this.http.get<any>(this.head.url.concat("documentos/download/").concat(id),{
      headers:this.head.header
    });
  }

}
