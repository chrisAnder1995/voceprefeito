import { LocalFile } from './LocalFile.model';
import { Bairro } from './Bairro.model';

import { Pais } from './Pais.model';

import { TempoGestacao } from './TempoGestacao.model';

import { Regiao } from './Regiao.model';

import { OrientacaoSexual } from './OrientacaoSexual.model';

import { Usuario } from './Usuario.model';
import { MembroFamilia } from './MembroFamilia.model';
import { Droga } from './Droga.model';
// IMPORT_MODEL_INSERT

export class Mulher {
    
	id: number
    nome: string
    idade: number
    nomeMae: string
    endereco: string
    numero: string
    bairro: Bairro
    bairro_id : number
    complemento: string
    pontoReferencia: string
    telefone1: string
    telefone2: string
    dataNascimento: Date
    naturalidade: string
    nacionalidade: Pais
    nacionalidade_id : number
    tempoGestacao: string
    estadoCivil: string
    Regiao: Regiao
    Regiao_id: number
    escolaridade: string
    etnia: string
    orientacaoSexual: OrientacaoSexual
    orientacaoSexual_id: number
    deficiencia: string
    rg: string
    rgUf: string
    rgOrgao: string
    cpf: string
    usaDrogas: boolean
    drogas: Droga[] = []
    usaMedicamentos: boolean
    medicamentos: string
    desempregada: boolean
    profissao: string
    localTrabalho: string
    rendaIndividual: number
    carteiraAssinada: boolean
    aposentada: boolean
    outrasRendasDomiciliares: number
    moradia: string
    observacao: string
    membrosFamilia: MembroFamilia[] = []
    ativo: boolean
    riscoDeVida: boolean
    sosMulher: boolean
    cancelamento: boolean
    numeroSosMulher : string

// MODEL_INSERT

    dataCadastro: Date
    usuarioCadastro: Usuario
    dataAtualizacao: Date
    usuarioAtualizacao: Usuario

    selecionado: boolean = false

    listSize : number
    listPage : number
    listPageSize : number
    listOrder : string
}
