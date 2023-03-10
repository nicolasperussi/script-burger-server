import { ICategoryRepository } from '@repositories/ICategoryRepository';
import { IDeleteCategoryRequestDTO } from './DeleteCategoryDTO';

export class DeleteCategoryUseCase {
	constructor(private categoryRepository: ICategoryRepository) {}

	async execute(data: IDeleteCategoryRequestDTO) {
		await this.categoryRepository.delete(data.id);
	}
}
