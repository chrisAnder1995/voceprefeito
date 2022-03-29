import { MembroFamilia } from './../../model/MembroFamilia.model';
import { Droga } from './../../model/Droga.model';
import { MORADIA, PARENTESCO, SEXO, BUSCA_SERVICO, ESTADO, TURNO, TEMPO_GESTACAO, ESTADO_CIVIL, ETNIA, TURNO_PUBLICO, TRABALHO, ESCOLARIDADE } from './../../model/enum/Enumeration.api.enum';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '../../../../node_modules/@angular/router';
import { AppService } from '../../app.service';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';
import { removeModal } from '../../modal.config'
import { Atendimento } from '../../model/Atendimento.model';
import { AtendimentoService } from '../../service/atendimento.service';
import { Usuario } from '../../model/Usuario.model';
import { Regiao } from '../../model/Regiao.model';
import { RegiaoService } from '../../service/Regiao.service';
import { PoliticaService } from '../../service/politica.service';
import { Politica } from '../../model/Politica.model';
import { Servico } from '../../model/Servico.model';
import { ServicoService } from '../../service/servico.service';
import { BairroService } from '../../service/bairro.service';
import { SERVICE } from '../../app.api';
import { Bairro } from '../../model/Bairro.model';
import { Cidade } from '../../model/Cidade.model';
import { DialogService, DynamicDialogConfig, DynamicDialogRef, MenuItem } from 'primeng-lts/api';


@Component({
  selector: 'di-atendimento-form',
  templateUrl: './atendimento-publi.component.html',
  styleUrls: ['./atendimento-publi.component.css']
})
export class AtendimentoPubliFormComponent implements OnInit {


  object = new Atendimento()
  showLoading: boolean = true
  message: string = ""
  successMessage: boolean = false
  errorMessage: boolean = false
  editandoMembro : boolean = false
  urlService: string

  preenchido : number

  mulherDrogas: Droga[] = []
  bairros: Bairro[] = []
  politicas : Politica[]=[]
  politicasDois : Politica[]=[]
  politicasTres : Politica[]=[]

  servicos : Servico[]=[]
  servicosDois : Servico[]=[]
  servicosTres : Servico[]=[]

  abaPolitica : boolean = false

  allTurnosHorario: string[]
  ativaBairro : boolean = false

  regioes : Regiao[]
  regiaoNome : String

  textoAvaliacao = ''

  pt:any
  cpf : string

  nota : number

  proximo : boolean = false
  avaliar : boolean = false

  saveDisabled = false
  hideHeader : boolean 
  ratingClicked: number;

  dropdownList = [];
  selectedItems = [];
  dropdownSettings = {};

  constructor(private router: Router, 
              private app: AppService,
              private route: ActivatedRoute,
              private location: Location,
              private regiaoService: RegiaoService,
              private politicaService: PoliticaService, 
              private servicoService: ServicoService, 
              public config: DynamicDialogConfig,
              public dialogService: DialogService,
              private atendimentoService : AtendimentoService,
              private bairroService : BairroService){}

  ngOnInit() {

    if(this.route.snapshot.params.id){
      this.object.usuario = new Usuario()
      this.object.usuario.cpf = this.route.snapshot.params.id
    }

    this.urlService = `${SERVICE}`
    this.carregaListas()
    this.carregaBairro()

    this.pt = {
      firstDayOfWeek: 0,
      dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
      dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
      dayNamesMin: ['Do', 'Se', 'Te', 'Qu', 'Qu', 'Se', 'Sa'],
      monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho',
        'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
      monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
      today: 'Hoje',
      clear: 'Limpar'
    };
    console.log(this.object)
  }


  async carregaListas(){
    this.politicaService.getAllFast().then(politicas =>{
      this.politicas = politicas
    })

    this.showLoading = false
  }

