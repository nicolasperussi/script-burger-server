import { PostgresOrderRepository } from '@implementations/PostgresOrderRepository';
import { CreateOrderController } from './CreateOrderController';
import { CreateOrderUseCase } from './CreateOrderUseCase';

const postgresOrderRepository = new PostgresOrderRepository();

const createOrderUseCase = new CreateOrderUseCase(postgresOrderRepository);

const createOrderController = new CreateOrderController(createOrderUseCase);

export { createOrderUseCase, createOrderController };
