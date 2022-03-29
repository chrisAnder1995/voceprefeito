import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Router, ActivatedRoute } from '../../../../node_modules/@angular/router';
import { MotivoSolicitacao } from '../../model/MotivoSolicitacao.model';
import { AppService } from '../../app.service';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';
import { MotivoSolicitacaoService } from 'src/app/service/motivoSolicitacao.service';

@Component({
  selector: 'di-motivoSolicitacao-form',
  templateUrl: './motivoSolicitacao-form.component.html',
  styleUrls: ['./motivoSolicitacao-form.component.css']
})
export class MotivoSolicitacaoFormComponent implements OnInit {
  @Input() modal : any
  @Output() modalResult = new EventEmitter();
  
  object = new MotivoSolicitacao()
  showLoading: boolean = true
  message: string = ""
  list: MotivoSolicitacao[] = []
  successMessage: boolean = false
  errorMessage: boolean = false

  saveDisabled = false

  constructor(private router: Router, 
              private motivoSolicitacaoService: MotivoSolicitacaoService, 
              private app: AppService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
    if (this.modal == undefined){
      this.app.setTitle('País')
      this.app.setMap(['País', 'Cadastro'])

      const id = this.route.snapshot.params['id']
      if(id > 0) {
        this.motivoSolicitacaoService.getById(id).then(MotivoSolicitacao => {
          this.showLoading = false
          this.object = MotivoSolicitacao
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
      this.motivoSolicitacaoService.update(this.object).then(MotivoSolicitacao => {
        if (MotivoSolicitacao !== undefined){
          this.router.onSameUrlNavigation = 'reload'
          this.message = "Registro excluído com sucesso"
          this.successMessage = true
          this.modalResult.emit(MotivoSolicitacao)

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
        this.motivoSolicitacaoService.create(this.object).then(MotivoSolicitacao => {
          this.saveDisabled = false
          if (MotivoSolicitacao !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.successMessage = true
            this.modalResult.emit(MotivoSolicitacao)
  
            this.clean()
            this.closeForm()
          } else{
            this.errorMessage = true
            this.message = "Ocorreu um erro, favor tentar novamente"
          }
        })
      } else {
        this.motivoSolicitacaoService.update(this.object).then(MotivoSolicitacao => {
          this.saveDisabled = false
          if (MotivoSolicitacao !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.modalResult.emit(MotivoSolicitacao)
  
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
    this.object = new MotivoSolicitacao()
  }
}
