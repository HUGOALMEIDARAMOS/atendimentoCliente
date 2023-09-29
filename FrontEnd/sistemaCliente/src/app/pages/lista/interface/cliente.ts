export interface ClienteLista{
  content: Cliente[];
  pageable: {
		pageNumber: number,
		pageSize: number,
		sort: {
			empty: boolean,
			unsorted: boolean,
			sorted: boolean
		},
		offset: number,
		unpaged: boolean,
		paged: boolean
	},
	last: boolean,
	totalElements: number,
	totalPages: number,
	first: boolean,
	size: number,
	number: number,
	sort: {
		empty: boolean,
		unsorted: boolean,
		sorted: boolean
	},
	numberOfElements: number,
	empty: boolean
}

export interface Cliente {

  clienteType: string;
  cnpj: string;
  razao_social: string;
  mcc: string;
  cpf_contato: string;
  nome_contato: string;
  email: string;
  cpf: string;
  nome_pessoa_fisica: string;
  dataCriacao: string;
}
