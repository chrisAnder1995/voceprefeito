import { UserGroups } from './../../model/UserGroups.model';
import { UserGroupsService } from './../../service/userGroups.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, NgForm } from '../../../../node_modules/@angular/forms';
import { ActivatedRoute, Router } from '../../../../node_modules/@angular/router';
import { sha256 } from '../../../../node_modules/js-sha256';
import { AppService } from '../../app.service';
import { Usuario } from '../../model/Usuario.model';
import { UsuarioService } from '../../service/usuario.service';
import { PERMISSAO } from '../../model/enum/Enumeration.api.enum';
import { Location } from '@angular/common';

@Component({
  selector: 'di-usuario-form',
  templateUrl: './usuario-form.component.html'
})
export class UsuarioFormComponent implements OnInit {
  @Input() modal : any
  @Output() modalResult = new EventEmitter();
  
  id : number = 0
  usuarioForm: FormGroup
  form: NgForm
  usuario = new Usuario()
  showLoading: boolean = true
  senha: string = ""
  senhaConfirm: string = ""
  message: string = ""
  permissao: string = ""
  successMessage: boolean = false
  errorMessage: boolean = false

  userGroups : UserGroups[] = []

  saveDisabled = false

  constructor(private router: Router, 
              private usuarioService: UsuarioService, 
              private userGroupService: UserGroupsService, 
              private appService: AppService,
              private location : Location,
              private route: ActivatedRoute) { }

  ngOnInit() {
    if (this.modal == undefined){
      this.appService.setTitle('Usuário')
      this.appService.setMap(['Usuário', 'Cadastro'])

      this.userGroupService.getAllFast().then(grupos =>{
        this.userGroups = grupos
      })

      this.id = this.route.snapshot.params['id']
      if(this.id > 0) {
        this.usuarioService.getById(this.id).then(usuario => {
          this.showLoading = false
          this.usuario = usuario
          this.permissao = this.usuario.permissao
        })
      } else{
        this.showLoading = false
      }
    }
  }

  closeForm() {
    if (this.modal == undefined){
      this.location.back();
    }
  }

  delete(){
    this.usuario.id = this.id
    if(this.usuario.id != undefined && this.usuario.id > 0) {
      this.usuario.ativo = false
      this.usuarioService.update(this.usuario).then(usuario => {
        if (usuario !== undefined){
          this.router.onSameUrlNavigation = 'reload'
          this.message = "Registro excluído com sucesso"
          this.successMessage = true
          this.modalResult.emit(usuario)
          this.clean()

          if (this.modal == undefined){
            this.router.navigate(['usuario'])
          }
        } else{
          this.errorMessage = true
          this.message = "Ocorreu um erro, favor tentar novamente"
        }
      })
    }
  }

  save(ngForm : NgForm){
    if(ngForm.valid && (this.usuario.id != undefined || (this.usuario.id == undefined && this.senha != ""))) {
      if(this.validatePassword()) {
        this.saveDisabled = true
        this.usuario.permissao = this.permissao
        if (this.senha != undefined && this.senha != ""){
          this.usuario.senha = sha256(this.senha).toUpperCase()
        }
        
        if(this.usuario.id === undefined) {
          this.usuario.ativo = true

          this.usuarioService.create(this.usuario).then(usuario => {
            this.saveDisabled = false
            if (usuario !== undefined){
              this.router.onSameUrlNavigation = 'reload'
              this.message = "Os dados foram salvos com sucesso"
              this.successMessage = true
              this.modalResult.emit(usuario)
              this.clean()

              if (this.modal == undefined){
                this.router.navigate(['usuario'])
              }
              
            } else{
              this.errorMessage = true
              this.message = "Ocorreu um erro. Tente novamente mais tarde"
            }
          })
        } else {
          
          this.usuarioService.update(this.usuario).then(usuario => {
            this.saveDisabled = false
            if (usuario !== undefined){
              this.router.onSameUrlNavigation = 'reload'
              this.successMessage = true
              this.modalResult.emit(usuario)
              this.message = "Dados atualizados com sucesso"

              if (this.modal == undefined){
                this.router.navigate(['usuario'])
              }
            } else{
              this.errorMessage = true
              this.message = "Ocorreu um erro. Tente novamente mais tarde"
            }
          })
        }
      } else {
        this.errorMessage = true
        this.message = "A senha e sua confirmação devem ser iguais"
      }
    } else {
      this.errorMessage = true
      this.message = "Todos os campos são obrigatórios"
    }
  }

  toEdit(usuario: Usuario) {
    this.usuario = usuario
    usuario = undefined
  }

  clean() {
    this.usuario = new Usuario()
  }

  allPermissoes(): string[] {
    return Object.keys(PERMISSAO)
  }
  
  compareFn(o1: any, o2:any): boolean {     
    return o1 && o2 ? o1.id === o2.id : o1 === o2; 
  }

  permissaoString(item) : string{
    return PERMISSAO[item]
  }

  validatePassword(): boolean {
    let valid = false
    if(this.senha === this.senhaConfirm) {
      valid = true
    }

    return valid
  }
}
