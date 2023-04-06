import { PostgresOrderRepository } from '@implementations/PostgresOrderRepository';
import { UpdateOrderStatusUseCase } from './UpdateOrderStatusUseCase';
import { UpdateOrderStatusController } from './UpdateOrderStatusController';

const postgresOrderRepository = new PostgresOrderRepository();

const updateOrderStatusUseCase = new UpdateOrderStatusUseCase(
	postgresOrderRepository
);

const updateOrderStatusController = new UpdateOrderStatusController(
	updateOrderStatusUseCase
);

export { updateOrderStatusUseCase, updateOrderStatusController };
