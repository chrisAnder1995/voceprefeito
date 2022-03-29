import { HttpClient } from '@angular/common/http';
import { AppService } from './../app.service';
import { SERVICE } from '../app.api';
import { Injectable } from '@angular/core';
import { Usuario } from '../model/Usuario.model';

import * as jwtDecode from 'jwt-decode';
import * as moment from 'moment';
import { Router } from '@angular/router';

@Injectable()
export class AuthService {

    constructor(private appService : AppService, private http : HttpClient, private router : Router){

    }

    public setSession(authResult) {
        const payload : any = jwtDecode(authResult)
        const expiresAt = moment.unix(payload.exp);
        
        localStorage.setItem('token', authResult);
        localStorage.setItem('expires_at', JSON.stringify(expiresAt.valueOf()));
    }

    async reloadSession(){
        await this.reloadAuthService().then(jwt => {})
    }

    private reloadAuthService(){
        let usuario: Usuario = JSON.parse(this.appService.getFromLocal('usuario'))

        return this.http.post(`${SERVICE}/usuario/getToken`, JSON.stringify(usuario))
                        .toPromise()
                        .then(res => <string> res)
                        .then(jwt => { 
                            this.setSession(jwt)
                            return jwt; })
    }

    getExpirationTime() : number{
        let now = new Date()
        let timeLeft = +this.appService.getFromLocal('expires_at') - now.getTime()
        return timeLeft
    }

    expires(){
        this.logout()
        this.toLogin()
    }

    logout(){
        this.appService.removeInLocal('token')
        this.appService.removeInLocal('expires_at')
        this.appService.removeInLocal('usuario')
    }
    // logout(){
        
    //     this.reloadAuthService()
    //     this.router.navigate(['/inicio'])
    // }

    toLogin() {
        this.router.navigate(['/inicio'])
    }
}
