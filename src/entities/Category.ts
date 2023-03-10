import { randomUUID } from 'crypto';

export class Category {
	public readonly id: string;
	public name: string;

	constructor(props: Omit<Category, 'id'>, id?: string) {
		this.id = id || randomUUID();
		this.name = props.name;
	}
}
