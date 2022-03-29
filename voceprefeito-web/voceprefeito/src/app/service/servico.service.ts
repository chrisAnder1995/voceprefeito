import { Servico } from '../model/Servico.model';
import { UsuarioEmail } from '../model/UsuarioEmail.model';
import { Injectable } from "../../../node_modules/@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "../../../node_modules/@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';

@Injectable()
export class ServicoService extends GenericService<Servico> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'servico', authService)
      }

      getByAtendimento(id : number): Promise<Servico[]>{
        return this.http.post(`${SERVICE}/${this.nomeClasse}/getByAtendimento`, JSON.stringify(id))
                        .toPromise()
                        .catch(error=> {
                          if (error.status == 401){
                            console.log("Acesso não autorizado")
                            this.authService.expires()
                          } else{
                            console.log("Erro desconhecido")
                          }
                        })
                        .then(res => <Servico[]> res)
                        .then(data => { 
                          this.authService.reloadSession()
                          return data;
                        })
      }

      sendEmail(usuario : UsuarioEmail){
        return this.http.post(`${SERVICE}/${this.nomeClasse}/sendEmail`, JSON.stringify(usuario))
                        .toPromise()
                        .catch(error=> {
                          if (error.status == 401){
                            console.log("Acesso não autorizado")
                            this.authService.expires()
                          } else{
                            console.log("Erro desconhecido")
                          }
                        })
                        .then(res => <UsuarioEmail[]> res)
                        .then(data => { 
                          return data;
                        })
      }

      getByExampleSimples(entity : Servico): Promise<Servico[]>{
        return this.http.post(`${SERVICE}/${this.nomeClasse}/getByExampleSimples`, JSON.stringify(entity))
                        .toPromise()
                        .catch(error=> {
                          if (error.status == 401){
                            console.log("Acesso não autorizado")
                            this.authService.expires()
                          } else{
                            console.log("Erro desconhecido")
                          }
                        })
                        .then(res => <Servico[]> res)
                        .then(data => { 
                          this.authService.reloadSession()
                          return data;
                        })
      }

    }
