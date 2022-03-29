import { AtendimentoServico } from '../model/AtendimentoServico.model';
import { Injectable } from "../../../node_modules/@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "../../../node_modules/@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';

@Injectable()
export class AtendimentoServicoService extends GenericService<AtendimentoServico> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'atendimentoServico', authService)
      }

      // METHODS_SERVICE
}
