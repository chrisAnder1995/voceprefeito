import { Pais } from '../model/Pais.model';
import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';
import { Politica } from '../model/Politica.model';

@Injectable()
export class PoliticaService extends GenericService<Politica> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'politica', authService)
      }

      // METHODS_SERVICE
}
