import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Usuario } from '../../model/Usuario.model';
import { UsuarioService } from '../../service/usuario.service';
import { AppService } from '../../app.service';
import { PERMISSAO } from '../../model/enum/Enumeration.api.enum';

@Component({
  selector: 'di-usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.css']
})
export class UsuarioListComponent implements OnInit {
  @Input() modal : any
  @Output() modalResult = new EventEmitter();

  allUsuario: Usuario[] = []
  showLoading: boolean = true

  constructor(private usuarioService: UsuarioService,
              private appService: AppService) { }

  ngOnInit() {
    if (this.modal == undefined){
      this.appService.setTitle('Usuário')
      this.appService.setMap(['Usuário', 'Lista'])
    }

    this.getAllUsuario()
  }

  modalSelect(object){
    this.modalResult.emit(object)
  }

  getAllUsuario() {
    return this.usuarioService.getAllFast().then(usuario => {
      this.allUsuario = usuario
      this.showLoading = false
    })
  }

  allPermissoes(): string[] {
    return Object.keys(PERMISSAO)
  }

  permissaoString(item) : string{
    return PERMISSAO[item]
  }
}
