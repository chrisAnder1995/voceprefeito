import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { AppService } from '../../app.service';
import { ActivatedRoute } from '@angular/router';
import * as Enums from '../../model/enum/Enumeration.api.enum';
import { removeModal } from '../../modal.config'
import { ViolenciaTipo } from 'src/app/model/ViolenciaTipo.model';
import { ViolenciaTipoService } from 'src/app/service/violenciaTipo.service';
import { LocalFileService } from 'src/app/service/localFile.service';
import { DynamicDialogRef, DynamicDialogConfig, DialogService  } from 'primeng-lts/api';
// INSERT_IMPORTS

@Component({
  selector: 'di-violenciaTipo-list',
  templateUrl: './violenciaTipo-list.component.html',
  styleUrls: ['./violenciaTipo-list.component.css']
})
export class ViolenciaTipoListComponent implements OnInit {
  @Input() modal : any
  Object = Object

  example : ViolenciaTipo
  exampleConfirmed : ViolenciaTipo

  list: ViolenciaTipo[] = []
  showLoading: boolean = true
  cols : any[]
  totalRecords : number = 0
  listOrder = "id"
  event : any

// INSERT_VARIABLES

  constructor(private route: ActivatedRoute,
              private violenciaTipoService: ViolenciaTipoService,
              private localFileService: LocalFileService,
              public ref: DynamicDialogRef, 
              public config: DynamicDialogConfig,
              public dialogService : DialogService,
// INSERT_SERVICES
              private app: AppService) {
                route.params.subscribe(val => {
                })
  }

  ngOnInit() {
    if (this.modal == undefined){
      this.app.setTitle('Tipo de Violência')
      this.app.setMap(['Tipo de Violência', 'Lista'])
    }

    this.example = new ViolenciaTipo()

    this.getList()

// INSERT_INIT

    this.cols = [
      { field: 'id', header: '#' , width: 70 },
// INSERT_LISTA
    ];
  }

  closeForm() {
    if (!this.isModal()){
      //this.location.back();
    }
  }

  async modalResult(obj){
    if (this.isModal()){
      this.ref.close(obj);
    }
  }
  
  isModal(){
    if (this.config != undefined && this.config.data != undefined && this.config.data.object != undefined){
      return true
    } else{
      return false
    }
  }

  clearFilter(){
    this.example = new ViolenciaTipo()
    this.listOrder = 'id'
// INSERT_CLEAR
    this.getList()
  }

  loadListLazy(event){
    this.event = event
    this.getList()
  }

  getList() {
    if (this.event != undefined){
      this.example.listPage = (this.event.first / this.event.rows)
      this.example.listPageSize = this.event.rows
      this.example.listOrder = this.listOrder

      this.example.ativo = true
      this.exampleConfirmed = JSON.parse(JSON.stringify(this.example))
      this.showLoading = true
  // INSERT_EXAMPLE
      return this.violenciaTipoService.getByExample(this.example).then(list => {
        this.list = list
        this.showLoading = false

        if (list.length > 0){
          if (list[0].listSize != undefined && list[0].listSize > 0){
            this.totalRecords = list[0].listSize
          } else{
            this.totalRecords = 1000
          }
        }

        this.closeModal('#modal-violenciaTipo-filter')
      })
    }
  }

  downloadListagemPDF(open?: boolean){
    this.showLoading = true
    this.violenciaTipoService.getListagem(open,this.exampleConfirmed).then(_ => this.showLoading = false)
  }

  escreveBoolean(bool : boolean){
    if (bool == undefined){
      return ""
    }
    if (bool){
      return 'Sim'
    } else{
      return 'Não'
    }
  }

  closeModal(modal){
    // @ts-ignore
    $(modal).modal('hide')
  }

  compareFn(o1: any, o2:any): boolean {     
    return o1 && o2 ? o1.id === o2.id : o1 === o2; 
  }
// INSERT_METHODS
}
