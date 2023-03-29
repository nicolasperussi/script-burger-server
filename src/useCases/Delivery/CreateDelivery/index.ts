import { PostgresDeliveryRepository } from '@implementations/PostgresDeliveryRepository';
import { CreateDeliveryController } from './CreateDeliveryController';
import { CreateDeliveryUseCase } from './CreateDeliveryUseCase';

const postgresDeliveryRepository = new PostgresDeliveryRepository();

const createDeliveryUseCase = new CreateDeliveryUseCase(
	postgresDeliveryRepository
);

const createDeliveryController = new CreateDeliveryController(
	createDeliveryUseCase
);

export { createDeliveryUseCase, createDeliveryController };
