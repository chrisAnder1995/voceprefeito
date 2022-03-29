import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormControl, NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { sha256 } from 'js-sha256';
import { Usuario } from '../model/Usuario.model';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'di-login-public',
  templateUrl: './login-public.component.html',
  styleUrls: ['./login-public.component.css']
})
export class LoginPublicComponent implements OnInit {
  isLoggedIn: boolean = false
  cpf: string = ""
  senha: string = ""
  message: string = ""
  successMessage: boolean = false
  errorMessage: boolean = false

  retorno : number

  @Input() modal : any
  @Output() modalResult = new EventEmitter();

  constructor(private router: Router, private usuarioService : UsuarioService,  private route: ActivatedRoute,){}

  ngOnInit() {
    if(this.route.snapshot.params.id){
      this.successMessage = true
      this.message = "Solicitação registrada com sucesso, obrigado!"
    }
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
    
    if(this.valida(this.cpf)){
      let usuario = new Usuario()
      usuario.ativo = true
      usuario.cpf = this.cpf
      this.usuarioService.consultarUsuario(usuario).then(usuarios =>{
        if(usuarios != undefined){
          if(usuarios.length > 0 ){
            if(usuarios[0].permissao == 'ACESSO_TOTAL' && usuarios[0].ativo == true){
              this.router.navigate(['atendimento',this.cpf])
            }else{
              this.successMessage = false
              this.errorMessage = true
              this.message = "Sugestões de Políticas Públicas já escolhidas pelo CPF cadastrado!"
            }
            
          }
          else{
            this.router.navigate(['atendimento',this.cpf])
          }
          
        }
      })

    }else{
      this.errorMessage = true
      this.message = "CPF incorreto, ou não existe!"
    }
  }

  valida(cpf: string): boolean {
    if (cpf == null) {
        return false;
    }
    if (cpf.length != 11) {
        return false;
    }
    if ((cpf == '00000000000') || (cpf == '11111111111') || (cpf == '22222222222') || (cpf == '33333333333') || (cpf == '44444444444') || (cpf == '55555555555') || (cpf == '66666666666') || (cpf == '77777777777') || (cpf == '88888888888') || (cpf == '99999999999')) {
        return false;
    }
    let numero: number = 0;
    let caracter: string = '';
    let numeros: string = '0123456789';
    let j: number = 10;
    let somatorio: number = 0;
    let resto: number = 0;
    let digito1: number = 0;
    let digito2: number = 0;
    let cpfAux: string = '';
    cpfAux = cpf.substring(0, 9);
    for (let i: number = 0; i < 9; i++) {
        caracter = cpfAux.charAt(i);
        if (numeros.search(caracter) == -1) {
            return false;
        }
        numero = Number(caracter);
        somatorio = somatorio + (numero * j);
        j--;
    }
    resto = somatorio % 11;
    digito1 = 11 - resto;
    if (digito1 > 9) {
        digito1 = 0;
    }
    j = 11;
    somatorio = 0;
    cpfAux = cpfAux + digito1;
    for (let i: number = 0; i < 10; i++) {
        caracter = cpfAux.charAt(i);
        numero = Number(caracter);
        somatorio = somatorio + (numero * j);
        j--;
    }
    resto = somatorio % 11;
    digito2 = 11 - resto;
    if (digito2 > 9) {
        digito2 = 0;
    }
    cpfAux = cpfAux + digito2;
    if (cpf != cpfAux) {
        return false;
    }
    else {
        return true;
    }
}
}
