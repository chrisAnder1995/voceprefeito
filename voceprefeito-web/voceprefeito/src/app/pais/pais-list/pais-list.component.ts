import { Component, OnInit } from '@angular/core';
import { AppService } from '../../app.service';
import { Pais } from 'src/app/model/Pais.model';
import { PaisService } from 'src/app/service/pais.service';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng-lts/api';

@Component({
  selector: 'di-pais-list',
  templateUrl: './pais-list.component.html',
  styleUrls: ['./pais-list.component.css']
})
export class PaisListComponent implements OnInit {

  example : Pais
  exampleConfirmed : Pais

  list: Pais[] = []
  showLoading: boolean = false
  cols : any[]
  totalRecords : number = 0
  listOrder = "id"
  event : any

  constructor(private paisService: PaisService,
    public config: DynamicDialogConfig,
    public ref: DynamicDialogRef, 
              private app: AppService) { }

  ngOnInit() {
    this.app.setTitle('País')
    this.app.setMap(['País', 'Lista'])

    // this.getList()

    this.example = new Pais()

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
      return this.paisService.getByExample(this.example).then(list => {
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

  
  closeModal(modal){
    // @ts-ignore
    $(modal).modal('hide')
  }

  downloadListagemPDF(open?: boolean){
    this.showLoading = true
    this.paisService.getListagem(open,this.exampleConfirmed).then(_ => this.showLoading = false)
  }

  clearFilter(){
    this.example = new Pais()
    this.listOrder = 'id'
// INSERT_CLEAR
    this.getList()
  }



}
