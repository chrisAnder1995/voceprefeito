import { Configuracao } from '../model/Configuracao.model';
import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';

@Injectable()
export class ConfiguracaoService extends GenericService<Configuracao> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'configuracao', authService)
      }

      // METHODS_SERVICE
}
