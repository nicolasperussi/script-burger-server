import { PostgresDeliveryRepository } from '@implementations/PostgresDeliveryRepository';
import { FindAllDeliveriesController } from './FindAllDeliveriesController';
import { FindAllDeliveriesUseCase } from './FindAllDeliveriesUseCase';

const postgresDeliveryRepository = new PostgresDeliveryRepository();

const findAllDeliveriesUseCase = new FindAllDeliveriesUseCase(
	postgresDeliveryRepository
);

const findAllDeliveriesController = new FindAllDeliveriesController(
	findAllDeliveriesUseCase
);

export { findAllDeliveriesUseCase, findAllDeliveriesController };
