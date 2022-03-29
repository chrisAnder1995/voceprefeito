import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PERMISSAO } from '../../../model/enum/Enumeration.api.enum';
import { Location } from '@angular/common';
import { UserGroups } from 'src/app/model/UserGroups.model';
import { UserGroupsService } from 'src/app/service/userGroups.service';
import { UsuarioService } from 'src/app/service/usuario.service';
import { AppService } from 'src/app/app.service';
import { Usuario } from 'src/app/model/Usuario.model';

@Component({
  selector: 'di-user-groups',
  templateUrl: './user-groups.html',
  styleUrls: ['./user-groups.css']
})
export class UserGroupFormComponent implements OnInit {
  @Input() modal : any
  @Output() modalResult = new EventEmitter();
  
  id : number = 0
  userForm: FormGroup
  form: NgForm
  user = new Usuario()
  showLoading: boolean = true
  password: string = ""
  passwordConfirm: string = ""
  message: string = ""
  permission: string = ""
  successMessage: boolean = false
  errorMessage: boolean = false

  urlService: string

  saveDisabled = false

  userGroup = new UserGroups()


  constructor(private router: Router, 
              private userService: UsuarioService, 
              private appService: AppService,
              private location : Location,
              private route: ActivatedRoute,
              private userGroupService: UserGroupsService) { }

  ngOnInit() {
      this.appService.setTitle('Grupos de Usuários')
      this.appService.setMap(['Grupos de Usuários', 'Registro'])


      this.id = this.route.snapshot.params['id']
      
      if(this.id > 0) {
        this.userGroupService.getById(this.id).then(userGroup => {
          this.showLoading = false
          this.userGroup = userGroup
        })
      } else{
        this.userGroup.totalAccess = false
        this.showLoading = false
      }
  }

  closeForm() {
    if (this.modal == undefined){
      this.location.back();
    }
  }

  delete(){
    this.user.id = this.id
    if(this.user.id != undefined && this.user.id > 0) {
      this.userService.update(this.user).then(user => {
        if (user !== undefined){
          this.router.onSameUrlNavigation = 'reload'
          this.message = "Register deleted with sucess"
          this.successMessage = true
          this.modalResult.emit(user)

          if (this.modal == undefined){
            this.router.navigate(['user'])
          }
        } else{
          this.errorMessage = true
          this.message = "An error has occurred. Please try again."
        }
      })
    }
  }

  save(ngForm: NgForm){
    if(ngForm.valid) {
      this.saveDisabled = true

      // FORM_SAVE

      if (this.id != undefined && this.id == 0){
        this.id = undefined
      }

      this.userGroup.ativo = true
      this.userGroup.id = this.id

      if(this.userGroup.id === undefined || this.userGroup.id == 0) {
        
        this.userGroupService.create(this.userGroup).then(userGroup => {
          this.saveDisabled = false
          this.userGroup = userGroup
          if (userGroup !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Your data has been saved"
            this.successMessage = true
            this.modalResult.emit(userGroup)

            if (this.modal == undefined){
              this.router.navigate(['grupos'])
            }

          } else{
            this.errorMessage = true
            this.message = "An error has occurred. Please try again."
          }
        })
      } else {
        this.userGroupService.update(this.userGroup).then(userGroup => {
          this.saveDisabled = false
          if (userGroup !== undefined){
            this.router.onSameUrlNavigation = 'reload'
            this.message = "Your data has been saved"
            this.modalResult.emit(userGroup)

            if (this.modal == undefined){
              this.router.navigate(['grupos'])
            }
          } else{
            this.message = "An error has occurred. Please try again."
          }
        })
      }
    } else {
      this.errorMessage = true
      this.message = "There are required fields not filled in"
    }
  }

  
 cancel(){
  this.userGroup.ativo = false
  this.userGroupService.update(this.userGroup).then(userGroup => {
    if (userGroup !== undefined){
      this.router.onSameUrlNavigation = 'reload'
      this.message = "Your data has been saved"
      this.modalResult.emit(userGroup)

      if (this.modal == undefined){
        this.router.navigate(['userGroups'])
      }
    } else{
      this.message = "An error has occurred. Please try again."
    }
  })
}

 
  

  allPermissoes(): string[] {
    return Object.keys(PERMISSAO)
  }

  permissionString(item) : string{
    return PERMISSAO[item]
  }

  validatePassword(): boolean {
    let valid = false
    if(this.password === this.passwordConfirm) {
      valid = true
    }

    return valid
  }


}
