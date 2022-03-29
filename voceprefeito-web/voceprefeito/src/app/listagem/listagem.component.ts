import { AtendimentoService } from 'src/app/service/atendimento.service';
import { BairroService } from 'src/app/service/bairro.service';
import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { Atendimento } from 'src/app/model/Atendimento.model';
import { Cidade } from '../model/Cidade.model';
import { CidadeService } from '../service/cidade.service';
import { Bairro } from '../model/Bairro.model';
import { LocalOcorrencia } from '../model/LocalOcorrencia.model';
import { LocalOcorrenciaService } from '../service/localOcorrencia.service';
import { ListagemPojo } from '../model/Listagem.model';
import { SERVICE } from '../app.api';

@Component({
  selector: 'di-listagem-list',
  templateUrl: './listagem.component.html',
  styleUrls: ['./listagem.component.css']
})
export class ListagemComponent implements OnInit {
  list: Atendimento[] = []
  locaisOcorrencia : LocalOcorrencia[] = []
  cidade_id : number
  bairro_id : number
  cidades : Cidade[]
  bairros : Bairro[]
  idadeInicio : number
  idadeFim : number
  dataOcorrenciaInicio : Date
  dataOcorrenciaFim : Date
  dataCadastroInicio : Date
  dataCadastroFim : Date
  rendaInicio : number
  rendaFim : number
  violenciaCategoria : string

  constructor(private app: AppService,
              private cidadeService : CidadeService,
              private bairroService : BairroService,
              private localOcorrenciaService : LocalOcorrenciaService,
              private atendimentoService : AtendimentoService) { }

  ngOnInit() {
    this.app.setTitle('Listagem')
    this.app.setMap(['Relatório', 'Listagem'])

    this.getListas()
  }

  async getListas(){
    await this.cidadeService.getAllFast().then(list=>{
      this.cidades = list
    })
    await this.bairroService.getAllFast().then(list=>{
      this.bairros = list
    })
    await this.localOcorrenciaService.getAllFast().then(list=>{
      this.locaisOcorrencia = list
    })
  }


  print(open?:boolean){
    let listagem = new ListagemPojo()
    
    if (this.violenciaCategoria != undefined && this.violenciaCategoria != ""){
      listagem.violenciaCategoria = this.violenciaCategoria
    }
    if (this.cidade_id != undefined && this.cidade_id > 0){
      let cidade = new Cidade()
      cidade.id = this.cidade_id

      listagem.cidade = cidade
    }
    if (this.bairro_id != undefined && this.bairro_id > 0){
      let bairro = new Bairro()
      bairro.id = this.bairro_id

      listagem.bairro = bairro
    }
    if (this.idadeInicio != undefined && this.idadeInicio > 0){
      listagem.idadeInicio = this.idadeInicio
    }
    if (this.idadeFim != undefined && this.idadeFim > 0){
      listagem.idadeFim = this.idadeFim
    }
    if (this.rendaInicio != undefined && this.rendaInicio > 0){
      listagem.rendaInicio = this.rendaInicio
    }
    if (this.rendaFim != undefined && this.rendaFim > 0){
      listagem.rendaFim = this.rendaFim
    }
    if (this.dataCadastroInicio != undefined ){
      listagem.dataCadastroInicio = this.dataCadastroInicio
    }
    if (this.dataCadastroFim != undefined ){
      listagem.dataCadastroFim = this.dataCadastroFim
    }
    if (this.dataOcorrenciaInicio != undefined ){
      listagem.dataOcorrenciaInicio = this.dataOcorrenciaInicio
    }
    if (this.dataOcorrenciaFim != undefined ){
      listagem.dataOcorrenciaFim = this.dataOcorrenciaFim
    }
    
    let locais = []
    for (let lo of this.locaisOcorrencia){
      if (lo.selecionado){
        locais.push(lo)
      }
    }

    if (locais.length > 0){
      listagem.locaisOcorrencia = locais
    }

    this.atendimentoService.downloadListagem(listagem,open)
    //window.open(`${SERVICE}/atendimento/listagemGETPDF/` + json + '/Relatório.pdf', '_blank', ''); 
  }
}
