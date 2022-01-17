import { HttpHeaders } from "@angular/common/http";

export class Header {

    public url:string="http://localhost:8104/v1/rest/api/";

    public header:HttpHeaders=new HttpHeaders({'Content-Type':  'application/json'});

}
