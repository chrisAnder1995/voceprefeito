import { saveAs } from 'file-saver';
import { AuthService } from './auth.service';
import { SERVICE } from '../app.api';
import { HttpClient, HttpResponse } from '@angular/common/http';

export class GenericService<T> {

  constructor(protected http: HttpClient, protected nomeClasse: string, protected authService : AuthService) {
    this.nomeClasse = nomeClasse
  }
  
  getById(id : number): Promise<T>{
    return this.http.post(`${SERVICE}/${this.nomeClasse}/getById`, id)
                    .toPromise()
                    .catch(error=> {
                      if (error.status == 401){
                        console.log("Acesso não autorizado")
                        this.authService.expires()
                      } else{
                        console.log("Erro desconhecido")
                      }
                    })
                    .then(res => <T> res)
                    .then(data => { 
                      this.authService.reloadSession()
                      return data;
                    })
  }

  getAll(): Promise<T[]> {
    return this.http.post(`${SERVICE}/${this.nomeClasse}/getAll`, JSON.stringify(""))
                    .toPromise()
                    .catch(error=> {
                      if (error.status == 401){
                        console.log("Acesso não autorizado")
                        this.authService.expires()
                      } else{
                        console.log("Erro desconhecido")
                      }
                    })
                    .then(res => <T[]> res )
                    .then(data => { 
                      this.authService.reloadSession()
                      return data;
                    })
                    
  }

  getAllFast(): Promise<T[]> {
    return this.http.post(`${SERVICE}/${this.nomeClasse}/getAllFast`, JSON.stringify(""))
                    .toPromise()
                    .catch(error=> {
                      if (error.status == 401){
                        console.log("Acesso não autorizado")
                        this.authService.expires()
                      } else{
                        console.log("Erro desconhecido")
                      }
                    })
                    .then(res => <T[]> res )
                    .then(data => { 
                      this.authService.reloadSession()
                      return data;
                    })
                    
  }
  
  getByExample(entity : T): Promise<T[]>{
    return this.http.post(`${SERVICE}/${this.nomeClasse}/getByExample`, JSON.stringify(entity))
                    .toPromise()
                    .catch(error=> {
                      if (error.status == 401){
                        console.log("Acesso não autorizado")
                        this.authService.expires()
                      } else{
                        console.log("Erro desconhecido")
                      }
                    })
                    .then(res => <T[]> res)
                    .then(data => { 
                      this.authService.reloadSession()
                      return data;
                    })
  }

  create(entity : T) : Promise<T> {
    return this.http.post(`${SERVICE}/${this.nomeClasse}/create`, JSON.stringify(entity))
                    .toPromise()
                    .catch(error=> {
                      if (error.status == 401){
                        console.log("Acesso não autorizado")
                        this.authService.expires()
                      } else{
                        console.log("Erro desconhecido")
                      }
                    })
                    .then(response => <T> response)
                    .then(data => { 
                      this.authService.reloadSession()
                      return data;
                    })
  }

  update(entity: T): Promise<T> {
    return this.http.post(`${SERVICE}/${this.nomeClasse}/update`, JSON.stringify(entity))
                    .toPromise()
                    .catch(error=> {
                      if (error.status == 401){
                        console.log("Acesso não autorizado")
                        this.authService.expires()
                      } else{
                        console.log("Erro desconhecido")
                      }
                    })
                    .then(res => <T> res)
                    .then(data => { 
                      this.authService.reloadSession()
                      return data;
                    })
  }


  async getListagem(open?:boolean, example?:T){
    let json : string = JSON.stringify("")
    if (example != undefined){
      json = JSON.stringify(example)
    }
    return this.http.post(`${SERVICE}/${this.nomeClasse}/listagem`, json, {observe: 'response', responseType: 'blob'})
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
                        var file = new Blob([response.body], {type: 'application/pdf'});
                        var fileURL = URL.createObjectURL(file);
                        this.authService.reloadSession()
                        if (open){
                          window.open(fileURL);
                        } else{
                          saveAs(response.body, "lista"+this.nomeClasse+'.pdf');
                        }
                    })
  }
  atendimentoPDF(id,open?:boolean){
        return this.http.post(`${SERVICE}/atendimento/atendimentoPDF`, id, {observe: 'response', responseType: 'blob'})
                        .subscribe((response)=>{
                          var file = new Blob([response.body], {type: 'application/pdf'});
                          var fileURL = URL.createObjectURL(file);
                          this.authService.reloadSession()
                          if (open){
                            window.open(fileURL);
                          } else{
                            saveAs(response.body, "atendimento"+id+".pdf");
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
