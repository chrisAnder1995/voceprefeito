import { Imagem } from './Imagem.model';
import { Regiao } from './Regiao.model';
import { Bairro } from './Bairro.model';

// IMPORT_MODEL_INSERT

export class Usuario {
    
    id: number
    nome : string
    cpf : string
    dataNascimento : Date
    sexo : string
    escolaridade  : string
    trabalho  : string
    regiao : Regiao
	email: string
    bairro : Bairro

    avaliacao : number

	senha: string

	permissao: string

    opcional : boolean

	imagem: Imagem

	jwt : string// MODEL_INSERT

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
