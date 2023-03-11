import { PostgresProductRepository } from '@implementations/PostgresProductRepository';
import { FindAllProductsController } from './FindAllProductsController';
import { FindAllProductsUseCase } from './FindAllProductsUseCase';

const postgresProductRepository = new PostgresProductRepository();

const findAllProductsUseCase = new FindAllProductsUseCase(
	postgresProductRepository
);

const findAllProductsController = new FindAllProductsController(
	findAllProductsUseCase
);

export { findAllProductsUseCase, findAllProductsController };
