import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { ServicoService } from '../../service/servico.service';
import { Router, ActivatedRoute } from '../../../../node_modules/@angular/router';
import { Servico } from '../../model/Servico.model';
import { AppService } from '../../app.service';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';
import { PoliticaService } from '../../service/politica.service';
import { Politica } from '../../model/Politica.model';

@Component({
  selector: 'di-servico-form',
  templateUrl: './servico-form.component.html',
  styleUrls: ['./servico-form.component.css']
})
export class ServicoFormComponent implements OnInit {
  @Input() modal : any
  @Output() modalResult = new EventEmitter();
  
  object = new Servico()
  showLoading: boolean = true
  message: string = ""
  list: Servico[] = []
  successMessage: boolean = false
  errorMessage: boolean = false
  politicas : Politica[]=[]

  saveDisabled = false

  constructor(private router: Router, 
              private servicoService: ServicoService, 
              private app: AppService,
              private politicaService: PoliticaService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
    if (this.modal == undefined){
      this.app.setTitle('Serviço')
      this.app.setMap(['Serviço', 'Cadastro'])

      const id = this.route.snapshot.params['id']

      this.politicaService.getAllFast().then(politicas =>{
        this.politicas = politicas
      })
  
      if(id > 0) {
        this.servicoService.getById(id).then(servico => {
          this.showLoading = false
          this.object = servico
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
      this.servicoService.update(this.object).then(servico => {
        if (servico !== undefined){
          this.router.onSameUrlNavigation = 'reload'
          this.message = "Registro excluído com sucesso"
          this.successMessage = true
          this.modalResult.emit(servico)

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
        this.servicoService.create(this.object).then(servico => {
          this.saveDisabled = false
          if (servico !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.successMessage = true
            this.modalResult.emit(servico)
            
            this.clean()
            this.closeForm()
          } else{
            this.errorMessage = true
            this.message = "Ocorreu um erro, favor tentar novamente"
          }
        })
      } else {
        this.servicoService.update(this.object).then(servico => {
          this.saveDisabled = false
          if (servico !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Os dados foram gravados"
            this.modalResult.emit(servico)
            
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
    this.object = new Servico()
  }
}
