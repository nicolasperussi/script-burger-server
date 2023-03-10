import { Product } from '@entities/Product';

export interface IProductRepository {
	findAll(): Promise<Product[]>;
	findById(id: string): Promise<Product | null>;
	save(product: Product): Promise<void>;
	delete(id: string): Promise<void>;
}
