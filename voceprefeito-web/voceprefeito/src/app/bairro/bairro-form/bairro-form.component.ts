import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { BairroService } from '../../service/bairro.service';
import { Router, ActivatedRoute } from '../../../../node_modules/@angular/router';
import { Bairro } from '../../model/Bairro.model';
import { AppService } from '../../app.service';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';
import { removeModal } from '../../modal.config'
import { RegiaoService } from '../../service/Regiao.service';
import { Regiao } from '../../model/Regiao.model';


@Component({
  selector: 'di-bairro-form',
  templateUrl: './bairro-form.component.html',
  styleUrls: ['./bairro-form.component.css']
})
export class BairroFormComponent implements OnInit {
  @Input() modal : any
  @Output() modalResult = new EventEmitter();
  
  object = new Bairro()
  showLoading: boolean = true
  message: string = ""
  list: Bairro[] = []
  successMessage: boolean = false
  errorMessage: boolean = false
  regioes : Regiao []
  regiao : Regiao

  saveDisabled = false

  constructor(private router: Router, 
              private bairroService: BairroService, 
              private app: AppService,
              private route: ActivatedRoute,
              private regiaoService: RegiaoService,
              private location: Location) { }

  ngOnInit() {
    this.carregaListas()
    this.regiao = new Regiao()
    if (this.modal == undefined){
      this.app.setTitle('Bairro')
      this.app.setMap(['Bairro', 'Cadastro'])

      const id = this.route.snapshot.params['id']
      if(id > 0) {
        this.bairroService.getById(id).then(bairro => {
          this.showLoading = false
          this.object = bairro
        })
      } else{
        this.showLoading = false
      }
    }
  }

  carregaListas(){
    
    this.regiaoService.getAllFast().then(list => {
      this.regioes = list
    })

  }

  closeForm() {
    if (this.modal == undefined){
      this.location.back();
    }
  }

  delete(){
    if(this.object.id != undefined) {
      this.object.ativo = false
      this.bairroService.update(this.object).then(bairro => {
        if (bairro !== undefined){
          this.router.onSameUrlNavigation = 'reload'
          this.message = "Registro excluído com sucesso"
          this.successMessage = true
          this.modalResult.emit(bairro)

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
      this.object.regiao = new Regiao()
      this.object.regiao = this.regiao

      if(this.object.id === undefined) {
        this.bairroService.create(this.object).then(bairro => {
          this.saveDisabled = false
          if (bairro !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.successMessage = true
            this.modalResult.emit(bairro)
  
            this.clean()
            this.closeForm()
          } else{
            this.errorMessage = true
            this.message = "Ocorreu um erro, favor tentar novamente"
          }
        })
      } else {
        this.bairroService.update(this.object).then(bairro => {
          this.saveDisabled = false
          if (bairro !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.modalResult.emit(bairro)
  
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
    this.object = new Bairro()
  }

}
