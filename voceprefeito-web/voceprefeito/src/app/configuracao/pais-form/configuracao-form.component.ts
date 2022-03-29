import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ConfiguracaoService } from '../../service/configuracao.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Configuracao } from '../../model/Configuracao.model';
import { AppService } from '../../app.service';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'di-configuracao-form',
  templateUrl: './configuracao-form.component.html',
  styleUrls: ['./configuracao-form.component.css']
})
export class ConfiguracaoFormComponent implements OnInit {
  @Input() modal : any
  @Output() modalResult = new EventEmitter();
  
  object = new Configuracao()
  showLoading: boolean = true
  message: string = ""
  list: Configuracao[] = []
  successMessage: boolean = false
  errorMessage: boolean = false
  id : number

  saveDisabled = false

  constructor(private router: Router, 
              private configuracaoService: ConfiguracaoService, 
              private app: AppService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
    if (this.modal == undefined){
      this.app.setTitle('Configurações')
      
      this.configuracaoService.getAll().then(config => {
        if(config.length > 0){
          this.id = 1
        }else{
          this.id = undefined
        }

        if(this.id > 0) {
          this.configuracaoService.getById(this.id).then(configuracao => {
            this.showLoading = false
            this.object = configuracao
          })
        } else{
          this.showLoading = false
        }
      })
    }
  }


  save(ngForm: NgForm){
    if(ngForm.valid) {
      this.saveDisabled = true
      this.object.ativo = true

      if(this.object.id === undefined) {
        this.configuracaoService.create(this.object).then(configuracao => {
          this.saveDisabled = false
          if (configuracao !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.successMessage = true
            this.modalResult.emit(configuracao)
            this.clean()
          } else{
            this.errorMessage = true
            this.message = "Ocorreu um erro, favor tentar novamente"
          }
        })
      } else {
        this.configuracaoService.update(this.object).then(configuracao => {
          this.saveDisabled = false
          if (configuracao !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.successMessage = true
            this.message = "Os dados foram gravados"
  
          } else{
            this.message = "Ocorreu um erro, favor tentar novamente"
          }
        })
      }
    } else {
      this.errorMessage = true
      this.message = "Existem campos obrigatórios não preenchidos"
    }
  }

  clean() {
    this.object = new Configuracao()
  }
}
