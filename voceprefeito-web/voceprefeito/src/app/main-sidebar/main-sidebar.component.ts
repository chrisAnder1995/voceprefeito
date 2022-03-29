import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { Usuario } from '../model/Usuario.model';
import { Imagem } from '../model/Imagem.model';

@Component({
  selector: 'di-main-sidebar',
  templateUrl: './main-sidebar.component.html'
})
export class MainSidebarComponent implements OnInit {

  constructor(private appService: AppService) { }

  ngOnInit() {
  }

  getName() : string{
    let usuario: Usuario = JSON.parse(this.appService.getFromLocal('usuario'))
    if (usuario){
      return usuario.nome
    }
    return ""
  }

  getImagem(): Imagem {
    let usuario: Usuario = JSON.parse(this.appService.getFromLocal('usuario'))
    if (usuario){
      return usuario.imagem
    }
    return undefined
  }
}
