import { Ocorrencia } from '../model/Ocorrencia.model';
import { Injectable } from "../../../node_modules/@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "../../../node_modules/@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';

@Injectable()
export class OcorrenciaService extends GenericService<Ocorrencia> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'ocorrencia', authService)
      }

      // METHODS_SERVICE
}
