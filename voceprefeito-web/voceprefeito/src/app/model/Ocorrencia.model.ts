import { LocalFile } from './LocalFile.model';
import { LocalOcorrencia } from './LocalOcorrencia.model';

import { Bairro } from './Bairro.model';

import { Usuario } from './Usuario.model';
import { ViolenciaTipo } from './ViolenciaTipo.model';
// IMPORT_MODEL_INSERT

export class Ocorrencia {
    id: number
    data: Date
    turno: string
    dataHora: string
    localOcorrencia: LocalOcorrencia
    localOcorrencia_id : number
    endereco: string
    numero: string
    bairro: Bairro
    bairro_id : number
    violencias: ViolenciaTipo[] = []
    ativo: boolean

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
