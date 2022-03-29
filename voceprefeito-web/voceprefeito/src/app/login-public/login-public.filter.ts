import { Route, CanLoad, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router'
import { Injectable } from '@angular/core'
import { UsuarioService } from '../service/usuario.service';



@Injectable()
export class LoginFilterPublic implements CanLoad, CanActivate{

  constructor(private usuarioService : UsuarioService){
  }

  isLoggedIn(url : string): boolean{
    const logged = this.usuarioService.isLoggedIn()

    if (!logged){
      this.usuarioService.toLogin()
    }
    return logged;
  }

  canLoad(route : Route): boolean{
    return this.isLoggedIn(route.path)
  }

  canActivate(activatedRoute : ActivatedRouteSnapshot, routerState : RouterStateSnapshot): boolean{
    return this.isLoggedIn(activatedRoute.routeConfig.path)
  }
}
