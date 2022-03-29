import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { OrientacaoSexualService } from '../../service/orientacaoSexual.service';
import { Router, ActivatedRoute } from '../../../../node_modules/@angular/router';
import { OrientacaoSexual } from '../../model/OrientacaoSexual.model';
import { AppService } from '../../app.service';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'di-orientacaoSexual-form',
  templateUrl: './orientacaoSexual-form.component.html',
  styleUrls: ['./orientacaoSexual-form.component.css']
})
export class OrientacaoSexualFormComponent implements OnInit {
  @Input() modal : any
  @Output() modalResult = new EventEmitter();
  
  object = new OrientacaoSexual()
  showLoading: boolean = true
  message: string = ""
  list: OrientacaoSexual[] = []
  successMessage: boolean = false
  errorMessage: boolean = false

  saveDisabled = false

  constructor(private router: Router, 
              private orientacaoSexualService: OrientacaoSexualService, 
              private app: AppService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
    if (this.modal == undefined){
      this.app.setTitle('Orientação Sexual')
      this.app.setMap(['Orientação Sexual', 'Cadastro'])

      const id = this.route.snapshot.params['id']
      if(id > 0) {
        this.orientacaoSexualService.getById(id).then(orientacaoSexual => {
          this.showLoading = false
          this.object = orientacaoSexual
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
      this.orientacaoSexualService.update(this.object).then(orientacaoSexual => {
        if (orientacaoSexual !== undefined){
          this.router.onSameUrlNavigation = 'reload'
          this.message = "Registro excluído com sucesso"
          this.successMessage = true
          this.modalResult.emit(orientacaoSexual)

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
        this.orientacaoSexualService.create(this.object).then(orientacaoSexual => {
          this.saveDisabled = false
          if (orientacaoSexual !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.successMessage = true
            this.modalResult.emit(orientacaoSexual)
  
            this.clean()
            this.closeForm()
          } else{
            this.errorMessage = true
            this.message = "Ocorreu um erro, favor tentar novamente"
          }
        })
      } else {
        this.orientacaoSexualService.update(this.object).then(orientacaoSexual => {
          this.saveDisabled = false
          if (orientacaoSexual !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.modalResult.emit(orientacaoSexual)
  
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
    this.object = new OrientacaoSexual()
  }
}
