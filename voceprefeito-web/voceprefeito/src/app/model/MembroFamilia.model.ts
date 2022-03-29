import { LocalFile } from './LocalFile.model';
import { Mulher } from './Mulher.model';

import { Usuario } from './Usuario.model';
// IMPORT_MODEL_INSERT

export class MembroFamilia {
    id: number
    nome : string
    	sexo: string

	idade: number

	parentesco: string

	ocupacao: string

	renda: number

	mulher: Mulher

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
