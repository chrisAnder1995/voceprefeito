import { Component, OnInit } from '@angular/core';
import { ServicoService } from 'src/app/service/servico.service';
import { UsuarioService } from 'src/app/service/usuario.service';
import { UsuarioEmail } from '../../model/UsuarioEmail.model';

@Component({
  selector: 'di-login-support',
  templateUrl: './login-support.component.html',
  styleUrls: ['./login-support.component.css']
})
export class LoginSupportComponent implements OnInit {

  showLoading: boolean
  contactName: string = ""
  contactMail: string = ""
  contactSubject: string = ""
  contactMessage: string = ""
  message: string = ""
  successMessage: boolean = false
  errorMessage: boolean = false

  usuarioEmail : UsuarioEmail

  constructor(private servicoService: ServicoService) { }

  ngOnInit() {
    this.usuarioEmail = new UsuarioEmail()
  }

  sendEmail(){
    this.showLoading = true
    this.usuarioEmail.nome = this.contactName
    this.usuarioEmail.email = this.contactMail
    this.usuarioEmail.assunto = this.contactSubject
    this.usuarioEmail.descricao = this.contactMessage
    this.servicoService.sendEmail(this.usuarioEmail).then(_=>{
      this.showLoading = false
      this.successMessage = true
      this.message = 'E-mail enviado com sucesso, obrigado por nos contactar!'
    })

  }
}
