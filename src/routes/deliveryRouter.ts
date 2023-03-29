import { createDeliveryController } from '@useCases/Delivery/CreateDelivery';
import { findAllDeliveriesController } from '@useCases/Delivery/FindAllDeliveries';
import validateDelivery from '@validators/delivery';
import { Router } from 'express';
const router = Router();

router
	.route('/')
	.get((req, res) => findAllDeliveriesController.handle(res))
	.post(validateDelivery, (req, res) =>
		createDeliveryController.handle(req, res)
	);

export default router;
