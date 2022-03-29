import { Bairro } from '../model/Bairro.model';
import { Injectable } from "../../../node_modules/@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "../../../node_modules/@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';

@Injectable()
export class BairroService extends GenericService<Bairro> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'bairro', authService)
      }

      getAllPublic(): Promise<Bairro[]> {
        return this.http.post(`${SERVICE}/${this.nomeClasse}/getAllPublic`, JSON.stringify(""))
                        .toPromise()
                        .catch(error=> {
                          if (error.status == 401){
                            console.log("Acesso não autorizado")
                            this.authService.expires()
                          } else{
                            console.log("Erro desconhecido")
                          }
                        })
                        .then(res => <Bairro[]> res )
                        .then(data => { 
                          return data;
                        })
                        
      }

      getByExamplePublic(entity : Bairro): Promise<Bairro[]>{
        return this.http.post(`${SERVICE}/${this.nomeClasse}/getByExamplePublic`, JSON.stringify(entity))
                        .toPromise()
                        .catch(error=> {
                          if (error.status == 401){
                            console.log("Acesso não autorizado")
                            this.authService.expires()
                          } else{
                            console.log("Erro desconhecido")
                          }
                        })
                        .then(res => <Bairro[]> res )
                        .then(data => { 
                          return data;
                        })
                        
      }
}