  carregaPoliticaDois(){
    this.politicasDois = []
    this.politicaService.getAllFast().then(politicas =>{
     for(let politica of politicas){
       if(politica.id != this.object.politicaUm.id){
         this.politicasDois.push(politica)
       }
     }
    })
  }

  carregaPoliticaTres(){
    this.politicasTres = []
    this.politicaService.getAllFast().then(politicas =>{
     for(let politica of politicas){
       if(politica.id != this.object.politicaUm.id && politica.id  != this.object.politicaDois.id  ){
         this.politicasTres.push(politica)
       }
     }
    })
  }

  atendimentoPDF(){
    this.atendimentoService.atendimentoPDF(this.object.id,false)
  }

  teste(){
    this.avaliar = true
  }

  closeForm() {
      this.location.back();
  }

  click1(ngForm: NgForm){
    this.nota = 5
    this.textoAvaliacao = 'Excelente!'
    this.save()
  }

  click2(ngForm: NgForm){
    this.nota = 4
    this.textoAvaliacao = 'Ótimo!'
    this.save()
  }

  click3(ngForm: NgForm){
    this.nota = 3
    this.textoAvaliacao = 'Bom!'
    this.save()
  }

  click4(ngForm: NgForm){
    this.nota = 2
    this.textoAvaliacao = 'Ruim!'
    this.save()
  }

  click5(ngForm: NgForm){
    this.nota = 1
    this.textoAvaliacao = 'Péssimo!'
    this.save()
  }


    delete(){
    if(this.object.id != undefined) {
      this.object.ativo = false
      this.atendimentoService.update(this.object).then(atendimento => {
        if (atendimento !== undefined){
          this.router.onSameUrlNavigation = 'reload'
          this.message = "Registro excluído com sucesso"
          this.successMessage = true
          this.avaliar = true
          this.clean()
          this.closeForm()
        } else{
          this.errorMessage = true
          this.message = "Ocorreu um erro, favor tentar novamente"
        }
      })
    }
  }

  compareFn(o1: any, o2:any): boolean {
    return o1 && o2 ? o1.id === o2.id : o1 === o2;
  }

  
  saveTeste(ngForm: NgForm){
    if(ngForm.valid){
      this.avaliar = true
    }
    else {
      this.errorMessage = true
      this.message = "Existem campos obrigatórios não preenchidos"
    }
  }

  save(){
      this.saveDisabled = true
      this.object.ativo = true
      this.object.usuario.ativo = true
      this.object.usuario.avaliacao = this.nota
      this.preenchido = 1

      if(this.object.sugestao != undefined){
          this.object.usuario.opcional = true
      }else{
          this.object.usuario.opcional = false
      }

      if(this.object.id === undefined) {
        this.atendimentoService.create(this.object).then(atendimento => {
            this.avaliar = false
            this.proximo = true
            this.saveDisabled = false
            this.message = "Solicitação registrada com sucesso, obrigado!"
            this.successMessage = true
            this.router.onSameUrlNavigation = 'reload'
            this.clean()
            this.router.navigate(['inicio',this.preenchido])
        })
      }
  }
  
  finalizar(){
    this.object.ativo = false
    this.atendimentoService.update(this.object).then(atendimento => {
      this.saveDisabled = false
      if (atendimento !== undefined){
        this.router.onSameUrlNavigation = 'reload'
        this.message = "Os dados foram gravados"
        this.clean()
        this.closeForm()
      } else{
        this.message = "Ocorreu um erro, favor tentar novamente"
      }
    })
  }

  clean() {
    this.object = new Atendimento()
    this.object.bairro = new Bairro()
    this.object.cidade = new Cidade()
    this.object.usuario = new Usuario()
    this.object.servicoUm = new Servico()
    this.object.servicoDois = new Servico()
    this.object.politicaUm = new Politica()
    this.object.politicaDois = new Politica()
    this.object.politicaDois = new Politica()
    this.object.locaisOcorrencia = []
  }

