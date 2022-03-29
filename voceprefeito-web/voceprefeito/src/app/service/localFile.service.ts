import { LocalFile } from "../model/LocalFile.model";
import { Injectable } from "../../../node_modules/@angular/core";
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Router } from "../../../node_modules/@angular/router";
import { GenericService } from "./generic.service";
import { SERVICE } from "../app.api";
import { AuthService } from "./auth.service";
import { saveAs } from 'file-saver';

@Injectable()
export class LocalFileService extends GenericService<LocalFile> {

    constructor(protected http: HttpClient, private router: Router, protected authService : AuthService){
        super(http, 'localFile', authService)
    }

    async download(file : LocalFile){
      return this.http.post(`${SERVICE}/${this.nomeClasse}/file`, file.id, {observe: 'response', responseType: 'blob'})
                      .toPromise()
                      .catch(error=> {
                        if (error.status == 401){
                          console.log("Acesso não autorizado")
                          this.authService.expires()
                        } else{
                          console.log("Erro desconhecido")
                        }
                      })
                      .then(res => <HttpResponse<Blob>> res)
                      .then((response)=>{
                          let type = "image/*"
                          if (file.tipo == 'pdf'){
                            type = "application/pdf"
                          }
                          var f = new Blob([response.body], {type: type});
                          var fileURL = URL.createObjectURL(f);
                          this.authService.reloadSession()
                          saveAs(response.body, file.nome);
                      })
    }
}
