import { randomUUID } from 'crypto';

export class User {
	public readonly id: string;
	public name: string;
	public email: string;
	public password: string;

	public addresses: {
		id?: string;
		cep: string;
		street: string;
		number: string;
	}[];

	constructor(props: Omit<User, 'id'>, id?: string) {
		this.id = id || randomUUID();
		this.name = props.name;
		this.email = props.email;
		this.password = props.password;
		this.addresses = props.addresses;
	}
}
