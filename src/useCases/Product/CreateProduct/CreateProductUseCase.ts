import { Product } from '@entities/Product';
import { IProductRepository } from '@repositories/IProductRepository';
import { ICreateProductRequestDTO } from './CreateProductDTO';

export class CreateProductUseCase {
	constructor(private productRepository: IProductRepository) {}

	async execute(data: ICreateProductRequestDTO) {
		const product = new Product(data);

		await this.productRepository.save(product);
	}
}
