import { PostgresCategoryRepository } from '@repositories/implementations/PostgresCategoryRepository';
import { DeleteCategoryController } from './DeleteCategoryController';
import { DeleteCategoryUseCase } from './DeleteCategoryUseCase';

const postgresCategoryRepository = new PostgresCategoryRepository();

const deleteCategoryUseCase = new DeleteCategoryUseCase(
	postgresCategoryRepository
);

const deleteCategoryController = new DeleteCategoryController(
	deleteCategoryUseCase
);

export { deleteCategoryUseCase, deleteCategoryController };
