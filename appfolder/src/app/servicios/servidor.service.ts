import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Servidor } from '../modelos/servidor';
import { Header } from '../util/header';

@Injectable({
  providedIn: 'root'
})
export class ServidorService {

  head:Header=new Header();

  constructor(private http:HttpClient) { }

  getServidor():Observable<Servidor>{
    return this.http.get<Servidor>(this.head.url.concat("servidor/consultar"),{
      headers:this.head.header
    });
  }

  guardar(servidor:any):Observable<any>{
    return this.http.post<any>(this.head.url.concat("servidor/save"),servidor,{
      headers:this.head.header
    });
  }
}
