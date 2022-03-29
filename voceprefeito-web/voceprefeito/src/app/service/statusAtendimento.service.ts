import { Status } from '../model/StatusAtendimento.model';
import { Pais } from '../model/Pais.model';
import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';

@Injectable()
export class StatusService extends GenericService<Status> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'status', authService)
      }

      // METHODS_SERVICE
}
