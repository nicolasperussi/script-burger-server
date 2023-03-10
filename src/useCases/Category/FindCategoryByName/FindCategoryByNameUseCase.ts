import { ICategoryRepository } from '@repositories/ICategoryRepository';
import { IFindCategoryByNameRequestDTO } from './FindCategoryByNameDTO';

export class FindCategoryByNameUseCase {
	constructor(private categoryRepository: ICategoryRepository) {}

	async execute(data: IFindCategoryByNameRequestDTO) {
		const category = await this.categoryRepository.findByName(data.name);

		if (!category) {
			throw new Error('Category with name ' + data.name + ' not found');
		}

		return category;
	}
}
