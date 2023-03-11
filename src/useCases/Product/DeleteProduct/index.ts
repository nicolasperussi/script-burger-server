import { PostgresProductRepository } from '@implementations/PostgresProductRepository';
import { DeleteProductUseCase } from './DeleteProductUseCase';
import { DeleteProductController } from './DeleteProductController';

const postgresProductRepository = new PostgresProductRepository();

const deleteProductUseCase = new DeleteProductUseCase(
	postgresProductRepository
);

const deleteProductController = new DeleteProductController(
	deleteProductUseCase
);

export { deleteProductUseCase, deleteProductController };
