import { PostgresCategoryRepository } from '@implementations/PostgresCategoryRepository';
import { CreateCategoryController } from './CreateCategoryController';
import { CreateCategoryUseCase } from './CreateCategoryUseCase';

const postgresCategoryRepository = new PostgresCategoryRepository();

const createCategoryUseCase = new CreateCategoryUseCase(
	postgresCategoryRepository
);

const createCategoryController = new CreateCategoryController(
	createCategoryUseCase
);

export { createCategoryUseCase, createCategoryController };
