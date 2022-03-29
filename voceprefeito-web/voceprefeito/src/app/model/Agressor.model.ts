import { Droga } from './Droga.model';
import { LocalFile } from './LocalFile.model';
import { OrientacaoSexual } from './OrientacaoSexual.model';

import { Usuario } from './Usuario.model';
// IMPORT_MODEL_INSERT

export class Agressor {
    id: number
    nome : string
    estadoCivil: string

	etnia: string

	idade: number

	parentesco: string

	orientacaoSexual: OrientacaoSexual

	deficiencia: string

	desempregado: boolean

	profissao: string

	ocupacao: string

	renda: number

	localTrabalho: string

	drogas: Droga[] = []

	carteiraAssinada: boolean

	aposentado: boolean

	usaDrogas: boolean

	usaMedicamentos: boolean

	medicamentos: string

	antecedentesCriminais: boolean

	violenciaContraFamiliares: boolean

	violenciaContraOutros: boolean

	foiPreso: boolean

	motivoPrisao: string

	escolaridade: string

// MODEL_INSERT

    dataCadastro: Date
    usuarioCadastro: Usuario
    dataAtualizacao: Date
    usuarioAtualizacao: Usuario

    ativo: boolean
    selecionado: boolean = false

    listSize : number
    listPage : number
    listPageSize : number
    listOrder : string
}
