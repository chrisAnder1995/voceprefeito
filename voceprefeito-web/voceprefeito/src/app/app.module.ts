import { StatusListComponent } from './status/status-list/status-list.component';
import { StatusFormComponent } from './status/status-form/status-form.component';
import { MotivoSolicitacaoService } from './service/motivoSolicitacao.service';
import { SolicitacaoService } from './service/solicitacao.service';
import { AuthService } from './service/auth.service';
import { PhoneMaskDirective } from './masks/phone-mask.directive';
import { NgModule, LOCALE_ID } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import localePT from '@angular/common/locales/pt';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { StorageServiceModule } from 'angular-webstorage-service';
import { NgxMaskModule } from 'ngx-mask';
import { AppComponent } from './app.component';
import { ROUTES } from './app.routes';
import { AppService } from './app.service';
import { ContentComponent } from './content/content.component';
import { ControlSidebarComponent } from './control-sidebar/control-sidebar.component';
import { FooterComponent } from './footer/footer.component';
import { FormGroupComponent } from './forms/form-group/form-group.component';
import { ModalComponent } from './forms/modal/modal.component';
import { HeaderComponent } from './header/header.component';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './login/login.component';
import { LoginFilter } from './login/login.filter';
import { MainSidebarComponent } from './main-sidebar/main-sidebar.component';
import { MenuComponent } from './main-sidebar/menu/menu.component';
import { ImagemService } from './service/imagem.service';
import { LocalFileService } from './service/localFile.service';
import { UsuarioService } from './service/usuario.service';
import { TemplateComponent } from './templates/template/template.component';
import { UsuarioFormComponent } from './usuario/usuario-form/usuario-form.component';
import { UsuarioListComponent } from './usuario/usuario-list/usuario-list.component';
import { MessageComponent } from './forms/message/message.component'
import { UsuarioProfileComponent } from './usuario/usuario-profile/usuario-profile.component';
import { AdminFilter } from './admin.filter';
import { StaffFilter } from './staff.filter';
import { SafeHtmlPipe } from './pipe/safeHtmlPipe';
import { SupportComponent } from './support/support.component';
import { LoginFooterComponent } from './login/login-footer/login-footer.component';
import { LoginSupportComponent } from './login/login-support/login-support.component';
import { CPFMaskDirective } from './masks/cpf-mask.directive';
import { CNPJMaskDirective } from './masks/cnpj-mask.directive';
import { CEPMaskDirective } from './masks/cep-mask.directive';

import { CepPipe } from './pipe/cepPipe';
import { CpfPipe } from './pipe/cpfPipe';
import { PhonePipe } from './pipe/phonePipe';
import { CnpjPipe } from './pipe/cnpjPipe';

import { CurrencyMaskModule } from "ng2-currency-mask";
import { CurrencyMaskConfig, CURRENCY_MASK_CONFIG } from "ng2-currency-mask/src/currency-mask.config";
import { DateMaskDirective } from './masks/date-mask.directive';
import { HttpsRequestInterceptor } from './service/interceptor.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ImageCropperModule } from 'ngx-image-cropper';

import { DynamicDialogRef, DynamicDialogConfig, DialogService } from 'primeng-lts/api';

import { AtendimentoService } from "./service/atendimento.service";
import { ViolenciaTipoFormComponent } from "./violenciaTipo/violenciaTipo-form/violenciaTipo-form.component";
import { ViolenciaTipoListComponent } from "./violenciaTipo/violenciaTipo-list/violenciaTipo-list.component";
import { ViolenciaTipoService } from "./service/violenciaTipo.service";
import { BairroListComponent } from './bairro/bairro-list/bairro-list.component';
import { BairroFormComponent } from './bairro/bairro-form/bairro-form.component';
import { LocalOcorrenciaListComponent } from './localOcorrencia/localOcorrencia-list/localOcorrencia-list.component';
import { LocalOcorrenciaFormComponent } from './localOcorrencia/localOcorrencia-form/localOcorrencia-form.component';
import { OrientacaoSexualListComponent } from './orientacaoSexual/orientacaoSexual-list/orientacaoSexual-list.component';
import { OrientacaoSexualFormComponent } from './orientacaoSexual/orientacaoSexual-form/orientacaoSexual-form.component';
import { ServicoFormComponent } from './servico/servico-form/servico-form.component';
import { ServicoListComponent } from './servico/servico-list/servico-list.component';
import { DrogaFormComponent } from './droga/droga-form/droga-form.component';
import { DrogaListComponent } from './droga/droga-list/droga-list.component';
import { PaisFormComponent } from './pais/pais-form/pais-form.component';
import { PaisListComponent } from './pais/pais-list/pais-list.component';
import { ListagemComponent } from './listagem/listagem.component';
import { CidadeService } from './service/cidade.service';
import { BairroService } from './service/bairro.service';
import { LocalOcorrenciaService } from './service/localOcorrencia.service';
import { OrientacaoSexualService } from './service/orientacaoSexual.service';
import { ServicoService } from './service/servico.service';
import { PaisService } from './service/pais.service';
import { DrogaService } from './service/droga.service';
import { MembroFamiliaService } from './service/membroFamilia.service';
import { MulherService } from './service/mulher.service';
import { AgressorService } from './service/agressor.service';
import { OcorrenciaService } from './service/ocorrencia.service';
import { AtendimentoPubliFormComponent } from './atendimento/atendimento-publi/atendimento-publi.component';
import { UserGroupsListComponent } from './usuario/users-groups/user-groups-list/user-groups-list';
import { UserGroupFormComponent } from './usuario/users-groups/user-groups/user-groups';
import { UserGroupsService } from './service/userGroups.service';
import { MotivoSolicitacaoFormComponent } from './motivoSolicitacao/motivoSolicitacao-form/motivoSolicitacao-form.component';
import { MotivoSolicitacaoListComponent } from './motivoSolicitacao/motivoSolicitacao-list/motivoSolicitacao-list.component';
import { StatusService } from './service/statusAtendimento.service';
import { HeaderPublicComponent } from './header-publi/header-publi.component';
import { SolicitacaoListComponent } from './atendimento/atendimento-publi-list/atendimento-publi-list.component';
import { ConfiguracaoFormComponent } from './configuracao/pais-form/configuracao-form.component';
import { ConfiguracaoService } from './service/configuracao.service';
import { LoginPublicComponent} from './login-public/login-public.component';
import { RegiaoListComponent } from './regiao/regiao-list/regiao-list.component';
import { RegiaoFormComponent } from './regiao/regiao-form/regiao-form.component';
import { RegiaoService } from './service/Regiao.service';
import { PoliticaService } from './service/politica.service';
import { DynamicDialogModule } from 'primeng-lts/components/dynamicdialog/dynamicdialog';
import {TableModule} from 'primeng-lts/table';
import {CheckboxModule} from 'primeng-lts/checkbox';
import {RadioButtonModule} from 'primeng-lts/radiobutton';
import {InputTextareaModule} from 'primeng-lts/inputtextarea';
import { ChartComponent } from './chart/chart.component';
// INSERT_INSERT_MODULE
 