  boolToStr(bool){
    if (bool == undefined){
      return ""
    } else{
      if (bool){
        return "Sim"
      } else{
        return "Não"
      }
    }
  }

  listToStr(list){
    let result = ""
    for (let item of list){
      if (item.selecionado){
        if (result != ""){
          result += ", "
        }
        result += item.nome
      }
    }
    return result
  }

  carregaServicoUm(){
    let servico = new Servico()
    servico.ativo = true
    servico.politica = new Politica
    servico.politica = this.object.politicaUm

    this.servicoService.getByExampleSimples(servico).then(servicos=>{
      this.servicos = servicos
    })
  }

  carregaServicoDois(){
    let servico = new Servico()
    servico.ativo = true
    servico.politica = new Politica
    servico.politica = this.object.politicaDois

    
    this.servicoService.getByExampleSimples(servico).then(servicos=>{
      this.servicosDois = servicos
    })
  }

  carregaServicoTres(){
    let servico = new Servico()
    servico.ativo = true
    servico.politica = new Politica
    servico.politica = this.object.politicaTres

    
    this.servicoService.getByExampleSimples(servico).then(servicos=>{
      this.servicosTres = servicos
    })
  }

  carregaBairro(){
    this.bairros = []
    this.regiaoService.getAllFast().then(regioes =>{
      this.bairroService.getAllFast().then(bairros => {
        for(let rg of regioes){
          for(let br of bairros){
            if(br.regiao.id == rg.id){
              this.bairros.push(br)
            }
          }
        }
      })
    })
  }  


  comparar(a, b) {
    if (a.nome < b.nome) {
      return -1;
    }
    if (a.nome > b.nome) {
      return 1;
    }
    return 0;
  }
  
  carregaRegiao(){
    this.regiaoService.getById(this.object.usuario.bairro.regiao.id).then(regiao =>{
      this.regiaoNome = regiao.nome
     })
  }


  passar(ngForm: NgForm) {
    if(ngForm.valid){
      this.proximo = true
    }
    else {
      this.errorMessage = true
      this.message = "Existem campos obrigatórios não preenchidos"
    }
  }

  
  voltar() {
   this.proximo = false
  }

  ratingComponentClick(clickObj: any): void {

  }

  allEscolaridades(): string[] {
    return Object.keys(ESCOLARIDADE)
  }

  escolaridadeString(item): string{
    return ESCOLARIDADE[item]
  }

  allTrabalhos(): string[] {
    return Object.keys(TRABALHO)
  }

  trabalhoString(item): string{
    return TRABALHO[item]
  }


  allMoradias(): string[] {
    return Object.keys(MORADIA)
  }

  moradiaString(item): string{
    return MORADIA[item]
  }

  allParentescos(): string[] {
    return Object.keys(PARENTESCO)
  }

  parentescoString(item): string{
    return PARENTESCO[item]
  }

  allSexos(): string[] {
    return Object.keys(SEXO)
  }

  sexoString(item): string{
    return SEXO[item]
  }

  allBuscaServicos(): string[] {
    return Object.keys(BUSCA_SERVICO)
  }

  buscaServicoString(item): string{
    return BUSCA_SERVICO[item]
  }

  allEstados(): string[] {
    return Object.keys(ESTADO)
  }

  estadoString(estado): string{
    return ESTADO[estado]
  }

  allTurnos(): string[] {
    return Object.keys(TURNO)
  }

  allTurnosPublico(): string[] {
    return Object.keys(TURNO_PUBLICO)
  }

  turnoString(turno): string{
    return TURNO[turno]
  }

  turnoPubliString(turno): string{
    return TURNO_PUBLICO[turno]
  }

  tempoGestacaoString(item): string{
    return TEMPO_GESTACAO[item]
  }

  estadoCivilString(item): string{
    return ESTADO_CIVIL[item]
  }

  etniaString(item): string{
    return ETNIA[item]
  }


}