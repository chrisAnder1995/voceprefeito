import { OcorrenciaViolencia } from '../model/OcorrenciaViolencia.model';
import { Injectable } from "../../../node_modules/@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "../../../node_modules/@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';

@Injectable()
export class OcorrenciaViolenciaService extends GenericService<OcorrenciaViolencia> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'ocorrenciaViolencia', authService)
      }

      // METHODS_SERVICE
}
