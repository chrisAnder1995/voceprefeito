import { OrientacaoSexual } from '../model/OrientacaoSexual.model';
import { Injectable } from "../../../node_modules/@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "../../../node_modules/@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';

@Injectable()
export class OrientacaoSexualService extends GenericService<OrientacaoSexual> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'orientacaoSexual', authService)
      }

      // METHODS_SERVICE
}
