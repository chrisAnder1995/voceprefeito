import { Usuario } from './Usuario.model';
// IMPORT_MODEL_INSERT

export class Status {

    id: number
    nome : string
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