export const CustomCurrencyMaskConfig: CurrencyMaskConfig = {
    align: "right",
    allowNegative: true,
    decimal: ",",
    precision: 2,
    prefix: "R$ ",
    suffix: "",
    thousands: "."
};

registerLocaleData(localePT);

@NgModule({
  declarations: [
    AppComponent,
    TemplateComponent,
    HeaderComponent,
    ContentComponent,
    FooterComponent,
    FormGroupComponent,
    LoginComponent,
    IndexComponent,
    MainSidebarComponent,
    ControlSidebarComponent,
    MenuComponent,
    PhoneMaskDirective,
    CPFMaskDirective,
    CNPJMaskDirective,
    CEPMaskDirective,
    DateMaskDirective,
    HeaderPublicComponent,
    UsuarioFormComponent,
    ModalComponent,
    UsuarioListComponent,
    MessageComponent,
    UsuarioProfileComponent,
    SafeHtmlPipe,
    CepPipe,
    CpfPipe,
    CnpjPipe,
    PhonePipe,
    SupportComponent,
    LoginFooterComponent,
    LoginSupportComponent,
    BairroFormComponent,
    BairroListComponent,
    LocalOcorrenciaFormComponent,
    LocalOcorrenciaListComponent,
    OrientacaoSexualFormComponent,
    OrientacaoSexualListComponent,
    ServicoFormComponent,
    ServicoListComponent,
    ViolenciaTipoFormComponent,
    ViolenciaTipoListComponent,
    DrogaFormComponent,
    DrogaListComponent,
    PaisFormComponent,
    PaisListComponent,
    ListagemComponent,
    StatusFormComponent,
    StatusListComponent,
    SolicitacaoListComponent,
    RegiaoListComponent,
    RegiaoFormComponent,
    ConfiguracaoFormComponent,
AtendimentoPubliFormComponent,
ViolenciaTipoFormComponent,
ViolenciaTipoListComponent,
UserGroupsListComponent,
UserGroupFormComponent,
LoginPublicComponent,
MotivoSolicitacaoListComponent,
MotivoSolicitacaoFormComponent,
ChartComponent

// INSERT_COMPONENTS
  ],
  imports: [
    DynamicDialogModule,
    // TieredMenuModule,
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(ROUTES, {useHash:true}),
    NgxMaskModule.forRoot(),
    NgxMaskModule,
     TableModule,
     CheckboxModule,
    ReactiveFormsModule,
    // GalleriaModule,
     RadioButtonModule,
     InputTextareaModule,
    NgxMaskModule,
    CurrencyMaskModule,
    // CalendarModule,
    BrowserAnimationsModule,
    // ChartModule,
    HttpClientModule,
    ImageCropperModule,
    // SplitButtonModule,

    StorageServiceModule
  ],
  providers: [
    {provide: LOCALE_ID, useValue: 'pt'},
    {provide: HTTP_INTERCEPTORS, useClass: HttpsRequestInterceptor, multi: true},
    {provide: CURRENCY_MASK_CONFIG, useValue: CustomCurrencyMaskConfig },
    
    AppService, 
    AuthService,
    RegiaoService,


    UsuarioService,
    LocalFileService,
    ImagemService,
    PoliticaService,
    AtendimentoService,
    ViolenciaTipoService,
    CidadeService,
    StatusService,
    AtendimentoService,
    BairroService,
    LocalOcorrenciaService,
    OrientacaoSexualService,
    ServicoService,
    ViolenciaTipoService,
    CidadeService,
    PaisService,
    DrogaService,
    MembroFamiliaService,
    MulherService,
    AgressorService,
    OcorrenciaService,
    UserGroupsService,
    SolicitacaoService,
    MotivoSolicitacaoService,
    ConfiguracaoService,
   
// INSERT_SERVICE

    LoginFilter,
    AdminFilter,
    StaffFilter,

    DialogService,
    DynamicDialogRef, 
    DynamicDialogConfig
  ],
  exports: [
    PhoneMaskDirective,
    CPFMaskDirective,
    CNPJMaskDirective,
    CEPMaskDirective,
    DateMaskDirective
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
