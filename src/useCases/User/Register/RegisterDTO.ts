export interface IRegisterRequestDTO {
	name: string;
	email: string;
	password: string;
	addresses: { cep: string; street: string; number: string }[];
}
