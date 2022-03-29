import { Imagem } from "../model/Imagem.model";
import { Injectable } from "../../../node_modules/@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "../../../node_modules/@angular/router";
import { GenericService } from "./generic.service";
import { SERVICE } from "../app.api";
import { AuthService } from "./auth.service";

@Injectable()
export class ImagemService extends GenericService<Imagem> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'imagem', authService)
    }

    getByIdSmall(id : number): Promise<Imagem>{
        return this.http.post(`${SERVICE}/imagem/getByIdSmall`, JSON.stringify(id))
                    .toPromise()
                    .catch(error=> {
                      if (error.status == 401){
                        console.log("Acesso não autorizado")
                        this.authService.expires()
                      } else{
                        console.log("Erro desconhecido")
                      }
                    })
                    .then(res => <Imagem> res)
                    .then(data => { 
                      this.authService.reloadSession()
                      return data;
                    })
    }

    getByIdMedium(id : number): Promise<Imagem>{
        return this.http.post(`${SERVICE}/imagem/getByIdMedium`, JSON.stringify(id))
                    .toPromise()
                    .catch(error=> {
                      if (error.status == 401){
                        console.log("Acesso não autorizado")
                        this.authService.expires()
                      } else{
                        console.log("Erro desconhecido")
                      }
                    })
                    .then(res => <Imagem> res)
                    .then(data => { return data; })
    }
}
