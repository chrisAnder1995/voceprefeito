import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { sha256 } from 'js-sha256';
import { AppService } from '../../app.service';
import { Imagem } from '../../model/Imagem.model';
import { Usuario } from '../../model/Usuario.model';
import { UsuarioService } from '../../service/usuario.service';

@Component({
  selector: 'di-usuario-profile',
  templateUrl: './usuario-profile.component.html',
  styleUrls: ['./usuario-profile.component.css']
})
export class UsuarioProfileComponent implements OnInit {
  usuario = new Usuario()
  senha: string = ""
  senhaConfirm: string = ""
  message: string = ""
  successMessage: boolean = false
  errorMessage: boolean = false
  selectedFile: File
  imagem = new Imagem()
  imageChangedEvent: any = '';
  croppedImage: any = '';

  saveDisabled = false

  constructor(private router: Router, 
              private usuarioService: UsuarioService, 
              private appService: AppService) { }

  ngOnInit() {
    this.appService.setTitle('Profile')
    this.appService.setMap(['Usuario', 'Profile'])

    this.usuario = JSON.parse(this.appService.getFromLocal('usuario'))
    this.imagem = this.usuario.imagem
  }

  imageCropped(event) {
      this.croppedImage = event.base64
      
      let re = /data:image\/png;base64\,/;
      this.imagem.base64 = event.base64.replace(re,"")
  }
  imageLoaded() {
      // show cropper
  }
  cropperReady() {
      // cropper ready
  }
  loadImageFailed() {
      // show message
  }

  save(ngForm : NgForm){
    let message: string = ""
    if(ngForm.valid) {
      if(this.verifyPasswordChange()) {
        if(this.validatePassword()) {
          this.usuario.senha = sha256(this.senhaConfirm)
          this.usuario.senha = this.usuario.senha.toUpperCase()
        } else {
          message = "A senha e a confirmação devem ser iguais"
        }
      }

      if(message.length <= 0) {
        this.usuario.imagem = this.imagem
        this.usuarioService.update(this.usuario).then(usuario => {
          if (usuario !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.successMessage = true
            this.message = "Os dados foram salvos"
            this.router.navigate(['profile'])
            this.reloadUsuario(this.usuario)
          } else{
            message = "Ocorreu um erro. Favor entrar em contato com o suporte"
          }
        })
      }
    } else {
      message = "Existem campos obrigatórios não preenchidos"
    }

    if(message.length > 0) {
      this.errorMessage = true
      this.message = message
    }
  }

  onUpload(event) {
    this.imageChangedEvent = event;
    this.selectedFile = event.target.files[0]
    if(this.selectedFile !== undefined) {
      this.imagem.name = this.selectedFile.name
      this.imagem.type = this.selectedFile.name.split('.').pop()
      this.imagem.date = new Date()
    }
  }

  toEdit(usuario: Usuario) {
    this.usuario = usuario
    usuario = undefined
  }

  clean() {
    this.usuario = new Usuario()
  }

  validatePassword(): boolean {
    let valid = false
    if(this.senha === this.senhaConfirm) {
      valid = true
    }

    return valid
  }

  verifyPasswordChange(): boolean {
    let changed = false
    let criptPassword = ""

    if(this.senhaConfirm !== undefined && this.senhaConfirm.length>0) {
      criptPassword = sha256(this.senhaConfirm)
      criptPassword = criptPassword.toUpperCase()
      if(criptPassword !== this.usuario.senha) {
        changed = true
      }
    }

    return changed
  }

  reloadUsuario(usuario: Usuario) {
    this.usuarioService.login(usuario)
  }
}
