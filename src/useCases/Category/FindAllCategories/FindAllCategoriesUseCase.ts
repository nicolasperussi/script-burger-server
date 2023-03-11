import { ICategoryRepository } from '@repositories/ICategoryRepository';

export class FindAllCategoriesUseCase {
	constructor(private categoryRepository: ICategoryRepository) {}

	async execute() {
		const categories = await this.categoryRepository.findAll();

		return categories;
	}
}
