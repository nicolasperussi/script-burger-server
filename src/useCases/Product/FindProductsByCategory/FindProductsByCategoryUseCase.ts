import { IProductRepository } from '@repositories/IProductRepository';
import { IFindProductsByCategoryRequestDTO } from './FindProductsByCategoryDTO';

export class FindProductsByCategoryUseCase {
	constructor(private productRepository: IProductRepository) {}

	async execute(data: IFindProductsByCategoryRequestDTO) {
		const products = await this.productRepository.findByCategory(
			data.categoryId
		);

		return products;
	}
}
