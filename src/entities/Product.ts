import { randomUUID } from 'crypto';
import { slugify } from 'src/utils/slugify';
import { Category } from './Category';

export class Product {
	public readonly id: string;
	public name: string;
	public description: string;
	public slug: string;
	public overview: string;
	public price: number;
	public categoryId?: string;
	public category?: Category;

	constructor(props: Omit<Product, 'id' | 'slug'>, id?: string) {
		this.id = id || randomUUID();
		this.name = props.name;
		this.description = props.description;
		this.slug = slugify(props.name);
		this.overview = props.overview;
		this.price = props.price;
		this.categoryId = props.categoryId;
	}
}
