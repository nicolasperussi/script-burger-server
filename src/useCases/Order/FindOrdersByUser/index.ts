import { PostgresOrderRepository } from '@implementations/PostgresOrderRepository';
import { FindOrdersByUserController } from './FindOrdersByUserController';
import { FindOrdersByUserUseCase } from './FindOrdersByUserUseCase';

const postgresOrderRepository = new PostgresOrderRepository();

const findOrdersByUserUseCase = new FindOrdersByUserUseCase(
	postgresOrderRepository
);

const findOrderByUserController = new FindOrdersByUserController(
	findOrdersByUserUseCase
);

export { findOrdersByUserUseCase, findOrderByUserController };
