import { Component, OnInit, Input } from '@angular/core';
import { AppService } from '../../app.service';
import { OrientacaoSexual } from 'src/app/model/OrientacaoSexual.model';
import { OrientacaoSexualService } from 'src/app/service/orientacaoSexual.service';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng-lts/api';

@Component({
  selector: 'di-orientacaoSexual-list',
  templateUrl: './orientacaoSexual-list.component.html',
  styleUrls: ['./orientacaoSexual-list.component.css']
})
export class OrientacaoSexualListComponent implements OnInit {
  
  @Input() modal : any
  showLoading: boolean = true
  cols : any[]
  example : OrientacaoSexual
  exampleConfirmed : OrientacaoSexual

  list: OrientacaoSexual[] = []
  totalRecords : number = 0
  listOrder = "id"
  event : any

  constructor(private orientacaoSexualService: OrientacaoSexualService,
    public config: DynamicDialogConfig,
    public ref: DynamicDialogRef, 
              private app: AppService) { }

  ngOnInit() {
    this.app.setTitle('Orientação Sexual')
    this.app.setMap(['Orientação Sexual', 'Lista'])

    this.example = new OrientacaoSexual()

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
    return this.orientacaoSexualService.getByExample(this.example).then(list => {
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
    this.orientacaoSexualService.getListagem(open,this.exampleConfirmed).then(_ => this.showLoading = false)
  }

  closeModal(modal){
    // @ts-ignore
    $(modal).modal('hide')
  }

  clearFilter(){
    this.example = new OrientacaoSexual()
    this.listOrder = 'id'
// INSERT_CLEAR
    this.getList()
  }
}
