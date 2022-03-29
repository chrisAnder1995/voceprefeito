import { Pais } from '../model/Pais.model';
import { Injectable } from "../../../node_modules/@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "../../../node_modules/@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';

@Injectable()
export class PaisService extends GenericService<Pais> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'pais', authService)
      }

      // METHODS_SERVICE
}
