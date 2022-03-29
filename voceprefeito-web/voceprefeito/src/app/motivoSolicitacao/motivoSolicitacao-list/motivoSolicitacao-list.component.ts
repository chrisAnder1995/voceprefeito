import { Component, OnInit } from '@angular/core';
import { AppService } from '../../app.service';
import { MotivoSolicitacao } from 'src/app/model/MotivoSolicitacao.model';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng-lts/api';
import { MotivoSolicitacaoService } from 'src/app/service/motivoSolicitacao.service';

@Component({
  selector: 'di-movimentoSolicitacao-list',
  templateUrl: './motivoSolicitacao-list.component.html',
  styleUrls: ['./motivoSolicitacao-list.component.css']
})
export class MotivoSolicitacaoListComponent implements OnInit {

  example : MotivoSolicitacao
  exampleConfirmed : MotivoSolicitacao

  list: MotivoSolicitacao[] = []
  showLoading: boolean = false
  cols : any[]
  totalRecords : number = 0
  listOrder = "id"
  event : any

  constructor(private motivoSolicitacaoService: MotivoSolicitacaoService,
    public config: DynamicDialogConfig,
    public ref: DynamicDialogRef, 
              private app: AppService) { }

  ngOnInit() {
    this.app.setTitle('Motivo de Solicitacao')
    this.app.setMap(['Motivo de Solicitacao', 'Lista'])

    // this.getList()

    this.example = new MotivoSolicitacao()

    this.cols = [
      { field: 'id', header: '#' , width: 70 },
    ];
  }

  isModal(){
    if (this.config != undefined && this.config.data != undefined && this.config.data.object != undefined){
      return true
    } else{
      return false
    }
  }

  async modalResult(obj){
    if (this.isModal()){
      this.ref.close(obj);
    }
  }

  loadListLazy(event){
    this.event = event
    this.getList()
  }

  getList() {

      this.example.listPage = (this.event.first / this.event.rows)
      this.example.listPageSize = this.event.rows
      this.example.listOrder = this.listOrder

      this.example.ativo = true
      this.exampleConfirmed = JSON.parse(JSON.stringify(this.example))
  // INSERT_EXAMPLE
      return this.motivoSolicitacaoService.getByExample(this.example).then(list => {
       
        this.list = list
        this.showLoading = false

        if (list.length > 0){
          if (list[0].listSize != undefined && list[0].listSize > 0){
            this.totalRecords = list[0].listSize
          } else{
            this.totalRecords = 1000
          }
        }

        this.closeModal('#modal-MotivoSolicitacao-filter')
      })
  }

  
  closeModal(modal){
    // @ts-ignore
    $(modal).modal('hide')
  }

  downloadListagemPDF(open?: boolean){
    this.showLoading = true
    this.motivoSolicitacaoService.getListagem(open,this.exampleConfirmed).then(_ => this.showLoading = false)
  }

  clearFilter(){
    this.example = new MotivoSolicitacao()
    this.listOrder = 'id'
// INSERT_CLEAR
    this.getList()
  }



}
