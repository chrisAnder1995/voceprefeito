import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { PaisService } from '../../service/pais.service';
import { Router, ActivatedRoute } from '../../../../node_modules/@angular/router';
import { Pais } from '../../model/Pais.model';
import { AppService } from '../../app.service';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'di-pais-form',
  templateUrl: './pais-form.component.html',
  styleUrls: ['./pais-form.component.css']
})
export class PaisFormComponent implements OnInit {
  @Input() modal : any
  @Output() modalResult = new EventEmitter();
  
  object = new Pais()
  showLoading: boolean = true
  message: string = ""
  list: Pais[] = []
  successMessage: boolean = false
  errorMessage: boolean = false

  saveDisabled = false

  constructor(private router: Router, 
              private paisService: PaisService, 
              private app: AppService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
    if (this.modal == undefined){
      this.app.setTitle('País')
      this.app.setMap(['País', 'Cadastro'])

      const id = this.route.snapshot.params['id']
      if(id > 0) {
        this.paisService.getById(id).then(pais => {
          this.showLoading = false
          this.object = pais
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
      this.paisService.update(this.object).then(pais => {
        if (pais !== undefined){
          this.router.onSameUrlNavigation = 'reload'
          this.message = "Registro excluído com sucesso"
          this.successMessage = true
          this.modalResult.emit(pais)

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
        this.paisService.create(this.object).then(pais => {
          this.saveDisabled = false
          if (pais !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.successMessage = true
            this.modalResult.emit(pais)
  
            this.clean()
            this.closeForm()
          } else{
            this.errorMessage = true
            this.message = "Ocorreu um erro, favor tentar novamente"
          }
        })
      } else {
        this.paisService.update(this.object).then(pais => {
          this.saveDisabled = false
          if (pais !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.modalResult.emit(pais)
  
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
    this.object = new Pais()
  }
}
