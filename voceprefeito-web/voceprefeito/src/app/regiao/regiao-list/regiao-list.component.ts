import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { AppService } from '../../app.service';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng-lts/api';
import { RegiaoService } from '../../service/Regiao.service';
import { Regiao } from '../../model/Regiao.model';

@Component({
  selector: 'di-regiao-list',
  templateUrl: './regiao-list.component.html',
  styleUrls: ['./regiao-list.component.css']
})
export class RegiaoListComponent implements OnInit {
  showLoading: boolean = true
  cols : any[]

  example : Regiao
  exampleConfirmed : Regiao

  list: Regiao[] = []
  totalRecords : number = 0
  listOrder = "id"
  event : any

  constructor(
    private regiaoService: RegiaoService,
    public config: DynamicDialogConfig,
    public ref: DynamicDialogRef, 
              private app: AppService) { }

  ngOnInit() {
      this.app.setTitle('Regiao')
      this.app.setMap(['Regiao', 'Lista'])

    this.example = new Regiao()

    this.cols = [
      { field: 'id', header: '#' , width: 50 },
      { field: 'nome', header: 'Nome' },
      { field: 'estado', header: 'Estado' },
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
    debugger
    this.example.listPage = (this.event.first / this.event.rows)
    this.example.listPageSize = this.event.rows
    this.example.listOrder = this.listOrder
    this.exampleConfirmed = JSON.parse(JSON.stringify(this.example))
    return this.regiaoService.getByExample(this.example).then(list => {
      this.list = list
      this.showLoading = false

      if (list.length > 0){
        if (list[0].listSize != undefined && list[0].listSize > 0){
          this.totalRecords = list[0].listSize
        } else{
          this.totalRecords = 1000
        }
      }

      this.closeModal('#modal-pais-filter')
    })
}

  downloadListagemPDF(open?: boolean){
    this.regiaoService.getListagem(open,this.exampleConfirmed).then(_ => this.showLoading = false)
 
  }

  closeModal(modal){
    // @ts-ignore
    $(modal).modal('hide')
  }

  clearFilter(){
    this.example = new Regiao()
    this.listOrder = 'id'
// INSERT_CLEAR
    this.getList()
  }

  desativar(obj){
    this.example = obj
    this.example.ativo = false
    this.regiaoService.update(this.example).then(obj => {
      this.example = obj
    })
  }

  ativar(obj){
    this.example = obj
    this.example.ativo = true
    this.regiaoService.update(this.example).then(obj => {
      this.example = obj
    })
  }

}
