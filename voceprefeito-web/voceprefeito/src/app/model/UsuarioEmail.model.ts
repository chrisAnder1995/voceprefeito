import { LocalFile } from './LocalFile.model';
import { Imagem } from './Imagem.model';

// IMPORT_MODEL_INSERT

export class UsuarioEmail {

    id: number
    nome : string
    sobrenome: string
	email: string
	descricao: string

	assunto : string// MODEL_INSERT

    dataCadastro: Date
    dataAtualizacao: Date

    ativo: boolean
    selecionado: boolean = false

    listSize : number
    listPage : number
    listPageSize : number
    listOrder : string
}
