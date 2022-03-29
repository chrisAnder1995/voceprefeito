import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { DrogaService } from '../../service/droga.service';
import { Router, ActivatedRoute } from '../../../../node_modules/@angular/router';
import { Droga } from '../../model/Droga.model';
import { AppService } from '../../app.service';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'di-droga-form',
  templateUrl: './droga-form.component.html',
  styleUrls: ['./droga-form.component.css']
})
export class DrogaFormComponent implements OnInit {
  @Input() modal : any
  @Output() modalResult = new EventEmitter();
  
  object = new Droga()
  showLoading: boolean = true
  message: string = ""
  list: Droga[] = []
  successMessage: boolean = false
  errorMessage: boolean = false

  saveDisabled = false

  constructor(private router: Router, 
              private drogaService: DrogaService, 
              private app: AppService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
    if (this.modal == undefined){
      this.app.setTitle('Droga')
      this.app.setMap(['Droga', 'Cadastro'])

      const id = this.route.snapshot.params['id']
      if(id > 0) {
        this.drogaService.getById(id).then(droga => {
          this.showLoading = false
          this.object = droga
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
      this.drogaService.update(this.object).then(droga => {
        if (droga !== undefined){
          this.router.onSameUrlNavigation = 'reload'
          this.message = "Registro excluído com sucesso"
          this.successMessage = true
          this.modalResult.emit(droga)

          this.clean()
          this.closeForm()
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
        this.drogaService.create(this.object).then(droga => {
          this.saveDisabled = false

          if (droga !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.successMessage = true
            this.modalResult.emit(droga)
  
            this.clean()
            this.closeForm()
          } else{
            this.errorMessage = true
            this.message = "Ocorreu um erro, favor tentar novamente"
          }
        })
      } else {
        this.drogaService.update(this.object).then(droga => {
          this.saveDisabled = false

          if (droga !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.modalResult.emit(droga)
  
            this.clean()
            this.closeForm()
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
    this.object = new Droga()
  }
}
