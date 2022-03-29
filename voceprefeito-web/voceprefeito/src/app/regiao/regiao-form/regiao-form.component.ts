import { ESTADO } from './../../model/enum/Enumeration.api.enum';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { RegiaoService } from '../../service/Regiao.service';
import { Router, ActivatedRoute } from '../../../../node_modules/@angular/router';
import { Regiao } from '../../model/Regiao.model';
import { AppService } from '../../app.service';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'di-regiao-form',
  templateUrl: './regiao-form.component.html'
})
export class RegiaoFormComponent implements OnInit {
  @Input() modal : any
  @Output() modalResult = new EventEmitter();
  
  object = new Regiao()
  showLoading: boolean = true
  message: string = ""
  list: Regiao[] = []
  successMessage: boolean = false
  errorMessage: boolean = false

  saveDisabled = false

  constructor(private router: Router, 
              private regiaoService: RegiaoService, 
              private app: AppService,
              private location : Location,
              private route: ActivatedRoute) { 
               
              }

  ngOnInit() {
    if (this.modal == undefined){
      this.app.setTitle('Regiao')
      this.app.setMap(['Regiao', 'Cadastro'])
    
      const id = this.route.snapshot.params['id']
      if(id > 0) {
        this.regiaoService.getById(id).then(Regiao => {
          this.showLoading = false
          this.object = Regiao
        })
      } else{
        this.showLoading = false
      }
    }
  }

  closeForm() {
    if (this.modal == undefined){
      this.location.back();
    }
  }

  delete(){
    if(this.object.id != undefined) {
      this.object.ativo = false
      this.regiaoService.update(this.object).then(Regiao => {
        if (Regiao !== undefined){
          this.router.onSameUrlNavigation = 'reload'
          this.message = "Registro excluído com sucesso"
          this.successMessage = true
          this.modalResult.emit(Regiao)
          this.clean()

          if (this.modal == undefined){
            this.router.navigate(['Regiaos'])
          }
        } else{
          this.errorMessage = true
          this.message = "Ocorreu um erro, favor tentar novamente"
        }
      })
    }
  }

  save(ngForm: NgForm){
    if(ngForm.valid) {
      this.saveDisabled = true
      this.object.ativo = true

      if(this.object.id === undefined) {
        this.regiaoService.create(this.object).then(Regiao => {
          this.saveDisabled = false
          if (Regiao !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.successMessage = true
            this.modalResult.emit(Regiao)
            this.clean()

            if (this.modal == undefined){
              this.router.navigate(['Regiaos'])
            }
          } else{
            this.errorMessage = true
            this.message = "Ocorreu um erro, favor tentar novamente"
          }
        })
      } else {
        this.regiaoService.update(this.object).then(Regiao => {
          this.saveDisabled = false
          if (Regiao !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.modalResult.emit(Regiao)
            this.clean()

            if (this.modal == undefined){
              this.router.navigate(['Regiaos'])
            }
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
    this.object = new Regiao()
  }

  allEstados(): string[] {
    return Object.keys(ESTADO)
  }

  estadoString(estado): string{
    return ESTADO[estado]
  }

  desativar(){
    this.object.ativo = false
    this.regiaoService.update(this.object).then(obj => {
      this.object = obj
      this.closeForm()
    })
  }

  ativar(){
    this.object.ativo = true
    this.regiaoService.update(this.object).then(obj => {
      this.object = obj
      this.closeForm()
    })
  }
}

