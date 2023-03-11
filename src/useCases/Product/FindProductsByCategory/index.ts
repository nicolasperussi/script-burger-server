import { PostgresProductRepository } from '@implementations/PostgresProductRepository';
import { FindProductsByCategoryController } from './FindProductsByCategoryController';
import { FindProductsByCategoryUseCase } from './FindProductsByCategoryUseCase';

const postgresProductRepository = new PostgresProductRepository();

const findProductsByCategoryUseCase = new FindProductsByCategoryUseCase(
	postgresProductRepository
);

const findProductsByCategoryController = new FindProductsByCategoryController(
	findProductsByCategoryUseCase
);

export { findProductsByCategoryUseCase, findProductsByCategoryController };
