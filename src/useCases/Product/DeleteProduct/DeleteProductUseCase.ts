import { IProductRepository } from '@repositories/IProductRepository';
import { IDeleteProductRequestDTO } from './DeleteProductDTO';

export class DeleteProductUseCase {
	constructor(private productRepository: IProductRepository) {}

	async execute(data: IDeleteProductRequestDTO) {
		const product = await this.productRepository.findById(data.id);

		if (!product) {
			throw new Error('Product not found');
		}

		await this.productRepository.delete(data.id);
	}
}
