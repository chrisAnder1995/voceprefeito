import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { Injectable as Injectable_1 } from "@angular/core";
import { UserGroups } from "../model/UserGroups.model";
import { SERVICE } from '../app.api';

@Injectable_1()
@Injectable()
export class UserGroupsService extends GenericService<UserGroups> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'userGroup', authService)
      }

      // METHODS_SERVICE
}
