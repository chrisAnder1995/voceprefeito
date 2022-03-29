import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../service/usuario.service';

@Component({
  selector: 'di-menu',
  templateUrl: './menu.component.html'
})
export class MenuComponent implements OnInit {

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit() {
  }

  isAdmin(): boolean {
    return this.usuarioService.isAdmin()
  }

  isStaff(): boolean {
    return this.usuarioService.isStaff()
  }
}
