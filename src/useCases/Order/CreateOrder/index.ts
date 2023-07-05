import { PostgresOrderRepository } from "@implementations/PostgresOrderRepository";
import { CreateOrderController } from "./CreateOrderController";
import { CreateDineInOrderUseCase } from "./CreateDineInOrderUseCase";
import { CreateDeliveryOrderUseCase } from "./CreateDeliveryOrderUseCase";

const postgresOrderRepository = new PostgresOrderRepository();

const createDineInOrderUseCase = new CreateDineInOrderUseCase(
  postgresOrderRepository
);
const createDeliveryOrderUseCase = new CreateDeliveryOrderUseCase(
  postgresOrderRepository
);

const createOrderController = new CreateOrderController(
  createDineInOrderUseCase,
  createDeliveryOrderUseCase
);

export { createDineInOrderUseCase, createOrderController };
