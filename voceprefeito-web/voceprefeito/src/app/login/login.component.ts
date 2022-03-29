import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { sha256 } from 'js-sha256';
import { Usuario } from '../model/Usuario.model';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'di-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  isLoggedIn: boolean = false
  email: string = ""
  senha: string = ""
  message: string = ""
  successMessage: boolean = false
  errorMessage: boolean = false

  @Input() modal : any
  @Output() modalResult = new EventEmitter();

  constructor(private router: Router, private usuarioService : UsuarioService){}

  ngOnInit() {
    this.isLoggedIn = this.usuarioService.isLoggedIn()
  }

  ifModal(css){
    if (this.modal != undefined){
      return css
    } else{
      ""
    }
  }

  login(){
    let usuario = new Usuario()
    usuario.email = this.email
    usuario.senha = sha256(this.senha)
    
    this.usuarioService.login(usuario).then(usuario => {
      if (usuario !=   undefined && usuario.id > 0){
          this.isLoggedIn = true
            this.router.onSameUrlNavigation = 'reload'
            this.router.navigate(['formularios'])
      } else{
        this.senha = ""
        this.errorMessage = true
        this.message = "Usuário ou senha inválidos"
      }
    })
  }
}
