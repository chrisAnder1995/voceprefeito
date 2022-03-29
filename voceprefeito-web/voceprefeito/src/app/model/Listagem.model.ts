import { Cidade } from 'src/app/model/Cidade.model';
import { Bairro } from 'src/app/model/Bairro.model';
import { LocalOcorrencia } from 'src/app/model/LocalOcorrencia.model';

export class ListagemPojo{
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