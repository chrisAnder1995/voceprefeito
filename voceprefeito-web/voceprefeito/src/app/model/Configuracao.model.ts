import { LocalFile } from './LocalFile.model';
import { Usuario } from './Usuario.model';
// IMPORT_MODEL_INSERT

export class Configuracao {
    id: number
    nome : string

    qntDias : number
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
