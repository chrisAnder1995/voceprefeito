import { Status} from './StatusAtendimento.model';
import { LocalFile } from './LocalFile.model';
import { Mulher } from './Mulher.model';

import { Agressor } from './Agressor.model';

import { Ocorrencia } from './Ocorrencia.model';

import { Usuario } from './Usuario.model';
import { Servico } from './Servico.model';
import { LocalOcorrencia } from './LocalOcorrencia.model';
import { Cidade } from './Cidade.model';
import { Bairro } from './Bairro.model';
import { UserGroups } from './UserGroups.model';
import { Regiao } from './Regiao.model';
import { Politica } from './Politica.model';
// IMPORT_MODEL_INSERT

export class Atendimento {
    
    id: number
    usuario : Usuario

    ativo: boolean
    status: string
    
    orderAsc : boolean

    politicaUm : Politica

    politicaDois : Politica

    politicaTres : Politica

    servicoUm : Servico

    servicoDois : Servico

    servicoTres : Servico

    sugestao : string

    sugestaoDois : string

// MODEL_INSERT

    dataCadastro: Date
    usuarioCadastro: Usuario
    dataAtualizacao: Date
    usuarioAtualizacao: Usuario

    selecionado: boolean = false

    listSize : number
    listPage : number
    listPageSize : number
    listOrder : string

    //POJO
    locaisOcorrencia : LocalOcorrencia[]
    dataCadastroInicio : Date
    dataCadastroFim : Date
    dataOcorrenciaInicio : Date
    dataOcorrenciaFim : Date
    cidade : Cidade
    bairro : Bairro
    violenciaCategoria : string
    idadeInicio : number
    idadeFim : number
    rendaInicio : number
    rendaFim : number
}
