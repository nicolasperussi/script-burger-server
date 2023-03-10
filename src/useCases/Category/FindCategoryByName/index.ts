import { PostgresCategoryRepository } from '@implementations/PostgresCategoryRepository';
import { FindCategoryByNameController } from './FindCategoryByNameController';
import { FindCategoryByNameUseCase } from './FindCategoryByNameUseCase';

const postgresCategoryRepository = new PostgresCategoryRepository();

const findCategoryByNameUseCase = new FindCategoryByNameUseCase(
	postgresCategoryRepository
);

const findCategoryByNameController = new FindCategoryByNameController(
	findCategoryByNameUseCase
);

export { findCategoryByNameUseCase, findCategoryByNameController };
