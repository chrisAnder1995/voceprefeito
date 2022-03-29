export enum ESTADO {
    AC = "Acre", 
	AL = "Alagoas", 
	AP = "Amapa", 
	AM = "Amazonas", 
	BA = "Bahia", 
	CE = "Ceará", 
	DF = "Distrito Federal", 
	ES = "Espirito Santo", 
	GO = "Goiás", 
	MA = "Maranhão", 
	MT = "Mato Grosso", 
	MS = "Mato Grosso do Sul", 
	MG = "Minas Gerais", 
	PA = "Pará", 
	PB = "Paraíba", 
	PR = "Paraná", 
	PE = "Pernambuco", 
	PI = "Piauí",
	RJ = "Rio de Janeiro", 
	RN = "Rio Grande do Norte", 
	RS = "Rio Grande do Sul", 
	RO = "Rondonia", 
	RR = "Roraima", 
	SC = "Santa Catarina", 
	SP = "São Paulo", 
	SE = "Sergipe", 
	TO = "Tocantins"
}

export enum PERMISSAO {
	ATENDENTE = "Atendente",
	ACESSO_TOTAL = "Acesso Total"
}

export enum VIOLENCIA_CATEGORIA {
	FISICA = "Física",
	PSICOLOGICA = "Psicológica",
	PATRIMONIAL = "Patrimonial",
	SEXUAL = "Sexual",
	MORAL = "Moral"
}

export enum TEMPO_GESTACAO {
	NAO = "Não",
	TRIMESTRE1 = "1º Trimestre",
	TRIMESTRE2 = "2º Trimestre",
	TRIMESTRE3 = "3º Trimestre"
}

export enum SEXO {
	Masculino = "Masculino",
	Feminino = "Feminino",
	Outro = "Outro"
}

export enum TURNO {
	MANHA = "Manhã",
	TARDE = "Tarde",
	NOITE = "Noite",
	MADRUGADA = "Madrugada"
}

export enum TURNO_PUBLICO {
	MANHA = "Manhã",
	TARDE = "Tarde",
}

export enum PARENTESCO {
	MAEPAI = "Mãe/Pai",
	IRMAO = "Irmã(ão)",
	CONJUGE = "Cônjuge",
	FILHO = "Filha(o)",
	NETO = "Neta(o)",
	VIUVO = "Viúva(o)",
	COMPANHEIRO = "Companheira(o)",
	SENTENCA_JUDICIAL = "Sentença Judicial",
	AVO = "Avó(ô)", 
	OUTRO = "Outro"
}

export enum MORADIA {
	PROPRIA = "Própria",
	ALUGADA = "Alugada",
	CEDIDA = "Cedida",
	OCUPADA = "Ocupada",
	BENEFICIO_SOCIAL = "Benefício Social",
	TERCEIROS = "Terceiros",
	OUTROS = "Outros"
}

export enum ETNIA {
	AMARELA = "Amarela",
	BRANCA = "Branca",
	INDIGENA = "Indí­gena",
	NEGRA = "Negra",
	PARDA = "Parda",
	OUTRA = "Outra"
}

export enum ESTADO_CIVIL {
	SOLTEIRO = "Solteira(o)",
	CASADO = "Casada(o)",
	DIVORCIADO = "Divorciada(o)",
	VIUVO = "Viúva(o)"
}

export enum STATUS {
	NOVA_DENUNCIA = "Nova Denúncia",
    NOVA_SUSPEITA = "Nova Suspeita",
    EM_ATENDIMENTO = "Em Atendimento",
    SUSPEITA_DESCARTADA = "Suspeita Descartada",
    FECHADO = "Fechado"
}

export enum ESCOLARIDADE {
	SEM_ESCOLARIDADE       = "Sem escolaridade",          
	FUNDAMENTAL_INCOMPLETO = "Fundamental Incompleto",
	FUNDAMENTAL_COMPLETO   = "Fundamental Completo",  
	MEDIO_INCOMPLETO       = "Médio Incompleto",      
	MEDIO_COMPLETO         = "Médio Completo",        
	SUPERIOR_INCOMPLETO    = "Superior Incompleto",   
	SUPERIOR_COMPLETO      = "Superior Completo",     
}

export enum TRABALHO {
	TRABALHO_FORMALMENTE  = "Trabalhando Formalmente",          
	FAZENDO_BICO 		  = "Trabalho Fazendo Bicos",
	EMPREENDEDOR   		  = "Sou Empreendendor",  
	DESEMPREGADO       	  = "Sou Desempregador",      
	APOSENTADO         	  = "Sou Aposentado",        
	ESTAGIARIO    		  = "Sou Estudante/Estagiario",   
}

export enum DEFICIENCIA {
	NENHUMA = "Nenhuma",
	AUDITIVA = "Auditiva",
	FISICA = "Física",
	MENTAL = "Mental",
	VISUAL = "Visual",
	MULTIPLA = "Múltipla"
}

export enum BUSCA_SERVICO {
	ESPONTANEA = "Espontânea",
	ENCAMINHADA = "Encaminhada"
}