import { PostgresCategoryRepository } from '@implementations/PostgresCategoryRepository';
import { FindAllCategoriesController } from './FindAllCategoriesController';
import { FindAllCategoriesUseCase } from './FindAllCategoriesUseCase';

const postgresCategoryRepository = new PostgresCategoryRepository();

const findAllCategoriesUseCase = new FindAllCategoriesUseCase(
	postgresCategoryRepository
);

const findAllCategoriesController = new FindAllCategoriesController(
	findAllCategoriesUseCase
);

export { findAllCategoriesUseCase, findAllCategoriesController };
