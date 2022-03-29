import { LocalFile } from './LocalFile.model';
import { Politica } from './Politica.model';
import { Usuario } from './Usuario.model';
// IMPORT_MODEL_INSERT

export class Servico {
    id: number
    nome : string
    politica : Politica
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
