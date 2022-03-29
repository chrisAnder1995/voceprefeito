import { ViolenciaTipo } from '../model/ViolenciaTipo.model';
import { Injectable } from "../../../node_modules/@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "../../../node_modules/@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';

@Injectable()
export class ViolenciaTipoService extends GenericService<ViolenciaTipo> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'violenciaTipo', authService)
      }

      getByOcorrencia(id : number): Promise<ViolenciaTipo[]>{
        return this.http.post(`${SERVICE}/${this.nomeClasse}/getByOcorrencia`, JSON.stringify(id))
                        .toPromise()
                        .catch(error=> {
                          if (error.status == 401){
                            console.log("Acesso não autorizado")
                            this.authService.expires()
                          } else{
                            console.log("Erro desconhecido")
                          }
                        })
                        .then(res => <ViolenciaTipo[]> res)
                        .then(data => { 
                          this.authService.reloadSession()
                          return data;
                        })
      }
}
