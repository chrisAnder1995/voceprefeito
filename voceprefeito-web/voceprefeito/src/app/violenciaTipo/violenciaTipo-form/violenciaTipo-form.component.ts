import { VIOLENCIA_CATEGORIA } from './../../model/enum/Enumeration.api.enum';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ViolenciaTipoService } from '../../service/violenciaTipo.service';
import { Router, ActivatedRoute } from '../../../../node_modules/@angular/router';
import { ViolenciaTipo } from '../../model/ViolenciaTipo.model';
import { AppService } from '../../app.service';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'di-violenciaTipo-form',
  templateUrl: './violenciaTipo-form.component.html',
  styleUrls: ['./violenciaTipo-form.component.css']
})
export class ViolenciaTipoFormComponent implements OnInit {
  @Input() modal : any
  @Output() modalResult = new EventEmitter();
  
  object = new ViolenciaTipo()
  showLoading: boolean = true
  message: string = ""
  list: ViolenciaTipo[] = []
  successMessage: boolean = false
  errorMessage: boolean = false

  saveDisabled = false

  constructor(private router: Router, 
              private violenciaTipoService: ViolenciaTipoService, 
              private app: AppService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
      if (this.modal == undefined){
      this.app.setTitle('Tipo de Violência')
      this.app.setMap(['Tipo de Violência', 'Cadastro'])

      const id = this.route.snapshot.params['id']
      if(id > 0) {
        this.violenciaTipoService.getById(id).then(violenciaTipo => {
          this.showLoading = false
          this.object = violenciaTipo
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
      this.violenciaTipoService.update(this.object).then(violenciaTipo => {
        if (violenciaTipo !== undefined){
          this.router.onSameUrlNavigation = 'reload'
          this.message = "Registro excluído com sucesso"
          this.successMessage = true
          this.modalResult.emit(violenciaTipo)

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
        this.violenciaTipoService.create(this.object).then(violenciaTipo => {
          this.saveDisabled = false
          if (violenciaTipo !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.successMessage = true
            this.modalResult.emit(violenciaTipo)
  
            this.clean()
            this.closeForm()
          } else{
            this.errorMessage = true
            this.message = "Ocorreu um erro, favor tentar novamente"
          }
        })
      } else {
        this.violenciaTipoService.update(this.object).then(violenciaTipo => {
          this.saveDisabled = false
          if (violenciaTipo !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.modalResult.emit(violenciaTipo)
  
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
    this.object = new ViolenciaTipo()
  }

  allViolenciaCategorias(): string[] {
    return Object.keys(VIOLENCIA_CATEGORIA)
  }

  violenciaCategoriaString(categoria): string{
    return VIOLENCIA_CATEGORIA[categoria]
  }
}
