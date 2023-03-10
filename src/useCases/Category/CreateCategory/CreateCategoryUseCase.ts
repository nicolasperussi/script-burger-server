import { Category } from '@entities/Category';
import { ICategoryRepository } from '@repositories/ICategoryRepository';
import { ICreateCategoryRequestDTO } from './CreateCategoryDTO';

export class CreateCategoryUseCase {
	constructor(private categoryRepository: ICategoryRepository) {}

	async execute(data: ICreateCategoryRequestDTO) {
		const categoryAlreadyExists = await this.categoryRepository.findByName(
			data.name
		);

		if (categoryAlreadyExists) {
			throw new Error('Category with this name already exists');
		}

		const category = new Category(data);

		await this.categoryRepository.save(category);
	}
}
