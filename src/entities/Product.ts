import { randomUUID } from 'crypto';
import { Category } from './Category';

export class Product {
	public readonly id: string;
	public name: string;
	public description: string;
	public price: number;
	public categoryId?: string;
	public category?: Category;

	constructor(props: Omit<Product, 'id'>, id?: string) {
		this.id = id || randomUUID();
		this.name = props.name;
		this.description = props.description;
		this.price = props.price;
		this.categoryId = props.categoryId;
	}
}
