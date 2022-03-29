import { Pais } from '../model/Pais.model';
import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';
import { MotivoSolicitacao } from '../model/MotivoSolicitacao.model';

@Injectable()
export class MotivoSolicitacaoService extends GenericService<MotivoSolicitacao> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'motivoSolicitacao', authService)
      }


      getAllPublic(): Promise<MotivoSolicitacao[]> {
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
                        .then(res => <MotivoSolicitacao[]> res )
                        .then(data => { 
                          return data;
                        })
                        
      }
      // METHODS_SERVICE
}
