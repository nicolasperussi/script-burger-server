import { Product } from '@entities/Product';

export interface IProductRepository {
	findAll(): Promise<Product[]>;
	findByCategory(categoryId: string): Promise<Product[]>;
	save(product: Product): Promise<void>;
	delete(id: string): Promise<void>;
}
