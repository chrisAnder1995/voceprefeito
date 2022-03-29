import { Agressor } from '../model/Agressor.model';
import { Injectable } from "../../../node_modules/@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "../../../node_modules/@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';

@Injectable()
export class AgressorService extends GenericService<Agressor> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'agressor', authService)
      }

      // METHODS_SERVICE
}
