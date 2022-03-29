import { AuthService } from './../service/auth.service';
import { UsuarioService } from 'src/app/service/usuario.service';
import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { AppService } from '../app.service';
import { removeModal } from '../modal.config'

@Component({
  selector: 'di-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {

  title : string
  map : string[]

  timeLeft: string;
  interval;
  showAlertSession : boolean = false

  mapHTML : string
  exibirModal = true

  constructor(private app: AppService, private authService : AuthService) {  }

  ngOnInit(){
    this.app.title.subscribe(title => this.title = title)
    this.app.map.subscribe(map => this.updateMap(map))

    this.sessionTime()
  }

  reloadSession(){
    if (this.timeLeft != "00:00"){
      this.showAlertSession = false
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

      if (this.timeLeft == "02:00"){
        this.showAlertSession = true
      }
      if (this.timeLeft == "00:00" && this.exibirModal){
        this.exibirModal = false
        document.getElementById("showLoginModal").click();
      }
    },1000)
  }

  isLast(title : string) : string{
    if (title == this.map[this.map.length-1])
      return "ativo"
    else
      return ""
  }

  login(event){
    this.exibirModal = true
    removeModal()
    document.getElementById("showLoginModal").click();
  }

  updateMap(map : string[]){
    this.map = map
    this.mapHTML = `<li><a href="#"><i class="fa fa-bullhorn"></i> Você Prefeito</a></li>`
    for (let local of map){
      this.mapHTML += ` <li class='${this.isLast(local)}'> ${local} </li> `
    }
  }
}
