import { Component, OnInit,Input } from '@angular/core';
import { AppService } from '../../app.service';
import { Bairro } from 'src/app/model/Bairro.model';
import { BairroService } from 'src/app/service/bairro.service';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng-lts/api';

@Component({
  selector: 'di-bairro-list',
  templateUrl: './bairro-list.component.html',
  styleUrls: ['./bairro-list.component.css']
})
export class BairroListComponent implements OnInit {
  
  @Input() modal : any
  showLoading: boolean = true
  cols : any[]
  example : Bairro
  exampleConfirmed : Bairro

  list: Bairro[] = []
  totalRecords : number = 0
  listOrder = "id"
  event : any
  constructor(private bairroService: BairroService,
    public config: DynamicDialogConfig,
    public ref: DynamicDialogRef, 
              private app: AppService) { }

  ngOnInit() {
    this.app.setTitle('Bairro')
    this.app.setMap(['Bairro', 'Lista'])

    this.example = new Bairro()

    // this.getList()

    this.cols = [
      { field: 'id', header: '#' , width: 50 },
      { field: 'nome', header: 'Nome' },
      { field: 'cidade', header: 'Cidade' },
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
    return this.bairroService.getByExample(this.example).then(list => {
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
    this.bairroService.getListagem(open,this.exampleConfirmed).then(_ => this.showLoading = false)
  }

  closeModal(modal){
    // @ts-ignore
    $(modal).modal('hide')
  }

  clearFilter(){
    this.example = new Bairro()
    this.listOrder = 'id'
// INSERT_CLEAR
    this.getList()
  }

}
