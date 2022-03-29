import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Router, ActivatedRoute } from '../../../../node_modules/@angular/router';
import { Status } from '../../model/StatusAtendimento.model';
import { AppService } from '../../app.service';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';
import { StatusService } from 'src/app/service/statusAtendimento.service';

@Component({
  selector: 'di-status-form',
  templateUrl: './status-form.component.html',
  styleUrls: ['./status-form.component.css']
})
export class StatusFormComponent implements OnInit {

  @Input() modal : any
  @Output() modalResult = new EventEmitter();
  
  object = new Status()
  showLoading: boolean = true
  message: string = ""
  list: Status[] = []
  successMessage: boolean = false
  errorMessage: boolean = false

  saveDisabled = false

  constructor(private router: Router, 
              private statusService: StatusService, 
              private app: AppService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
    if (this.modal == undefined){
      this.app.setTitle('Status')
      this.app.setMap(['Status', 'Cadastro'])

      const id = this.route.snapshot.params['id']
      if(id > 0) {
        this.statusService.getById(id).then(status => {
          this.showLoading = false
          this.object = status
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
      this.statusService.update(this.object).then(status => {
        if (status !== undefined){
          this.router.onSameUrlNavigation = 'reload'
          this.message = "Registro excluído com sucesso"
          this.successMessage = true
          this.modalResult.emit(status)

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
        this.statusService.create(this.object).then(status => {
          this.saveDisabled = false
          if (status !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.successMessage = true
            this.modalResult.emit(status)
  
            this.clean()
            this.closeForm()
          } else{
            this.errorMessage = true
            this.message = "Ocorreu um erro, favor tentar novamente"
          }
        })
      } else {
        this.statusService.update(this.object).then(status => {
          this.saveDisabled = false
          if (status !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.modalResult.emit(status)
  
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
    this.object = new Status()
  }
}
