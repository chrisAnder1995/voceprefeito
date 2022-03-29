import { AuthService } from './auth.service';
import { Injectable } from "@angular/core";
import { Router } from '@angular/router';
import { SERVICE } from "../app.api";
import { AppService } from "../app.service";
import { Usuario } from "../model/Usuario.model";
import { GenericService } from "./generic.service";
import { HttpClient } from "@angular/common/http";

@Injectable()
export class UsuarioService extends GenericService<Usuario> {

    constructor(protected http: HttpClient, private router: Router, private appService: AppService, protected authService : AuthService){
        super(http, 'usuario', authService)
      }

    login(usuario: Usuario) {
        return this.http.post(`${SERVICE}/usuario/login`, JSON.stringify(usuario))
                        .toPromise()
                        .then(res => <Usuario> res)
                        .then(data => { 
                            if (data.jwt != undefined)
                                this.authService.setSession(data.jwt)
                            this.appService.saveInLocal('usuario', JSON.stringify(data))
                            return data; })
    }

    consultarUsuario(usuario: Usuario) {
        return this.http.post(`${SERVICE}/usuario/consultarUsuario`, JSON.stringify(usuario))
                        .toPromise()
                        .then(res =>  <Usuario[]> res)
                      
    }

    isLoggedIn() : boolean{
        let isLoggedIn = false
        let usuario = JSON.parse(this.appService.getFromLocal('usuario'))
    
        if (usuario) {
            isLoggedIn = true
        }
        
        return isLoggedIn;
    }

    isAdmin(): boolean {
        let isAdmin = false
        let usuario: Usuario = JSON.parse(this.appService.getFromLocal('usuario'))
    
        if (usuario) {
            if(usuario.permissao === 'ACESSO_TOTAL')
            isAdmin = true
        }

        return isAdmin
    }

    isStaff(): boolean {
        let isStaff = false
        let usuario: Usuario = JSON.parse(this.appService.getFromLocal('usuario'))
        if (usuario) {
            if (usuario.permissao != 'ACESSO_SUSPEITA'){
                isStaff = true
            }
        }

        return isStaff
    }

    logout(){
        this.appService.removeInLocal('token')
        this.appService.removeInLocal('expires_at')
        this.appService.removeInLocal('usuario')
    }

    toLogin() {
        this.router.navigate(['/inicio'])
    }
}
