import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { LocalOcorrenciaService } from '../../service/localOcorrencia.service';
import { Router, ActivatedRoute } from '../../../../node_modules/@angular/router';
import { LocalOcorrencia } from '../../model/LocalOcorrencia.model';
import { AppService } from '../../app.service';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'di-localOcorrencia-form',
  templateUrl: './localOcorrencia-form.component.html',
  styleUrls: ['./localOcorrencia-form.component.css']
})
export class LocalOcorrenciaFormComponent implements OnInit {
  @Input() modal : any
  @Output() modalResult = new EventEmitter();
  
  object = new LocalOcorrencia()
  showLoading: boolean = true
  message: string = ""
  list: LocalOcorrencia[] = []
  successMessage: boolean = false
  errorMessage: boolean = false

  saveDisabled = false

  constructor(private router: Router, 
              private localOcorrenciaService: LocalOcorrenciaService, 
              private app: AppService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
    if (this.modal == undefined){
      this.app.setTitle('Local da Ocorrência')
      this.app.setMap(['Local da Ocorrência', 'Cadastro'])

      const id = this.route.snapshot.params['id']
      if(id > 0) {
        this.localOcorrenciaService.getById(id).then(localOcorrencia => {
          this.showLoading = false
          this.object = localOcorrencia
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
      this.localOcorrenciaService.update(this.object).then(localOcorrencia => {
        if (localOcorrencia !== undefined){
          this.router.onSameUrlNavigation = 'reload'
          this.message = "Registro excluído com sucesso"
          this.successMessage = true
          this.modalResult.emit(localOcorrencia)

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
        this.localOcorrenciaService.create(this.object).then(localOcorrencia => {
          this.saveDisabled = false
          if (localOcorrencia !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.successMessage = true
            this.modalResult.emit(localOcorrencia)
  
            this.clean()
            this.closeForm()
          } else{
            this.errorMessage = true
            this.message = "Ocorreu um erro, favor tentar novamente"
          }
        })
      } else {
        this.localOcorrenciaService.update(this.object).then(localOcorrencia => {
          this.saveDisabled = false
          if (localOcorrencia !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.modalResult.emit(localOcorrencia)
  
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
    this.object = new LocalOcorrencia()
  }
}
