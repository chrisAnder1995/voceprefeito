import { Usuario } from "./Usuario.model"

export class UserGroups {

    id : number
    description: string
    ativo: boolean

    // MODEL_INSERT

    dataCadastro: Date
    userCadastro: Usuario
    dataAtualizacao: Date
    userAtualizacao: Usuario

    selecionado: boolean = false
    totalAccess: boolean = false

    listSize : number
    listPage : number
    listPageSize : number
    listOrder : string

}
