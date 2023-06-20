import { Component, OnInit } from '@angular/core';
import { AtendimentoService } from '../service/atendimento.service';
import {Chart} from '../../../node_modules/chart.js'
import { Atendimento } from '../model/Atendimento.model';

@Component({
  selector: 'di-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {

  assistencia : number = 0
  educacao : number = 0
  habitacao : number = 0 
  saude : number = 0
  infraestrutura : number = 0
  esporte : number = 0 
  cultura : number = 0
  meioambiente : number = 0
  mobilidade : number = 0

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

  atendimentos :  Atendimento[]=[]

  numAtendimento : number = 0

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
  ) { }

  ngOnInit() {
      this.getList()
   
    }

    chartPoliticas(){
        var myChart = new Chart("politicas", {
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
           }
          }
      });
    }

    chartRegioes(){
      var myChart = new Chart("regioes", {
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


    getList(){
      this.atendimentoService.getAll().then(list => {
        this.atendimentos = list
          for(let atendimento of this.atendimentos){
            this.numAtendimento++
            if(atendimento.politicaUm.id == 1){
              this.assistencia++
            }
            if(atendimento.politicaUm.id == 2){
              this.educacao++
            }
            if(atendimento.politicaUm.id == 3){
              this.habitacao++
            }
            if(atendimento.politicaUm.id == 4){
              this.saude++
            }
            if(atendimento.politicaUm.id == 5){
              this.infraestrutura++
            }
            if(atendimento.politicaUm.id == 6){
              this.esporte++
            }
            if(atendimento.politicaUm.id == 7){
              this.cultura++
            }
            if(atendimento.politicaUm.id == 8){
              this.meioambiente++
            }
            if(atendimento.politicaUm.id == 9){
              this.mobilidade++
            }
            if(atendimento.politicaDois.id == 1){
              this.assistencia++
            }
            if(atendimento.politicaDois.id == 2){
              this.cultura++
            }
            if(atendimento.politicaDois.id == 3){
              this.habitacao++
            }
            if(atendimento.politicaDois.id == 4){
              this.saude++
            }
            if(atendimento.politicaDois.id == 5){
              this.infraestrutura++
            }
            if(atendimento.politicaDois.id == 6){
              this.esporte++
            }
            if(atendimento.politicaDois.id == 7){
              this.cultura++
            }
            if(atendimento.politicaDois.id == 8){
              this.meioambiente++
            }
            if(atendimento.politicaDois.id == 9){
              this.mobilidade++
            }
            if(atendimento.politicaTres.id == 1){
              this.assistencia++
            }
            if(atendimento.politicaTres.id == 2){
              this.cultura++
            }
            if(atendimento.politicaTres.id == 3){
              this.habitacao++
            }
            if(atendimento.politicaTres.id == 4){
              this.saude++
            }
            if(atendimento.politicaTres.id == 5){
              this.infraestrutura++
            }
            if(atendimento.politicaTres.id == 6){
              this.esporte++
            }
            if(atendimento.politicaTres.id == 7){
              this.cultura++
            }
            if(atendimento.politicaTres.id == 8){
              this.meioambiente++
            }
            if(atendimento.politicaTres.id == 9){
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
        }
        debugger
        this.chartPoliticas()
        this.chartRegioes()
      })
    }





}
