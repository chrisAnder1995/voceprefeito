import { Component, OnInit } from '@angular/core';
import { AppService } from '../../app.service';
import { Status } from 'src/app/model/StatusAtendimento.model';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng-lts/api';
import { StatusService } from 'src/app/service/statusAtendimento.service';

@Component({
  selector: 'di-status-list',
  templateUrl: './status-list.component.html',
  styleUrls: ['./status-list.component.css']
})
export class StatusListComponent implements OnInit {

  example : Status
  exampleConfirmed : Status

  list: Status[] = []
  showLoading: boolean = false
  cols : any[]
  totalRecords : number = 0
  listOrder = "id"
  event : any

  constructor(
    private statusService: StatusService,
    public config: DynamicDialogConfig,
    public ref: DynamicDialogRef, 
              private app: AppService) { }

  ngOnInit() {
    this.app.setTitle('Status')
    this.app.setMap(['Status', 'Lista'])

    // this.getList()

    this.example = new Status()

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
      return this.statusService.getByExample(this.example).then(list => {
       
        this.list = list
        this.showLoading = false

        if (list.length > 0){
          if (list[0].listSize != undefined && list[0].listSize > 0){
            this.totalRecords = list[0].listSize
          } else{
            this.totalRecords = 1000
          }
        }

        this.closeModal('#modal-Status-filter')
      })
  }

  
  closeModal(modal){
    // @ts-ignore
    $(modal).modal('hide')
  }

  downloadListagemPDF(open?: boolean){
    this.showLoading = true
    this.statusService.getListagem(open,this.exampleConfirmed).then(_ => this.showLoading = false)
  }

  clearFilter(){
    this.example = new Status()
    this.listOrder = 'id'
// INSERT_CLEAR
    this.getList()
  }



}
