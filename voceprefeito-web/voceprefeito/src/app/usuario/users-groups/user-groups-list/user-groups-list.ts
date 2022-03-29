import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { AppService } from '../../../app.service';
import { ActivatedRoute } from '../../../../../node_modules/@angular/router';
import { UserGroupsService } from 'src/app/service/userGroups.service';
import { UserGroups } from 'src/app/model/UserGroups.model';
// INSERT_IMPORTS

@Component({
  selector: 'di-UserGroup-list',
  templateUrl: './user-groups-list.html',
})

export class UserGroupsListComponent implements OnInit {
  @Input() modal : any
  @Output() modalResult = new EventEmitter();

  example : UserGroups
  exampleConfirmed : UserGroups

  list: UserGroups[] = []
  showLoading: boolean = true
  cols : any[]
  totalRecords : number = 0
  listOrder = "description"
  event : any

// INSERT_VARIABLES

  constructor(private route: ActivatedRoute,
              private userGroupService: UserGroupsService,
              private app: AppService) {
                route.params.subscribe(val => {
                })
  }

  ngOnInit() {
    if (this.modal == undefined){
      this.app.setTitle('Grupos de Usuário')
      this.app.setMap(['Grupos de Usuário', 'Lista'])
    }

    this.example = new UserGroups()

    //this.getList()

// INSERT_INIT

    this.cols = [
      { field: 'id', header: '#' , width: 50 },
// INSERT_LISTA
    ];
  }

  modalSelect(_){
    this.modalResult.emit(this.example)
  }

  clearFilter(){
    this.example = new UserGroups()
    this.listOrder = 'id'
// INSERT_CLEAR
    this.getAllUser()
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
      return this.userGroupService.getByExample(this.example).then(list => {
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
  loadListLazy(event){
    this.event = event
    this.getAllUser()
  }

  getAllUser() {
    return this.userGroupService.getAllFast().then(user => {
      this.list = user
      this.showLoading = false
    })
  }

  downloadListagemPDF(open?: boolean){
    this.showLoading = true
    this.userGroupService.getListagem(open,this.exampleConfirmed).then(_ => this.showLoading = false)
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
// INSERT_METHODS
}
