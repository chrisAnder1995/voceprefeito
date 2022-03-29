import { Bairro } from './Bairro.model';
import { Cidade } from './Cidade.model';
import { LocalFile } from './LocalFile.model';
import { MotivoSolicitacao } from './MotivoSolicitacao.model';
import { Usuario } from './Usuario.model';
// IMPORT_MODEL_INSERT

export class Solicitacao {
    id: number
    nome : string
    telefone : string
    endereco : string
    bairro : Bairro
    cidade : Cidade
    complemento : string
    descricao : string
    turno : string
    motivoSolicitacao : MotivoSolicitacao
    // MODEL_INSERT

    dataCadastro: Date
    usuarioCadastro: Usuario
    dataAtualizacao: Date
    usuarioAtualizacao: Usuario

    orderAsc : boolean

    ativo: boolean
    selecionado: boolean = false

    listSize : number
    listPage : number
    listPageSize : number
    listOrder : string
}
