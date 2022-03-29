import { Solicitacao } from './../model/Solicitacao.model';
import { Pais } from '../model/Pais.model';
import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';

@Injectable()
export class SolicitacaoService extends GenericService<Solicitacao> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'solicitacao', authService)
      }


     
}
