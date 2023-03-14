import { PostgresOrderRepository } from '@implementations/PostgresOrderRepository';
import { FindAllOrdersController } from './FindAllOrdersController';
import { FindAllOrdersUseCase } from './FindAllOrdersUseCase';

const postgresOrderRepository = new PostgresOrderRepository();

const findAllOrdersUseCase = new FindAllOrdersUseCase(postgresOrderRepository);
const findAllOrdersController = new FindAllOrdersController(
	findAllOrdersUseCase
);

export { findAllOrdersUseCase, findAllOrdersController };
