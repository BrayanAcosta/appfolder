import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../modelos/usuario';
import { Header } from '../util/header';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  head: Header = new Header();

  constructor(private http: HttpClient) { }

  login(usuario: any): Observable<any> {
    return this.http.post<any>(this.head.url.concat("usuario/login"), usuario, {
      headers: this.head.header
    });
  }

  registrar(usuario:any):Observable<any>{
    return this.http.post<any>(this.head.url.concat("usuario/save"),usuario,{
      headers:this.head.header
    });
  }

  listar():Observable<Usuario[]>{
    return this.http.get<Usuario[]>(this.head.url.concat("usuario/list"),{
      headers:this.head.header
    })
  }

}
