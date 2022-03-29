import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit,Input } from '@angular/core';
import { AppService } from '../../app.service';
import { DialogService, DynamicDialogConfig, DynamicDialogRef, MenuItem } from 'primeng-lts/api';
import { AtendimentoService } from '../../service/atendimento.service';
import { Atendimento } from '../../model/Atendimento.model';
import { PoliticaService } from '../../service/politica.service';
import { Politica } from '../../model/Politica.model';
import { Usuario } from '../../model/Usuario.model';
import { Regiao } from '../../model/Regiao.model';
import { RegiaoService } from '../../service/Regiao.service';
import { Bairro } from '../../model/Bairro.model';
import {Chart} from '../../../../node_modules/chart.js'
import { utf8Encode } from '@angular/compiler/src/util';
import { Servico } from '../../model/Servico.model';

@Component({
  selector: 'di-atendimento-publi-list',
  templateUrl: './atendimento-publi-list.component.html',
  styleUrls: ['./atendimento-publi-list.component.css']
})
export class SolicitacaoListComponent implements OnInit {

  @Input() modal : any
  showLoading: boolean = true
  cols : any[]
  example : Atendimento
  exampleConfirmed : Atendimento

  list: Atendimento[] = []
  totalRecords : number = 0
  listOrder = "id"
  event : any

  orderAsc = false

  cpf : string

  btnNovaDenuncia: MenuItem[];
  btnNovaSuspeita: MenuItem[];
  btnSuspeitaDescartada: MenuItem[];
  btnEmSolicitacao: MenuItem[];
  btnFechado: MenuItem[];

  politicas :  Politica[]=[]
  politica :  Politica

  myChart : Chart[]=[]

  selected : Atendimento

  atendimentoAss : Atendimento[]=[]
  atendimentoCult : Atendimento[]=[]
  atendimentoHab : Atendimento[]=[]
  atendimentoSaude : Atendimento[]=[]
  atendimentoInfra : Atendimento[]=[]
  atendimentoEdc : Atendimento[]=[]
  atendimentoEsp : Atendimento[]=[]
  atendimentoMeio : Atendimento[]=[]
  atendimentoMob : Atendimento[]=[]

  assistencia : number = 0
  cultura : number = 0
  habitacao : number = 0 
  saude : number = 0
  infraestrutura : number = 0
  educacao : number = 0
  esporte : number = 0 
  meioambiente : number = 0
  mobilidade : number = 0

  svv1 : number = 0
  svv2 : number = 0
  svv3 : number = 0
  svv4 : number = 0
  svv5 : number = 0
  svv6 : number = 0
  svv7 : number = 0
  svv8 : number = 0
  svv9 : number = 0
  svv10 : number = 0
  svv11 : number = 0
  svv12 : number = 0
  svv13 : number = 0
  svv14 : number = 0
  svv15 : number = 0
  svv16 : number = 0
  svv17 : number = 0
  svv18 : number = 0
  svv19 : number = 0
  svv20 : number = 0
  svv21 : number = 0
  svv22 : number = 0
  svv23 : number = 0
  svv24 : number = 0
  svv25 : number = 0
  svv26 : number = 0
  svv27 : number = 0
  svv28 : number = 0
  svv29 : number = 0
  svv30 : number = 0
  svv31 : number = 0
  svv32 : number = 0
  svv33 : number = 0
  svv34 : number = 0
  svv35 : number = 0
  svv36 : number = 0
  svv37 : number = 0
  svv38 : number = 0
  svv39 : number = 0
  svv40 : number = 0
  svv41 : number = 0
  svv42 : number = 0
  svv43 : number = 0
  svv44 : number = 0
  svv45 : number = 0
  svv46 : number = 0
  svv47 : number = 0
  svv48 : number = 0
  svv49 : number = 0
  svv50 : number = 0
  svv51 : number = 0
  svv52 : number = 0
  svv53 : number = 0
  svv54 : number = 0
  svv55 : number = 0
  svv56 : number = 0
  svv57 : number = 0
  svv58 : number = 0
  svv59 : number = 0
  svv60 : number = 0
  svv61 : number = 0
  svv62 : number = 0
  svv63 : number = 0
  svv64 : number = 0
  svv65 : number = 0
  svv66 : number = 0
  svv67 : number = 0
  svv68 : number = 0
  svv69 : number = 0
  svv70 : number = 0
  svv71 : number = 0
  svv72 : number = 0
  svv73 : number = 0
  svv74 : number = 0
  svv75 : number = 0
  svv76 : number = 0
  

  rpp1 : number = 0
  rpp2 : number = 0
  rpp3 : number = 0 
  rpp4 : number = 0
  rpp5 : number = 0
  rpp6 : number = 0
  rpp7 : number = 0
  rpp8 : number = 0
  rpp9 : number = 0 
  rpp10 : number = 0
  rpp11 : number = 0
  rpp12 : number = 0
  rpp13 : number = 0
  rpp14 : number = 0

  blockBtn : boolean = false

  numAtendimento : number = 0

  avaliacao : number = 0

  regioes : Regiao[] = []
  regiao : Regiao

  window: any

  testeView : boolean = false

  atendimentos :  Atendimento[]=[]

  colors = [
  "#FF69B4",
  "#FFA500",
  "#FF8068",
  "#FF4262",
  "#4BC0C0",
  "#FFCE56",
  "#D8BFD8",
  "#36A2EB",
  "#AA00FF",
  "#40E0D0",
  "#FF00AA",
  "#4D433D",
  "#5D4157",
  "#0C7E7E"
]


  constructor(
    private atendimentoService : AtendimentoService,
    public config: DynamicDialogConfig,
    public dialogService: DialogService,
    public politicaService : PoliticaService, 
    public regiaoService : RegiaoService,
    public ref: DynamicDialogRef, 
    private route: ActivatedRoute,
              private app: AppService,
              private router : Router) { }

