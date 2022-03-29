import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanLoad, Route, RouterStateSnapshot } from '@angular/router';
import { AppService } from './app.service';
import { UsuarioService } from './service/usuario.service';



@Injectable()
export class StaffFilter implements CanLoad, CanActivate{
    constructor(private usuarioService: UsuarioService,
                private appService: AppService){
    }

    isStaff(): boolean{
        let isStaff = this.usuarioService.isStaff()
        return isStaff
    }

    canLoad(route : Route): boolean{
        return this.isStaff()
    }

    canActivate(activatedRoute : ActivatedRouteSnapshot, routerState : RouterStateSnapshot): boolean{
        return this.isStaff()
    }
}
