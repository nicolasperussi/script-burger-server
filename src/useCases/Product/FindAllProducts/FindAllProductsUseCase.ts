import { IProductRepository } from '@repositories/IProductRepository';

export class FindAllProductsUseCase {
	constructor(private productRepository: IProductRepository) {}

	async execute() {
		const categories = await this.productRepository.findAll();

		return categories;
	}
}
