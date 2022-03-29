import { Component, OnInit, Input} from '@angular/core';
import { AppService } from '../../app.service';
import { Droga } from 'src/app/model/Droga.model';
import { DrogaService } from 'src/app/service/droga.service';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng-lts/api';

@Component({
  selector: 'di-droga-list',
  templateUrl: './droga-list.component.html',
  styleUrls: ['./droga-list.component.css']
})
export class DrogaListComponent implements OnInit {

  @Input() modal : any
  showLoading: boolean = true
  cols : any[]
  example : Droga
  exampleConfirmed : Droga

  list: Droga[] = []
  totalRecords : number = 0
  listOrder = "id"
  event : any


  constructor(private drogaService: DrogaService,
    public config: DynamicDialogConfig,
    public ref: DynamicDialogRef, 
              private app: AppService) { }

  ngOnInit() {
    this.app.setTitle('Droga')
    this.app.setMap(['Droga', 'Lista'])

    this.example = new Droga()

    this.cols = [
      { field: 'id', header: '#' , width: 50 },
      { field: 'nome', header: 'Nome' },
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
    return this.drogaService.getByExample(this.example).then(list => {
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
    this.drogaService.getListagem(open,this.exampleConfirmed).then(_ => this.showLoading = false)
  }

  closeModal(modal){
    // @ts-ignore
    $(modal).modal('hide')
  }

  clearFilter(){
    this.example = new Droga()
    this.listOrder = 'id'
// INSERT_CLEAR
    this.getList()
  }
}