  ngOnInit() {
    
    this.cpf =  this.route.snapshot.paramMap.get('id')

    this.app.setTitle('Formulários')
    this.app.setMap(['Formulários', 'Lista de'])

    this.example = new Atendimento()

    this.politicaService.getAllFast().then(result => {
      this.politicas = result
    })

    this.regiaoService.getAll().then(result => {
      this.regioes = result
    })

    this.getList()
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

  getListByRegiao(){
    this.blockBtn = true
    this.testeView = true
    this.clearDash()
    this.example.usuario = new Usuario
    this.example.usuario.bairro = new Bairro
    this.example.usuario.bairro.regiao = new Regiao
    this.example.usuario.bairro.regiao = this.regiao
    this.getListNovo()
  }

  getListByPolitica(){
    this.testeView = true
    this.clearDash()
    this.example.usuario = new Usuario
    this.example.usuario.bairro = new Bairro
    this.example.usuario.bairro.regiao = new Regiao
    this.example.usuario.bairro.regiao = this.regiao
    this.example.servicoUm = new Servico
    this.example.servicoDois = new Servico
    this.example.servicoTres = new Servico
    this.example.servicoUm.politica = new Politica
    this.example.servicoDois.politica = new Politica
    this.example.servicoTres.politica = new Politica
    this.example.servicoUm.politica = this.politica
    this.example.servicoDois.politica = this.politica
    this.example.servicoTres.politica = this.politica
    this.getListNovo()
  }

  clear(){
    this.blockBtn = false
    this.testeView = false
    this.clearDash()
    this.example = new Atendimento
    this.example.ativo = true
    this.getList()
  }

    chartPoliticas(){
      this.myChart = new Chart("politicas", {
        type: 'bar',
        data: {
            labels: ['Assistencia Social', 'Educação', 'Habitação', 'Saúde', 'Infraestrutura', 'Esporte','Cultura', 'Meio Ambiente', 'Mobilidade Urbana',],
            datasets: [{
                data: [this.assistencia, this.educacao, this.habitacao, this.saude, this.infraestrutura, this.esporte, this.cultura, this.meioambiente, this.mobilidade],
                backgroundColor: this.colors,
                hoverBackgroundColor: this.colors
            }]
        },
        options: {
          legend: {
            display:false
          },
          hoverBorderWidth:1,
          hoverBorderRadius: 0,
          scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
    }

    chartRegioes(){
    this.myChart = new Chart("regioes", {
      type: 'pie',
      data: {
          labels: ['1ª RPP', '2ª RPP', '3ª RPP', '4ª RPP', '5ª RPP', '6ª RPP','7ª RPP', '8ª RPP', '9ª RPP', '10ª RPP','11ª RPP','12ª RPP','13ª RPP','14ª RPP',],
          datasets: [{
              data: [this.rpp1, this.rpp2, this.rpp3,this.rpp4, this.rpp5, this.rpp6, this.rpp7, this.rpp8, this.rpp9, this.rpp10, this.rpp11, this.rpp12, this.rpp13, this.rpp14],
              backgroundColor: this.colors,
              hoverBackgroundColor: this.colors
          }]
      },
      options: {
          scales: {
              y: {
                  beginAtZero: true
              }
          }
      }
    });
    }

    chartPoliticasNovo(){
      this.myChart = new Chart("politicasNovo", {
        type: 'bar',
        data: {
            labels: ['Assistencia Social', 'Educação', 'Habitação', 'Saúde', 'Infraestrutura', 'Esporte','Cultura', 'Meio Ambiente', 'Mobilidade Urbana',],
            datasets: [{
                data: [this.assistencia, this.educacao, this.habitacao, this.saude, this.infraestrutura, this.esporte, this.cultura, this.meioambiente, this.mobilidade],
                backgroundColor: this.colors,
                hoverBackgroundColor: this.colors
            }]
        },
        options: {
          legend: {
            display:false
         },
         hoverBorderWidth:1,
         hoverBorderRadius: 0,
         scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }
  
  chartRegioesNovo(){
    this.myChart = new Chart("regioesNovo", {
      type: 'pie',
      data: {
          labels: ['1ª RPP', '2ª RPP', '3ª RPP', '4ª RPP', '5ª RPP', '6ª RPP','7ª RPP', '8ª RPP', '9ª RPP', '10ª RPP','11ª RPP','12ª RPP','13ª RPP','14ª RPP',],
          datasets: [{
              data: [this.rpp1, this.rpp2, this.rpp3,this.rpp4, this.rpp5, this.rpp6, this.rpp7, this.rpp8, this.rpp9, this.rpp10, this.rpp11, this.rpp12, this.rpp13, this.rpp14],
              backgroundColor: this.colors,
              hoverBackgroundColor: this.colors
          }]
      },
      options: {
          scales: {
              y: {
                  beginAtZero: true
              }
          }
      }
  });
  }

clearDash(){
    this.rpp1  = 0
    this.rpp2  = 0
    this.rpp3  = 0 
    this.rpp4  = 0
    this.rpp5  = 0
    this.rpp6  = 0
    this.rpp7  = 0
    this.rpp8  = 0
    this.rpp9  = 0 
    this.rpp10  = 0
    this.rpp11  = 0
    this.rpp12  = 0
    this.rpp13  = 0
    this.rpp14  = 0

    this.assistencia  = 0
    this.educacao  = 0
    this.habitacao  = 0 
    this.cultura  = 0
    this.saude = 0
    this.infraestrutura  = 0
    this.esporte  = 0
    this.meioambiente  = 0
    this.mobilidade  = 0
    
    
}

clearServicos(){
  this.svv1 = 0
    this.svv2 = 0
    this.svv3 = 0
    this.svv4 = 0
    this.svv5 = 0
    this.svv6 = 0
    this.svv7 = 0
    this.svv8 = 0
    this.svv9 = 0
    this.svv10 = 0
    this.svv11 = 0
    this.svv12 = 0
    this.svv13 = 0
    this.svv14 = 0
    this.svv15 = 0
    this.svv16 = 0
    this.svv17 = 0
    this.svv18 = 0
    this.svv19 = 0
    this.svv20 = 0
    this.svv21 = 0
    this.svv22 = 0
    this.svv23 = 0
    this.svv24 = 0
    this.svv25 = 0
    this.svv26 = 0
    this.svv27 = 0
    this.svv28 = 0
    this.svv29 = 0
    this.svv30 = 0
    this.svv31 = 0
    this.svv32 = 0
    this.svv33 = 0
    this.svv34 = 0
    this.svv35 = 0
    this.svv36 = 0
    this.svv37 = 0
    this.svv38 = 0
    this.svv39 = 0
    this.svv40 = 0
    this.svv41 = 0
    this.svv42 = 0
    this.svv43 = 0
    this.svv44 = 0
    this.svv45 = 0
    this.svv46 = 0
    this.svv47 = 0
    this.svv48 = 0
    this.svv49 = 0
    this.svv50 = 0
    this.svv51 = 0
    this.svv52 = 0
    this.svv53 = 0
    this.svv54 = 0
    this.svv55 = 0
    this.svv56 = 0
    this.svv57 = 0
    this.svv58 = 0
    this.svv59 = 0
    this.svv60 = 0
    this.svv61 = 0
    this.svv62 = 0
    this.svv63 = 0
    this.svv64 = 0
    this.svv65 = 0
    this.svv66 = 0
    this.svv67 = 0
    this.svv68 = 0
    this.svv69 = 0
    this.svv70 = 0
    this.svv71 = 0
    this.svv72 = 0
    this.svv73 = 0
    this.svv74 = 0
    this.svv75 = 0
    this.svv76 = 0
}


getList() {
    this.clearServicos()
    this.showLoading = true
    this.list = []
    this.example.ativo = true
    return this.atendimentoService.getByExample(this.example).then(list => {


     
      this.list  = list
      this.numAtendimento = this.list.length
      for(let atendimento of this.list){
        if(atendimento.politicaUm.id == 1){
          this.atendimentoAss.push(atendimento)
          this.assistencia++
        }
        if(atendimento.politicaUm.id == 2){
          this.atendimentoEdc.push(atendimento)
          this.educacao++
        }
        if(atendimento.politicaUm.id == 3){
          this.atendimentoHab.push(atendimento)
          this.habitacao++
        }
        if(atendimento.politicaUm.id == 4){
          this.atendimentoSaude.push(atendimento)
          this.saude++
        }
        if(atendimento.politicaUm.id == 5){
          this.atendimentoInfra.push(atendimento)
          this.infraestrutura++
        }
        if(atendimento.politicaUm.id == 6){
          this.atendimentoEsp.push(atendimento)
          this.esporte++
        }
        if(atendimento.politicaUm.id == 7){
          this.atendimentoCult.push(atendimento)
          this.cultura++
        }
        if(atendimento.politicaUm.id == 8){
          this.atendimentoMeio.push(atendimento)
          this.meioambiente++
        }
        if(atendimento.politicaUm.id == 9){
          this.atendimentoMob.push(atendimento)
          this.mobilidade++
        }
        if(atendimento.politicaDois.id == 1){
          this.atendimentoAss.push(atendimento)
          this.assistencia++
        }
        if(atendimento.politicaDois.id == 2){
          this.atendimentoEdc.push(atendimento)
          this.cultura++
        }
        if(atendimento.politicaDois.id == 3){
          this.atendimentoHab.push(atendimento)
          this.habitacao++
        }
        if(atendimento.politicaDois.id == 4){
          this.atendimentoSaude.push(atendimento)
          this.saude++
        }
        if(atendimento.politicaDois.id == 5){
          this.atendimentoInfra.push(atendimento)
          this.infraestrutura++
        }
        if(atendimento.politicaDois.id == 6){
          this.atendimentoEsp.push(atendimento)
          this.esporte++
        }
        if(atendimento.politicaDois.id == 7){
          this.atendimentoCult.push(atendimento)
          this.cultura++
        }
        if(atendimento.politicaDois.id == 8){
          this.atendimentoMeio.push(atendimento)
          this.meioambiente++
        }
        if(atendimento.politicaDois.id == 9){
          this.atendimentoMob.push(atendimento)
          this.mobilidade++
        }
        if(atendimento.politicaTres.id == 1){
          this.atendimentoAss.push(atendimento)
          this.assistencia++
        }
        if(atendimento.politicaTres.id == 2){
          this.atendimentoEdc.push(atendimento)
          this.cultura++
        }
        if(atendimento.politicaTres.id == 3){
          this.atendimentoHab.push(atendimento)
          this.habitacao++
        }
        if(atendimento.politicaTres.id == 4){
          this.atendimentoSaude.push(atendimento)
          this.saude++
        }
        if(atendimento.politicaTres.id == 5){
          this.atendimentoInfra.push(atendimento)
          this.infraestrutura++
        }
        if(atendimento.politicaTres.id == 6){
          this.atendimentoEsp.push(atendimento)
          this.esporte++
        }
        if(atendimento.politicaTres.id == 7){
          this.atendimentoCult.push(atendimento)
          this.cultura++
        }
        if(atendimento.politicaTres.id == 8){
          this.atendimentoMeio.push(atendimento)
          this.meioambiente++
        }
        if(atendimento.politicaTres.id == 9){
          this.atendimentoMob.push(atendimento)
          this.mobilidade++
       }
        if(atendimento.usuario.bairro.regiao.id == 1){
          this.rpp1++
        }
        if(atendimento.usuario.bairro.regiao.id == 2){
          this.rpp2++
        }
        if(atendimento.usuario.bairro.regiao.id == 3){
          this.rpp3++
        }
        if(atendimento.usuario.bairro.regiao.id == 4){
          this.rpp4++
        }
        if(atendimento.usuario.bairro.regiao.id == 5){
          this.rpp5++
        }
        if(atendimento.usuario.bairro.regiao.id == 6){
          this.rpp6++
        }
        if(atendimento.usuario.bairro.regiao.id == 7){
          this.rpp7++
        }
        if(atendimento.usuario.bairro.regiao.id == 8){
          this.rpp8++
        }
        if(atendimento.usuario.bairro.regiao.id == 9){
          this.rpp9++
        }
        if(atendimento.usuario.bairro.regiao.id == 10){
          this.rpp10++
        }
        if(atendimento.usuario.bairro.regiao.id == 11){
          this.rpp11++
        }
        if(atendimento.usuario.bairro.regiao.id == 12){
          this.rpp12++
        }
        if(atendimento.usuario.bairro.regiao.id == 13){
          this.rpp13++
        }
        if(atendimento.usuario.bairro.regiao.id == 14){
          this.rpp14++
        }
        if(atendimento.servicoUm.id == 41){
          this.svv1++
        }
        if(atendimento.servicoDois.id == 41){
          this.svv1++
        }
        if(atendimento.servicoTres.id == 41){
          this.svv1++
        }
        if(atendimento.servicoUm.id == 42){
          this.svv2++
        }
        if(atendimento.servicoDois.id == 42){
          this.svv2++
        }
        if(atendimento.servicoTres.id == 42){
          this.svv2++
        }
        if(atendimento.servicoUm.id == 43){
          this.svv3++
        }
        if(atendimento.servicoDois.id == 43){
          this.svv3++
        }
        if(atendimento.servicoTres.id == 43){
          this.svv3++
        }
        if(atendimento.servicoUm.id == 44){
          this.svv4++
        }
        if(atendimento.servicoDois.id == 44){
          this.svv4++
        }
        if(atendimento.servicoTres.id == 44){
          this.svv4++
        }
        if(atendimento.servicoUm.id == 45){
          this.svv5++
        }
        if(atendimento.servicoDois.id == 45){
          this.svv5++
        }
        if(atendimento.servicoTres.id == 45){
          this.svv5++
        }
        if(atendimento.servicoUm.id == 46){
          this.svv6++
        }
        if(atendimento.servicoDois.id == 46){
          this.svv6++
        }
        if(atendimento.servicoTres.id == 46){
          this.svv6++
        }
        if(atendimento.servicoUm.id == 47){
          this.svv7++
        }
        if(atendimento.servicoDois.id == 47){
          this.svv7++
        }
        if(atendimento.servicoTres.id == 47){
          this.svv7++
        }
        if(atendimento.servicoUm.id == 48){
          this.svv8++
        }
        if(atendimento.servicoDois.id == 48){
          this.svv8++
        }
        if(atendimento.servicoTres.id == 48){
          this.svv8++
        }
        if(atendimento.servicoUm.id == 49){
          this.svv9++
        }
        if(atendimento.servicoDois.id == 49){
          this.svv9++
        }
        if(atendimento.servicoTres.id == 49){
          this.svv9++
        }
        if(atendimento.servicoUm.id == 50){
          this.svv10++
        }
        if(atendimento.servicoDois.id == 50){
          this.svv10++
        }
        if(atendimento.servicoTres.id == 50){
          this.svv10++
        }
        if(atendimento.servicoUm.id == 51){
          this.svv11++
        }
        if(atendimento.servicoDois.id == 51){
          this.svv11++
        }
        if(atendimento.servicoTres.id == 51){
          this.svv11++
        }
        if(atendimento.servicoUm.id == 52){
          this.svv12++
        }
        if(atendimento.servicoDois.id == 52){
          this.svv12++
        }
        if(atendimento.servicoTres.id == 52){
          this.svv12++
        }
        if(atendimento.servicoUm.id == 53){
          this.svv13++
        }
        if(atendimento.servicoDois.id == 53){
          this.svv13++
        }
        if(atendimento.servicoTres.id == 53){
          this.svv13++
        }
        if(atendimento.servicoUm.id == 54){
          this.svv14++
        }
        if(atendimento.servicoDois.id == 54){
          this.svv14++
        }
        if(atendimento.servicoTres.id == 54){
          this.svv14++
        }
        if(atendimento.servicoUm.id == 55){
          this.svv15++
        }
        if(atendimento.servicoDois.id == 55){
          this.svv15++
        }
        if(atendimento.servicoTres.id == 55){
          this.svv15++
        }
        if(atendimento.servicoUm.id == 56){
          this.svv16++
        }
        if(atendimento.servicoDois.id == 56){
          this.svv16++
        }
        if(atendimento.servicoTres.id == 56){
          this.svv16++
        }
        if(atendimento.servicoUm.id == 57){
          this.svv17++
        }
        if(atendimento.servicoDois.id == 57){
          this.svv17++
        }
        if(atendimento.servicoTres.id == 57){
          this.svv17++
        }
        if(atendimento.servicoUm.id == 58){
          this.svv18++
        }
        if(atendimento.servicoDois.id == 58){
          this.svv18++
        }
        if(atendimento.servicoTres.id == 58){
          this.svv18++
        }
        if(atendimento.servicoUm.id == 59){
          this.svv19++
        }
        if(atendimento.servicoDois.id == 59){
          this.svv19++
        }
        if(atendimento.servicoTres.id == 59){
          this.svv19++
        }
        if(atendimento.servicoUm.id == 60){
          this.svv20++
        }
        if(atendimento.servicoDois.id == 60){
          this.svv20++
        }
        if(atendimento.servicoTres.id == 60){
          this.svv20++
        }
        if(atendimento.servicoUm.id == 61){
          this.svv21++
        }
        if(atendimento.servicoDois.id == 61){
          this.svv21++
        }
        if(atendimento.servicoTres.id == 61){
          this.svv21++
        }
        if(atendimento.servicoUm.id == 62){
          this.svv22++
        }
        if(atendimento.servicoDois.id == 62){
          this.svv22++
        }
        if(atendimento.servicoTres.id == 62){
          this.svv22++
        }
        if(atendimento.servicoUm.id == 63){
          this.svv23++
        }
        if(atendimento.servicoDois.id == 63){
          this.svv23++
        }
        if(atendimento.servicoTres.id == 63){
          this.svv23++
        }
        if(atendimento.servicoUm.id == 64){
          this.svv24++
        }
        if(atendimento.servicoDois.id == 64){
          this.svv24++
        }
        if(atendimento.servicoTres.id == 64){
        this.svv24++
        }
        if(atendimento.servicoUm.id == 65){
          this.svv25++
        }
        if(atendimento.servicoDois.id == 65){
          this.svv25++
        }
        if(atendimento.servicoTres.id == 65){
          this.svv25++
        }
        if(atendimento.servicoUm.id == 66){
          this.svv26++
        }
        if(atendimento.servicoDois.id == 66){
          this.svv26++
        }
        if(atendimento.servicoTres.id == 66){
          this.svv26++
        }
        if(atendimento.servicoUm.id == 67){
          this.svv27++
        }
        if(atendimento.servicoDois.id == 67){
          this.svv27++
        }
        if(atendimento.servicoTres.id == 67){
          this.svv27++
        }
        if(atendimento.servicoUm.id == 68){
          this.svv28++
        }
        if(atendimento.servicoDois.id == 68){
          this.svv28++
        }
        if(atendimento.servicoTres.id == 68){
          this.svv28++
        }
        if(atendimento.servicoUm.id == 69){
          this.svv29++
        }
        if(atendimento.servicoDois.id == 69){
          this.svv29++
        }
        if(atendimento.servicoTres.id == 69){
          this.svv29++
        }
        if(atendimento.servicoUm.id == 70){
          this.svv30++
        }
        if(atendimento.servicoDois.id == 70){
          this.svv30++
        }
        if(atendimento.servicoTres.id == 70){
          this.svv30++
        }
        if(atendimento.servicoUm.id == 71){
          this.svv31++
        }
        if(atendimento.servicoDois.id == 71){
          this.svv31++
        }
        if(atendimento.servicoTres.id == 71){
          this.svv31++
        }
        if(atendimento.servicoUm.id == 72){
          this.svv32++
        }
        if(atendimento.servicoDois.id == 72){
          this.svv32++
        }
        if(atendimento.servicoTres.id == 72){
          this.svv32++
        }
        if(atendimento.servicoUm.id == 73){
          this.svv33++
        }
        if(atendimento.servicoDois.id == 73){
          this.svv33++
        }
        if(atendimento.servicoTres.id == 73){
          this.svv33++
        }
        if(atendimento.servicoUm.id == 74){
          this.svv34++
        }
        if(atendimento.servicoDois.id == 74){
          this.svv34++
        }
        if(atendimento.servicoTres.id == 74){
          this.svv34++
        }
        if(atendimento.servicoUm.id == 75){
          this.svv35++
        }
        if(atendimento.servicoDois.id == 75){
          this.svv35++
        }
        if(atendimento.servicoTres.id == 75){
          this.svv35++
        }
        if(atendimento.servicoUm.id == 76){
          this.svv36++
        }
        if(atendimento.servicoDois.id == 76){
          this.svv36++
        }
        if(atendimento.servicoTres.id == 76){
          this.svv36++
        }
        if(atendimento.servicoUm.id == 77){
          this.svv37++
        }
        if(atendimento.servicoDois.id == 77){
          this.svv37++
        }
        if(atendimento.servicoTres.id == 77){
          this.svv37++
        }
        if(atendimento.servicoUm.id == 78){
          this.svv38++
        }
        if(atendimento.servicoDois.id == 78){
          this.svv38++
        }
        if(atendimento.servicoTres.id == 78){
          this.svv38++
        }
        if(atendimento.servicoUm.id == 79){
          this.svv39++
        }
        if(atendimento.servicoDois.id == 79){
          this.svv39++
        }
        if(atendimento.servicoTres.id == 79){
          this.svv39++
        }
        if(atendimento.servicoUm.id == 80){
          this.svv40++
        }
        if(atendimento.servicoDois.id == 80){
          this.svv40++
        }
        if(atendimento.servicoTres.id == 80){
          this.svv40++
        }
        if(atendimento.servicoUm.id == 81){
          this.svv41++
        }
        if(atendimento.servicoDois.id == 81){
          this.svv41++
        }
        if(atendimento.servicoTres.id == 81){
          this.svv41++
        }
        if(atendimento.servicoUm.id == 82){
          this.svv42++
        }
        if(atendimento.servicoDois.id == 82){
          this.svv42++
        }
        if(atendimento.servicoTres.id == 82){
          this.svv42++
        }
        if(atendimento.servicoUm.id == 83){
          this.svv43++
        }
        if(atendimento.servicoDois.id == 83){
          this.svv43++
        }
        if(atendimento.servicoTres.id == 83){
          this.svv43++
        }
        if(atendimento.servicoUm.id == 84){
          this.svv44++
        }
        if(atendimento.servicoDois.id == 84){
          this.svv44++
        }
        if(atendimento.servicoTres.id == 84){
          this.svv44++
        }
        if(atendimento.servicoUm.id == 85){
          this.svv45++
        }
        if(atendimento.servicoDois.id == 85){
          this.svv45++
        }
        if(atendimento.servicoTres.id == 85){
          this.svv45++
        }
        if(atendimento.servicoUm.id == 86){
          this.svv46++
        }
        if(atendimento.servicoDois.id == 86){
          this.svv46++
        }
        if(atendimento.servicoTres.id == 86){
          this.svv46++
        }
        if(atendimento.servicoUm.id == 87){
          this.svv47++
        }
        if(atendimento.servicoDois.id == 87){
          this.svv47++
        }
        if(atendimento.servicoTres.id == 87){
          this.svv47++
        }
        if(atendimento.servicoUm.id == 88){
          this.svv48++
        }
        if(atendimento.servicoDois.id == 88){
          this.svv48++
        }
        if(atendimento.servicoTres.id == 88){
          this.svv48++
        }
        if(atendimento.servicoUm.id == 89){
          this.svv49++
        }
        if(atendimento.servicoDois.id == 89){
          this.svv49++
        }
        if(atendimento.servicoTres.id == 89){
          this.svv49++
        }
        if(atendimento.servicoUm.id == 90){
          this.svv50++
        }
        if(atendimento.servicoDois.id == 90){
          this.svv50++
        }
        if(atendimento.servicoTres.id == 90){
          this.svv50++
        }
        if(atendimento.servicoUm.id == 91){
          this.svv51++
        }
        if(atendimento.servicoDois.id == 91){
          this.svv51++
        }
        if(atendimento.servicoTres.id == 91){
          this.svv51++
        }
        if(atendimento.servicoUm.id == 92){
          this.svv52++
        }
        if(atendimento.servicoDois.id == 92){
          this.svv52++
        }
        if(atendimento.servicoTres.id == 92){
          this.svv52++
        }
        if(atendimento.servicoUm.id == 93){
          this.svv53++
        }
        if(atendimento.servicoDois.id == 93){
          this.svv53++
        }
        if(atendimento.servicoTres.id == 93){
          this.svv53++
        }
        if(atendimento.servicoUm.id == 94){
          this.svv54++
        }
        if(atendimento.servicoDois.id == 94){
          this.svv54++
        }
        if(atendimento.servicoTres.id == 94){
          this.svv54++
        }
        if(atendimento.servicoUm.id == 95){
          this.svv55++
        }
        if(atendimento.servicoDois.id == 95){
          this.svv55++
        }
        if(atendimento.servicoTres.id == 95){
          this.svv55++
        }
        if(atendimento.servicoUm.id == 96){
          this.svv56++
        }
        if(atendimento.servicoDois.id == 96){
          this.svv56++
        }
        if(atendimento.servicoTres.id == 96){
          this.svv56++
        }
        if(atendimento.servicoUm.id == 97){
          this.svv57++
        }
        if(atendimento.servicoDois.id == 97){
          this.svv57++
        }
        if(atendimento.servicoTres.id == 97){
          this.svv57++
        }
        if(atendimento.servicoUm.id == 98){
          this.svv58++
        }
        if(atendimento.servicoDois.id == 98){
          this.svv58++
        }
        if(atendimento.servicoTres.id == 98){
          this.svv58++
        }
        if(atendimento.servicoUm.id == 99){
          this.svv59++
        }
        if(atendimento.servicoDois.id == 99){
          this.svv59++
        }
        if(atendimento.servicoTres.id == 99){
          this.svv59++
        }
        if(atendimento.servicoUm.id == 100){
          this.svv60++
        }
        if(atendimento.servicoDois.id == 100){
          this.svv60++
        }
        if(atendimento.servicoTres.id == 100){
          this.svv60++
        }
        if(atendimento.servicoUm.id == 101){
          this.svv61++
        }
        if(atendimento.servicoDois.id == 101){
          this.svv61++
        }
        if(atendimento.servicoTres.id == 101){
          this.svv61++
        }
        if(atendimento.servicoUm.id == 102){
          this.svv62++
        }
        if(atendimento.servicoDois.id == 102){
          this.svv62++
        }
        if(atendimento.servicoTres.id == 102){
          this.svv62++
        }
        if(atendimento.servicoUm.id == 103){
          this.svv63++
        }
        if(atendimento.servicoDois.id == 103){
          this.svv63++
        }
        if(atendimento.servicoTres.id == 103){
          this.svv63++
        }
        if(atendimento.servicoUm.id == 104){
          this.svv64++
        }
        if(atendimento.servicoDois.id == 104){
          this.svv64++
        }
        if(atendimento.servicoTres.id == 104){
          this.svv64++
        }
        if(atendimento.servicoUm.id == 105){
          this.svv65++
        }
        if(atendimento.servicoDois.id == 105){
          this.svv65++
        }
        if(atendimento.servicoTres.id == 105){
          this.svv65++
        }
        if(atendimento.servicoUm.id == 106){
          this.svv66++
        }
        if(atendimento.servicoDois.id == 106){
          this.svv66++
        }
        if(atendimento.servicoTres.id == 106){
          this.svv66++
        }
        if(atendimento.servicoUm.id == 107){
          this.svv67++
        }
        if(atendimento.servicoDois.id == 107){
          this.svv67++
        }
        if(atendimento.servicoTres.id == 107){
          this.svv67++
        }
        if(atendimento.servicoUm.id == 108){
          this.svv68++
        }
        if(atendimento.servicoDois.id == 108){
          this.svv68++
        }
        if(atendimento.servicoTres.id == 108){
          this.svv68++
        }
        if(atendimento.servicoUm.id == 109){
          this.svv69++
        }
        if(atendimento.servicoDois.id == 109){
          this.svv69++
        }
        if(atendimento.servicoTres.id == 109){
          this.svv69++
        }
        if(atendimento.servicoUm.id == 110){
          this.svv70++
        }
        if(atendimento.servicoDois.id == 110){
          this.svv70++
        }
        if(atendimento.servicoTres.id == 110){
          this.svv70++
        }
        if(atendimento.servicoUm.id == 111){
          this.svv71++
        }
        if(atendimento.servicoDois.id == 111){
          this.svv71++
        }
        if(atendimento.servicoTres.id == 111){
          this.svv71++
        }
        if(atendimento.servicoUm.id == 112){
          this.svv72++
        }
        if(atendimento.servicoDois.id == 112){
          this.svv72++
        }
        if(atendimento.servicoTres.id == 112){
          this.svv72++
        }
        if(atendimento.servicoUm.id == 113){
          this.svv73++
        }
        if(atendimento.servicoDois.id == 113){
          this.svv73++
        }
        if(atendimento.servicoTres.id == 113){
          this.svv73++
        }
        if(atendimento.servicoUm.id == 114){
          this.svv74++
        }
        if(atendimento.servicoDois.id == 114){
          this.svv74++
        }
        if(atendimento.servicoTres.id == 114){
          this.svv74++
        }
        if(atendimento.servicoUm.id == 115){
          this.svv75++
        }
        if(atendimento.servicoDois.id == 115){
          this.svv75++
        }
        if(atendimento.servicoTres.id == 115){
          this.svv75++
        }
        if(atendimento.servicoUm.id == 116){
          this.svv76++
        }
        if(atendimento.servicoDois.id == 116){
          this.svv76++
        }
        if(atendimento.servicoTres.id == 116){
          this.svv76++
        }
        if(atendimento.usuario.avaliacao != undefined){
          this.avaliacao = this.avaliacao + atendimento.usuario.avaliacao
        }
      }
      this.avaliacao = this.avaliacao/this.numAtendimento
      this.showLoading = false
      this.chartPoliticas()
      this.chartRegioes()
      this.regiao =  new Regiao
    })
}

getListNovo() {
  this.clearServicos()
  this.list = []
  this.example.ativo = true
  return this.atendimentoService.getByExample(this.example).then(list => {
    this.list  = list
   
    this.numAtendimento = this.list.length
    for(let atendimento of this.list){
      if(atendimento.politicaUm.id == 1){
        this.atendimentoAss.push(atendimento)
        this.assistencia++
      }
      if(atendimento.politicaUm.id == 2){
        this.atendimentoEdc.push(atendimento)
        this.educacao++
      }
      if(atendimento.politicaUm.id == 3){
        this.atendimentoHab.push(atendimento)
        this.habitacao++
      }
      if(atendimento.politicaUm.id == 4){
        this.atendimentoSaude.push(atendimento)
        this.saude++
      }
      if(atendimento.politicaUm.id == 5){
        this.atendimentoInfra.push(atendimento)
        this.infraestrutura++
      }
      if(atendimento.politicaUm.id == 6){
        this.atendimentoEsp.push(atendimento)
        this.esporte++
      }
      if(atendimento.politicaUm.id == 7){
        this.atendimentoCult.push(atendimento)
        this.cultura++
      }
      if(atendimento.politicaUm.id == 8){
        this.atendimentoMeio.push(atendimento)
        this.meioambiente++
      }
      if(atendimento.politicaUm.id == 9){
        this.atendimentoMob.push(atendimento)
        this.mobilidade++
      }
      if(atendimento.politicaDois.id == 1){
        this.atendimentoAss.push(atendimento)
        this.assistencia++
      }
      if(atendimento.politicaDois.id == 2){
        this.atendimentoEdc.push(atendimento)
        this.cultura++
      }
      if(atendimento.politicaDois.id == 3){
        this.atendimentoHab.push(atendimento)
        this.habitacao++
      }
      if(atendimento.politicaDois.id == 4){
        this.atendimentoSaude.push(atendimento)
        this.saude++
      }
      if(atendimento.politicaDois.id == 5){
        this.atendimentoInfra.push(atendimento)
        this.infraestrutura++
      }
      if(atendimento.politicaDois.id == 6){
        this.atendimentoEsp.push(atendimento)
        this.esporte++
      }
      if(atendimento.politicaDois.id == 7){
        this.atendimentoCult.push(atendimento)
        this.cultura++
      }
      if(atendimento.politicaDois.id == 8){
        this.atendimentoMeio.push(atendimento)
        this.meioambiente++
      }
      if(atendimento.politicaDois.id == 9){
        this.atendimentoMob.push(atendimento)
        this.mobilidade++
      }
      if(atendimento.politicaTres.id == 1){
        this.atendimentoAss.push(atendimento)
        this.assistencia++
      }
      if(atendimento.politicaTres.id == 2){
        this.atendimentoEdc.push(atendimento)
        this.cultura++
      }
      if(atendimento.politicaTres.id == 3){
        this.atendimentoHab.push(atendimento)
        this.habitacao++
      }
      if(atendimento.politicaTres.id == 4){
        this.atendimentoSaude.push(atendimento)
        this.saude++
      }
      if(atendimento.politicaTres.id == 5){
        this.atendimentoInfra.push(atendimento)
        this.infraestrutura++
      }
      if(atendimento.politicaTres.id == 6){
        this.atendimentoEsp.push(atendimento)
        this.esporte++
      }
      if(atendimento.politicaTres.id == 7){
        this.atendimentoCult.push(atendimento)
        this.cultura++
      }
      if(atendimento.politicaTres.id == 8){
        this.atendimentoMeio.push(atendimento)
        this.meioambiente++
      }
      if(atendimento.politicaTres.id == 9){
        this.atendimentoMob.push(atendimento)
        this.mobilidade++
     }
      if(atendimento.usuario.bairro.regiao.id == 1){
        this.rpp1++
      }
      if(atendimento.usuario.bairro.regiao.id == 2){
        this.rpp2++
      }
      if(atendimento.usuario.bairro.regiao.id == 3){
        this.rpp3++
      }
      if(atendimento.usuario.bairro.regiao.id == 4){
        this.rpp4++
      }
      if(atendimento.usuario.bairro.regiao.id == 5){
        this.rpp5++
      }
      if(atendimento.usuario.bairro.regiao.id == 6){
        this.rpp6++
      }
      if(atendimento.usuario.bairro.regiao.id == 7){
        this.rpp7++
      }
      if(atendimento.usuario.bairro.regiao.id == 8){
        this.rpp8++
      }
      if(atendimento.usuario.bairro.regiao.id == 9){
        this.rpp9++
      }
      if(atendimento.usuario.bairro.regiao.id == 10){
        this.rpp10++
      }
      if(atendimento.usuario.bairro.regiao.id == 11){
        this.rpp11++
      }
      if(atendimento.usuario.bairro.regiao.id == 12){
        this.rpp12++
      }
      if(atendimento.usuario.bairro.regiao.id == 13){
        this.rpp13++
      }
      if(atendimento.usuario.bairro.regiao.id == 14){
        this.rpp14++
      }
      if(atendimento.servicoUm.id == 41){
        this.svv1++
      }
      if(atendimento.servicoDois.id == 41){
        this.svv1++
      }
      if(atendimento.servicoTres.id == 41){
        this.svv1++
      }
      if(atendimento.servicoUm.id == 42){
        this.svv2++
      }
      if(atendimento.servicoDois.id == 42){
        this.svv2++
      }
      if(atendimento.servicoTres.id == 42){
        this.svv2++
      }
      if(atendimento.servicoUm.id == 43){
        this.svv3++
      }
      if(atendimento.servicoDois.id == 43){
        this.svv3++
      }
      if(atendimento.servicoTres.id == 43){
        this.svv3++
      }
      if(atendimento.servicoUm.id == 44){
        this.svv4++
      }
      if(atendimento.servicoDois.id == 44){
        this.svv4++
      }
      if(atendimento.servicoTres.id == 44){
        this.svv4++
      }
      if(atendimento.servicoUm.id == 45){
        this.svv5++
      }
      if(atendimento.servicoDois.id == 45){
        this.svv5++
      }
      if(atendimento.servicoTres.id == 45){
        this.svv5++
      }
      if(atendimento.servicoUm.id == 46){
        this.svv6++
      }
      if(atendimento.servicoDois.id == 46){
        this.svv6++
      }
      if(atendimento.servicoTres.id == 46){
        this.svv6++
      }
      if(atendimento.servicoUm.id == 47){
        this.svv7++
      }
      if(atendimento.servicoDois.id == 47){
        this.svv7++
      }
      if(atendimento.servicoTres.id == 47){
        this.svv7++
      }
      if(atendimento.servicoUm.id == 48){
        this.svv8++
      }
      if(atendimento.servicoDois.id == 48){
        this.svv8++
      }
      if(atendimento.servicoTres.id == 48){
        this.svv8++
      }
      if(atendimento.servicoUm.id == 49){
        this.svv9++
      }
      if(atendimento.servicoDois.id == 49){
        this.svv9++
      }
      if(atendimento.servicoTres.id == 49){
        this.svv9++
      }
      if(atendimento.servicoUm.id == 50){
        this.svv10++
      }
      if(atendimento.servicoDois.id == 50){
        this.svv10++
      }
      if(atendimento.servicoTres.id == 50){
        this.svv10++
      }
      if(atendimento.servicoUm.id == 51){
        this.svv11++
      }
      if(atendimento.servicoDois.id == 51){
        this.svv11++
      }
      if(atendimento.servicoTres.id == 51){
        this.svv11++
      }
      if(atendimento.servicoUm.id == 52){
        this.svv12++
      }
      if(atendimento.servicoDois.id == 52){
        this.svv12++
      }
      if(atendimento.servicoTres.id == 52){
        this.svv12++
      }
      if(atendimento.servicoUm.id == 53){
        this.svv13++
      }
      if(atendimento.servicoDois.id == 53){
        this.svv13++
      }
      if(atendimento.servicoTres.id == 53){
        this.svv13++
      }
      if(atendimento.servicoUm.id == 54){
        this.svv14++
      }
      if(atendimento.servicoDois.id == 54){
        this.svv14++
      }
      if(atendimento.servicoTres.id == 54){
        this.svv14++
      }
      if(atendimento.servicoUm.id == 55){
        this.svv15++
      }
      if(atendimento.servicoDois.id == 55){
        this.svv15++
      }
      if(atendimento.servicoTres.id == 55){
        this.svv15++
      }
      if(atendimento.servicoUm.id == 56){
        this.svv16++
      }
      if(atendimento.servicoDois.id == 56){
        this.svv16++
      }
      if(atendimento.servicoTres.id == 56){
        this.svv16++
      }
      if(atendimento.servicoUm.id == 57){
        this.svv17++
      }
      if(atendimento.servicoDois.id == 57){
        this.svv17++
      }
      if(atendimento.servicoTres.id == 57){
        this.svv17++
      }
      if(atendimento.servicoUm.id == 58){
        this.svv18++
      }
      if(atendimento.servicoDois.id == 58){
        this.svv18++
      }
      if(atendimento.servicoTres.id == 58){
        this.svv18++
      }
      if(atendimento.servicoUm.id == 59){
        this.svv19++
      }
      if(atendimento.servicoDois.id == 59){
        this.svv19++
      }
      if(atendimento.servicoTres.id == 59){
        this.svv19++
      }
      if(atendimento.servicoUm.id == 60){
        this.svv20++
      }
      if(atendimento.servicoDois.id == 60){
        this.svv20++
      }
      if(atendimento.servicoTres.id == 60){
        this.svv20++
      }
      if(atendimento.servicoUm.id == 61){
        this.svv21++
      }
      if(atendimento.servicoDois.id == 61){
        this.svv21++
      }
      if(atendimento.servicoTres.id == 61){
        this.svv21++
      }
      if(atendimento.servicoUm.id == 62){
        this.svv22++
      }
      if(atendimento.servicoDois.id == 62){
        this.svv22++
      }
      if(atendimento.servicoTres.id == 62){
        this.svv22++
      }
      if(atendimento.servicoUm.id == 63){
        this.svv23++
      }
      if(atendimento.servicoDois.id == 63){
        this.svv23++
      }
      if(atendimento.servicoTres.id == 63){
        this.svv23++
      }
      if(atendimento.servicoUm.id == 64){
        this.svv24++
      }
      if(atendimento.servicoDois.id == 64){
        this.svv24++
      }
      if(atendimento.servicoTres.id == 64){
      this.svv24++
      }
      if(atendimento.servicoUm.id == 65){
        this.svv25++
      }
      if(atendimento.servicoDois.id == 65){
        this.svv25++
      }
      if(atendimento.servicoTres.id == 65){
        this.svv25++
      }
      if(atendimento.servicoUm.id == 66){
        this.svv26++
      }
      if(atendimento.servicoDois.id == 66){
        this.svv26++
      }
      if(atendimento.servicoTres.id == 66){
        this.svv26++
      }
      if(atendimento.servicoUm.id == 67){
        this.svv27++
      }
      if(atendimento.servicoDois.id == 67){
        this.svv27++
      }
      if(atendimento.servicoTres.id == 67){
        this.svv27++
      }
      if(atendimento.servicoUm.id == 68){
        this.svv28++
      }
      if(atendimento.servicoDois.id == 68){
        this.svv28++
      }
      if(atendimento.servicoTres.id == 68){
        this.svv28++
      }
      if(atendimento.servicoUm.id == 69){
        this.svv29++
      }
      if(atendimento.servicoDois.id == 69){
        this.svv29++
      }
      if(atendimento.servicoTres.id == 69){
        this.svv29++
      }
      if(atendimento.servicoUm.id == 70){
        this.svv30++
      }
      if(atendimento.servicoDois.id == 70){
        this.svv30++
      }
      if(atendimento.servicoTres.id == 70){
        this.svv30++
      }
      if(atendimento.servicoUm.id == 71){
        this.svv31++
      }
      if(atendimento.servicoDois.id == 71){
        this.svv31++
      }
      if(atendimento.servicoTres.id == 71){
        this.svv31++
      }
      if(atendimento.servicoUm.id == 72){
        this.svv32++
      }
      if(atendimento.servicoDois.id == 72){
        this.svv32++
      }
      if(atendimento.servicoTres.id == 72){
        this.svv32++
      }
      if(atendimento.servicoUm.id == 73){
        this.svv33++
      }
      if(atendimento.servicoDois.id == 73){
        this.svv33++
      }
      if(atendimento.servicoTres.id == 73){
        this.svv33++
      }
      if(atendimento.servicoUm.id == 74){
        this.svv34++
      }
      if(atendimento.servicoDois.id == 74){
        this.svv34++
      }
      if(atendimento.servicoTres.id == 74){
        this.svv34++
      }
      if(atendimento.servicoUm.id == 75){
        this.svv35++
      }
      if(atendimento.servicoDois.id == 75){
        this.svv35++
      }
      if(atendimento.servicoTres.id == 75){
        this.svv35++
      }
      if(atendimento.servicoUm.id == 76){
        this.svv36++
      }
      if(atendimento.servicoDois.id == 76){
        this.svv36++
      }
      if(atendimento.servicoTres.id == 76){
        this.svv36++
      }
      if(atendimento.servicoUm.id == 77){
        this.svv37++
      }
      if(atendimento.servicoDois.id == 77){
        this.svv37++
      }
      if(atendimento.servicoTres.id == 77){
        this.svv37++
      }
      if(atendimento.servicoUm.id == 78){
        this.svv38++
      }
      if(atendimento.servicoDois.id == 78){
        this.svv38++
      }
      if(atendimento.servicoTres.id == 78){
        this.svv38++
      }
      if(atendimento.servicoUm.id == 79){
        this.svv39++
      }
      if(atendimento.servicoDois.id == 79){
        this.svv39++
      }
      if(atendimento.servicoTres.id == 79){
        this.svv39++
      }
      if(atendimento.servicoUm.id == 80){
        this.svv40++
      }
      if(atendimento.servicoDois.id == 80){
        this.svv40++
      }
      if(atendimento.servicoTres.id == 80){
        this.svv40++
      }
      if(atendimento.servicoUm.id == 81){
        this.svv41++
      }
      if(atendimento.servicoDois.id == 81){
        this.svv41++
      }
      if(atendimento.servicoTres.id == 81){
        this.svv41++
      }
      if(atendimento.servicoUm.id == 82){
        this.svv42++
      }
      if(atendimento.servicoDois.id == 82){
        this.svv42++
      }
      if(atendimento.servicoTres.id == 82){
        this.svv42++
      }
      if(atendimento.servicoUm.id == 83){
        this.svv43++
      }
      if(atendimento.servicoDois.id == 83){
        this.svv43++
      }
      if(atendimento.servicoTres.id == 83){
        this.svv43++
      }
      if(atendimento.servicoUm.id == 84){
        this.svv44++
      }
      if(atendimento.servicoDois.id == 84){
        this.svv44++
      }
      if(atendimento.servicoTres.id == 84){
        this.svv44++
      }
      if(atendimento.servicoUm.id == 85){
        this.svv45++
      }
      if(atendimento.servicoDois.id == 85){
        this.svv45++
      }
      if(atendimento.servicoTres.id == 85){
        this.svv45++
      }
      if(atendimento.servicoUm.id == 86){
        this.svv46++
      }
      if(atendimento.servicoDois.id == 86){
        this.svv46++
      }
      if(atendimento.servicoTres.id == 86){
        this.svv46++
      }
      if(atendimento.servicoUm.id == 87){
        this.svv47++
      }
      if(atendimento.servicoDois.id == 87){
        this.svv47++
      }
      if(atendimento.servicoTres.id == 87){
        this.svv47++
      }
      if(atendimento.servicoUm.id == 88){
        this.svv48++
      }
      if(atendimento.servicoDois.id == 88){
        this.svv48++
      }
      if(atendimento.servicoTres.id == 88){
        this.svv48++
      }
      if(atendimento.servicoUm.id == 89){
        this.svv49++
      }
      if(atendimento.servicoDois.id == 89){
        this.svv49++
      }
      if(atendimento.servicoTres.id == 89){
        this.svv49++
      }
      if(atendimento.servicoUm.id == 90){
        this.svv50++
      }
      if(atendimento.servicoDois.id == 90){
        this.svv50++
      }
      if(atendimento.servicoTres.id == 90){
        this.svv50++
      }
      if(atendimento.servicoUm.id == 91){
        this.svv51++
      }
      if(atendimento.servicoDois.id == 91){
        this.svv51++
      }
      if(atendimento.servicoTres.id == 91){
        this.svv51++
      }
      if(atendimento.servicoUm.id == 92){
        this.svv52++
      }
      if(atendimento.servicoDois.id == 92){
        this.svv52++
      }
      if(atendimento.servicoTres.id == 92){
        this.svv52++
      }
      if(atendimento.servicoUm.id == 93){
        this.svv53++
      }
      if(atendimento.servicoDois.id == 93){
        this.svv53++
      }
      if(atendimento.servicoTres.id == 93){
        this.svv53++
      }
      if(atendimento.servicoUm.id == 94){
        this.svv54++
      }
      if(atendimento.servicoDois.id == 94){
        this.svv54++
      }
      if(atendimento.servicoTres.id == 94){
        this.svv54++
      }
      if(atendimento.servicoUm.id == 95){
        this.svv55++
      }
      if(atendimento.servicoDois.id == 95){
        this.svv55++
      }
      if(atendimento.servicoTres.id == 95){
        this.svv55++
      }
      if(atendimento.servicoUm.id == 96){
        this.svv56++
      }
      if(atendimento.servicoDois.id == 96){
        this.svv56++
      }
      if(atendimento.servicoTres.id == 96){
        this.svv56++
      }
      if(atendimento.servicoUm.id == 97){
        this.svv57++
      }
      if(atendimento.servicoDois.id == 97){
        this.svv57++
      }
      if(atendimento.servicoTres.id == 97){
        this.svv57++
      }
      if(atendimento.servicoUm.id == 98){
        this.svv58++
      }
      if(atendimento.servicoDois.id == 98){
        this.svv58++
      }
      if(atendimento.servicoTres.id == 98){
        this.svv58++
      }
      if(atendimento.servicoUm.id == 99){
        this.svv59++
      }
      if(atendimento.servicoDois.id == 99){
        this.svv59++
      }
      if(atendimento.servicoTres.id == 99){
        this.svv59++
      }
      if(atendimento.servicoUm.id == 100){
        this.svv60++
      }
      if(atendimento.servicoDois.id == 100){
        this.svv60++
      }
      if(atendimento.servicoTres.id == 100){
        this.svv60++
      }
      if(atendimento.servicoUm.id == 101){
        this.svv61++
      }
      if(atendimento.servicoDois.id == 101){
        this.svv61++
      }
      if(atendimento.servicoTres.id == 101){
        this.svv61++
      }
      if(atendimento.servicoUm.id == 102){
        this.svv62++
      }
      if(atendimento.servicoDois.id == 102){
        this.svv62++
      }
      if(atendimento.servicoTres.id == 102){
        this.svv62++
      }
      if(atendimento.servicoUm.id == 103){
        this.svv63++
      }
      if(atendimento.servicoDois.id == 103){
        this.svv63++
      }
      if(atendimento.servicoTres.id == 103){
        this.svv63++
      }
      if(atendimento.servicoUm.id == 104){
        this.svv64++
      }
      if(atendimento.servicoDois.id == 104){
        this.svv64++
      }
      if(atendimento.servicoTres.id == 104){
        this.svv64++
      }
      if(atendimento.servicoUm.id == 105){
        this.svv65++
      }
      if(atendimento.servicoDois.id == 105){
        this.svv65++
      }
      if(atendimento.servicoTres.id == 105){
        this.svv65++
      }
      if(atendimento.servicoUm.id == 106){
        this.svv66++
      }
      if(atendimento.servicoDois.id == 106){
        this.svv66++
      }
      if(atendimento.servicoTres.id == 106){
        this.svv66++
      }
      if(atendimento.servicoUm.id == 107){
        this.svv67++
      }
      if(atendimento.servicoDois.id == 107){
        this.svv67++
      }
      if(atendimento.servicoTres.id == 107){
        this.svv67++
      }
      if(atendimento.servicoUm.id == 108){
        this.svv68++
      }
      if(atendimento.servicoDois.id == 108){
        this.svv68++
      }
      if(atendimento.servicoTres.id == 108){
        this.svv68++
      }
      if(atendimento.servicoUm.id == 109){
        this.svv69++
      }
      if(atendimento.servicoDois.id == 109){
        this.svv69++
      }
      if(atendimento.servicoTres.id == 109){
        this.svv69++
      }
      if(atendimento.servicoUm.id == 110){
        this.svv70++
      }
      if(atendimento.servicoDois.id == 110){
        this.svv70++
      }
      if(atendimento.servicoTres.id == 110){
        this.svv70++
      }
      if(atendimento.servicoUm.id == 111){
        this.svv71++
      }
      if(atendimento.servicoDois.id == 111){
        this.svv71++
      }
      if(atendimento.servicoTres.id == 111){
        this.svv71++
      }
      if(atendimento.servicoUm.id == 112){
        this.svv72++
      }
      if(atendimento.servicoDois.id == 112){
        this.svv72++
      }
      if(atendimento.servicoTres.id == 112){
        this.svv72++
      }
      if(atendimento.servicoUm.id == 113){
        this.svv73++
      }
      if(atendimento.servicoDois.id == 113){
        this.svv73++
      }
      if(atendimento.servicoTres.id == 113){
        this.svv73++
      }
      if(atendimento.servicoUm.id == 114){
        this.svv74++
      }
      if(atendimento.servicoDois.id == 114){
        this.svv74++
      }
      if(atendimento.servicoTres.id == 114){
        this.svv74++
      }
      if(atendimento.servicoUm.id == 115){
        this.svv75++
      }
      if(atendimento.servicoDois.id == 115){
        this.svv75++
      }
      if(atendimento.servicoTres.id == 115){
        this.svv75++
      }
      if(atendimento.servicoUm.id == 116){
        this.svv76++
      }
      if(atendimento.servicoDois.id == 116){
        this.svv76++
      }
      if(atendimento.servicoTres.id == 116){
        this.svv76++
      }
      if(atendimento.usuario.avaliacao != undefined){
        this.avaliacao = this.avaliacao + atendimento.usuario.avaliacao
      }
    }
    debugger
    this.avaliacao = this.avaliacao/this.numAtendimento
    this.showLoading = false
    this.chartPoliticasNovo()
    this.chartRegioesNovo()
  })
}


  // teste(){

  //   this.atendimentoService.getByExample(this.example).then(list =>{
  //     this.numAtendimento = list.length
  //     for(let atendimento of list){
  //       if(atendimento.politicaUm.id == 1){
  //         this.assistencia++
  //       }
  //       if(atendimento.politicaUm.id == 2){
  //         this.educacao++
  //       }
  //       if(atendimento.politicaUm.id == 3){
  //         this.habitacao++
  //       }
  //       if(atendimento.politicaUm.id == 4){
  //         this.saude++
  //       }
  //       if(atendimento.politicaUm.id == 5){
  //         this.infraestrutura++
  //       }
  //       if(atendimento.politicaUm.id == 6){
  //         this.esporte++
  //       }
  //       if(atendimento.politicaUm.id == 7){
  //         this.cultura++
  //       }
  //       if(atendimento.politicaUm.id == 8){
  //         this.meioambiente++
  //       }
  //       if(atendimento.politicaUm.id == 9){
  //         this.mobilidade++
  //       }
  //       if(atendimento.politicaDois.id == 1){
  //         this.assistencia++
  //       }
  //       if(atendimento.politicaDois.id == 2){
  //         this.cultura++
  //       }
  //       if(atendimento.politicaDois.id == 3){
  //         this.habitacao++
  //       }
  //       if(atendimento.politicaDois.id == 4){
  //         this.saude++
  //       }
  //       if(atendimento.politicaDois.id == 5){
  //         this.infraestrutura++
  //       }
  //       if(atendimento.politicaDois.id == 6){
  //         this.esporte++
  //       }
  //       if(atendimento.politicaDois.id == 7){
  //         this.cultura++
  //       }
  //       if(atendimento.politicaDois.id == 8){
  //         this.meioambiente++
  //       }
  //       if(atendimento.politicaDois.id == 9){
  //         this.mobilidade++
  //       }
  //       if(atendimento.politicaTres.id == 1){
  //         this.assistencia++
  //       }
  //       if(atendimento.politicaTres.id == 2){
  //         this.cultura++
  //       }
  //       if(atendimento.politicaTres.id == 3){
  //         this.habitacao++
  //       }
  //       if(atendimento.politicaTres.id == 4){
  //         this.saude++
  //       }
  //       if(atendimento.politicaTres.id == 5){
  //         this.infraestrutura++
  //       }
  //       if(atendimento.politicaTres.id == 6){
  //         this.esporte++
  //       }
  //       if(atendimento.politicaTres.id == 7){
  //         this.cultura++
  //       }
  //       if(atendimento.politicaTres.id == 8){
  //         this.meioambiente++
  //       }
  //       if(atendimento.politicaTres.id == 9){
  //         this.mobilidade++
  //       }
  //       if(atendimento.usuario.bairro.regiao.id == 1){
  //         this.rpp1++
  //       }
  //       if(atendimento.usuario.bairro.regiao.id == 2){
  //         this.rpp2++
  //       }
  //       if(atendimento.usuario.bairro.regiao.id == 3){
  //         this.rpp3++
  //       }
  //       if(atendimento.usuario.bairro.regiao.id == 4){
  //         this.rpp4++
  //       }
  //       if(atendimento.usuario.bairro.regiao.id == 5){
  //         this.rpp5++
  //       }
  //       if(atendimento.usuario.bairro.regiao.id == 6){
  //         this.rpp6++
  //       }
  //       if(atendimento.usuario.bairro.regiao.id == 7){
  //         this.rpp7++
  //       }
  //       if(atendimento.usuario.bairro.regiao.id == 8){
  //         this.rpp8++
  //       }
  //       if(atendimento.usuario.bairro.regiao.id == 9){
  //         this.rpp9++
  //       }
  //       if(atendimento.usuario.bairro.regiao.id == 10){
  //         this.rpp10++
  //       }
  //       if(atendimento.usuario.bairro.regiao.id == 11){
  //         this.rpp11++
  //       }
  //       if(atendimento.usuario.bairro.regiao.id == 12){
  //         this.rpp12++
  //       }
  //       if(atendimento.usuario.bairro.regiao.id == 13){
  //         this.rpp13++
  //       }
  //       if(atendimento.usuario.bairro.regiao.id == 14){
  //         this.rpp14++
  //       }
  //     }
  //       this.chartPoliticas()
  //       this.chartRegioes()
  //   })
  // }

  selectWo(object){
    this.selected = JSON.parse(JSON.stringify(object))
  }

  selectWoView(object){
    this.router.navigate(['denuncia',object.id])
    }

  closeModal(modal){
    // @ts-ignore
    $(modal).modal('hide')
  }

  clearFilter(){
    this.example = new Atendimento()
    this.getList()
  }

  compareFn(o1: any, o2:any): boolean {
    return o1 && o2 ? o1.id === o2.id : o1 === o2;
  }
}
