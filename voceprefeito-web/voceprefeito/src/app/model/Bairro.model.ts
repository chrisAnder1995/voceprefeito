import { Usuario } from './Usuario.model';
import { Regiao } from './Regiao.model';
// IMPORT_MODEL_INSERT

export class Bairro {
    
    id: number
    nome : string
    regiao: Regiao

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

    count : number = 0
}
