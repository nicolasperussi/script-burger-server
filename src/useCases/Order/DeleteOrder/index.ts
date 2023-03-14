import { PostgresOrderRepository } from '@implementations/PostgresOrderRepository';
import { DeleteOrderController } from './DeleteOrderController';
import { DeleteOrderUseCase } from './DeleteOrderUseCase';

const postgresOrderRepository = new PostgresOrderRepository();

const deleteOrderUseCase = new DeleteOrderUseCase(postgresOrderRepository);

const deleteOrderController = new DeleteOrderController(deleteOrderUseCase);

export { deleteOrderUseCase, deleteOrderController };
