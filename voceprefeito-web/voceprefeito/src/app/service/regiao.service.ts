import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { Regiao } from '../model/Regiao.model';

@Injectable()
export class RegiaoService extends GenericService<Regiao> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'regiao', authService)
      }

      // METHODS_SERVICE
}
