import { Routes } from "@angular/router";
import { IndexComponent } from "./index/index.component";
import { LoginComponent } from "./login/login.component";
import { LoginFilter } from "./login/login.filter";
import { TemplateComponent } from "./templates/template/template.component";
import { UsuarioFormComponent } from "./usuario/usuario-form/usuario-form.component";
import { UsuarioListComponent } from "./usuario/usuario-list/usuario-list.component";
import { UsuarioProfileComponent } from "./usuario/usuario-profile/usuario-profile.component";
import { AdminFilter } from "./admin.filter";
import { SupportComponent } from "./support/support.component";
import { LoginSupportComponent } from "./login/login-support/login-support.component";
import { DrogaFormComponent } from "./droga/droga-form/droga-form.component";
import { DrogaListComponent } from "./droga/droga-list/droga-list.component";
import { PaisListComponent } from "./pais/pais-list/pais-list.component";
import { PaisFormComponent } from "./pais/pais-form/pais-form.component";
import { BairroListComponent } from "./bairro/bairro-list/bairro-list.component";
import { BairroFormComponent } from "./bairro/bairro-form/bairro-form.component";
import { LocalOcorrenciaListComponent } from "./localOcorrencia/localOcorrencia-list/localOcorrencia-list.component";
import { LocalOcorrenciaFormComponent } from "./localOcorrencia/localOcorrencia-form/localOcorrencia-form.component";
import { OrientacaoSexualListComponent } from "./orientacaoSexual/orientacaoSexual-list/orientacaoSexual-list.component";
import { OrientacaoSexualFormComponent } from "./orientacaoSexual/orientacaoSexual-form/orientacaoSexual-form.component";
import { ServicoListComponent } from "./servico/servico-list/servico-list.component";
import { ViolenciaTipoListComponent } from "./violenciaTipo/violenciaTipo-list/violenciaTipo-list.component";
import { ServicoFormComponent } from "./servico/servico-form/servico-form.component";
import { ViolenciaTipoFormComponent } from "./violenciaTipo/violenciaTipo-form/violenciaTipo-form.component";
import { ListagemComponent } from "./listagem/listagem.component";
import { AtendimentoPubliFormComponent } from "./atendimento/atendimento-publi/atendimento-publi.component";
import { UserGroupsListComponent } from "./usuario/users-groups/user-groups-list/user-groups-list";
import { UserGroupFormComponent } from "./usuario/users-groups/user-groups/user-groups";
import { MotivoSolicitacaoListComponent } from "./motivoSolicitacao/motivoSolicitacao-list/motivoSolicitacao-list.component";
import { MotivoSolicitacaoFormComponent } from "./motivoSolicitacao/motivoSolicitacao-form/motivoSolicitacao-form.component";
import { StatusListComponent } from "./status/status-list/status-list.component";
import { StatusFormComponent } from "./status/status-form/status-form.component";
import { SolicitacaoListComponent } from "./atendimento/atendimento-publi-list/atendimento-publi-list.component";
import { ConfiguracaoFormComponent } from "./configuracao/pais-form/configuracao-form.component";
import { StaffFilter } from "./staff.filter";
import { LoginPublicComponent } from "./login-public/login-public.component";
import { RegiaoListComponent } from "./regiao/regiao-list/regiao-list.component";
import { RegiaoFormComponent } from "./regiao/regiao-form/regiao-form.component";

export const ROUTES: Routes = [
    {path: 'inicio',  component: LoginPublicComponent}, 
    {path: 'inicio/:id',  component: LoginPublicComponent}, 
    {path: 'atendimento/:id',  component: AtendimentoPubliFormComponent},
    {path: 'login',  component: LoginComponent}, 
    {
        path: '',
        component: TemplateComponent,
        canLoad: [LoginFilter], canActivate: [LoginFilter],
        runGuardsAndResolvers: 'always',
        children:[
          {path: '', component: IndexComponent},
          {path: 'support', component: SupportComponent},

          {path: 'formularios', component: SolicitacaoListComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},

          {path: 'listagem', component: ListagemComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},

          {path: 'bairros', component: BairroListComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'bairro/:id', component: BairroFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'bairro', component: BairroFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'locaisOcorrencia', component: LocalOcorrenciaListComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'localOcorrencia/:id', component: LocalOcorrenciaFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'localOcorrencia', component: LocalOcorrenciaFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'orientacoesSexuais', component: OrientacaoSexualListComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'orientacaoSexual/:id', component: OrientacaoSexualFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'orientacaoSexual', component: OrientacaoSexualFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'servicos', component: ServicoListComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'servico/:id', component: ServicoFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'servico', component: ServicoFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'violenciaTipos', component: ViolenciaTipoListComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'violenciaTipo/:id', component: ViolenciaTipoFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'violenciaTipo', component: ViolenciaTipoFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'paises', component: PaisListComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'pais/:id', component: PaisFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'pais', component: PaisFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'listaStatus', component: StatusListComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'status/:id', component: StatusFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'status', component: StatusFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'drogas', component: DrogaListComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'droga/:id', component: DrogaFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'droga', component: DrogaFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'motivoSolicitacoes', component: MotivoSolicitacaoListComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'motivoSolicitacao/:id', component: MotivoSolicitacaoFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'motivoSolicitacao', component: MotivoSolicitacaoFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'configuracoes', component: ConfiguracaoFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'regioes', component: RegiaoListComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'regiao/:id', component: RegiaoFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'regiao', component: RegiaoFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},

          {path: 'usuarios', component: UsuarioListComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'grupos', component: UserGroupsListComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'grupo', component: UserGroupFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'grupo/:id', component: UserGroupFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'usuarioregister', component: UsuarioFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]},
          {path: 'profile', component: UsuarioProfileComponent},
          {path: 'usuarioregister/:id', component: UsuarioFormComponent, canLoad: [AdminFilter], canActivate: [AdminFilter]}
        ]
    }
]