import { LocalFile } from './LocalFile.model';
import { ViolenciaCategoria } from './ViolenciaCategoria.model';

import { Usuario } from './Usuario.model';
// IMPORT_MODEL_INSERT

export class ViolenciaTipo {
    id: number
    nome : string
    pena: string

	artigo: string

	lei: string

    violenciaCategoria: string

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
