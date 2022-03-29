import { Atendimento } from '../model/Atendimento.model';
import { Injectable } from "../../../node_modules/@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router } from "../../../node_modules/@angular/router";
import { GenericService } from "./generic.service";
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';
import { saveAs } from 'file-saver';

@Injectable()
export class AtendimentoService extends GenericService<Atendimento> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'atendimento', authService)
      }


      downloadListagem(listagem,open?:boolean){
        return this.http.post(`${SERVICE}/${this.nomeClasse}/listagem`, JSON.stringify(listagem), {observe: 'response', responseType: 'blob'})
                        .subscribe((response)=>{
                          var file = new Blob([response.body], {type: 'application/pdf'});
                          var fileURL = URL.createObjectURL(file);
                          this.authService.reloadSession()
                          if (open){
                            window.open(fileURL);
                          } else{
                            saveAs(response.body, "lista"+this.nomeClasse+'.pdf');
                          }
                        },(error=> {
                          if (error.status == 401){
                            console.log("Acesso não autorizado")
                            this.authService.expires()
                          } else{
                            console.log("Erro desconhecido")
                          }
                        }))
      }
  
      auxilioLegalPDF(id,open?:boolean){
        return this.http.post(`${SERVICE}/${this.nomeClasse}/auxilioLegalPDF`, id, {observe: 'response', responseType: 'blob'})
                        .subscribe((response)=>{
                          var file = new Blob([response.body], {type: 'application/pdf'});
                          var fileURL = URL.createObjectURL(file);
                          this.authService.reloadSession()
                          if (open){
                            window.open(fileURL);
                          } else{
                            saveAs(response.body, "auxiliolegal"+id+".pdf");
                          }
                        },(error=> {
                          if (error.status == 401){
                            console.log("Acesso não autorizado")
                            this.authService.expires()
                          } else{
                            console.log("Erro desconhecido")
                          }
                        }))
      }

}
