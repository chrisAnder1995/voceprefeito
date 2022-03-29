import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { Usuario } from '../model/Usuario.model';
import { Imagem } from '../model/Imagem.model';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'di-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  timeLeft: string;
  interval;

  constructor(private authService : AuthService, private appService: AppService) { }

  ngOnInit() {
    this.sessionTime()
  }

  reloadSession(){
    if (this.timeLeft != "00:00"){
      this.authService.reloadSession()
    } else{
      this.authService.logout()
      this.authService.toLogin()
    }
  }

  sessionTime() {
    this.interval = setInterval(() => {
      let expiration = this.authService.getExpirationTime()
      let fullSeconds = Math.floor(expiration / 1000)

      let m = Math.floor(fullSeconds / 60)
      let s = Math.floor(fullSeconds % 60)

      if (m >= 0){
        this.timeLeft = m.toString().padStart(2, "0") + ":" + s.toString().padStart(2, "0")
      } else{
        this.timeLeft = "00:00"
      }
    },1000)
  }

  logout(){
    this.authService.logout()
    this.authService.toLogin()
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
