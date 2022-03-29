import { Droga } from '../model/Droga.model';
import { Injectable } from "../../../node_modules/@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "../../../node_modules/@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';

@Injectable()
export class DrogaService extends GenericService<Droga> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'droga', authService)
      }

      getByAgressor(id : number): Promise<Droga[]>{
        return this.http.post(`${SERVICE}/${this.nomeClasse}/getByAgressor`, JSON.stringify(id))
                        .toPromise()
                        .catch(error=> {
                          if (error.status == 401){
                            console.log("Acesso não autorizado")
                            this.authService.expires()
                          } else{
                            console.log("Erro desconhecido")
                          }
                        })
                        .then(res => <Droga[]> res)
                        .then(data => { 
                          this.authService.reloadSession()
                          return data;
                        })
      }
  
      getByMulher(id : number): Promise<Droga[]>{
        return this.http.post(`${SERVICE}/${this.nomeClasse}/getByMulher`, JSON.stringify(id))
                        .toPromise()
                        .catch(error=> {
                          if (error.status == 401){
                            console.log("Acesso não autorizado")
                            this.authService.expires()
                          } else{
                            console.log("Erro desconhecido")
                          }
                        })
                        .then(res => <Droga[]> res)
                        .then(data => { 
                          this.authService.reloadSession()
                          return data;
                        })
      }
}
