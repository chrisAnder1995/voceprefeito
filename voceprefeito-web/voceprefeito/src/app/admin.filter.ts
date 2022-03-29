import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanLoad, Route, RouterStateSnapshot } from '@angular/router';
import { AppService } from './app.service';
import { UsuarioService } from './service/usuario.service';



@Injectable()
export class AdminFilter implements CanLoad, CanActivate{
    constructor(private usuarioService: UsuarioService,
                private appService: AppService){
    }

    isAdmin(): boolean{
        let isAdmin = this.usuarioService.isAdmin()
        return isAdmin;
    }

    canLoad(route : Route): boolean{
        return this.isAdmin()
    }

    canActivate(activatedRoute : ActivatedRouteSnapshot, routerState : RouterStateSnapshot): boolean{
        return this.isAdmin()
    }
}
