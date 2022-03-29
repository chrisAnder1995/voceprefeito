import { LocalFile } from './LocalFile.model';
import { Usuario } from './Usuario.model';
// IMPORT_MODEL_INSERT

export class Politica {
    id: number
    nome : string

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
