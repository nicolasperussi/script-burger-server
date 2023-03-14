import { createOrderController } from '@useCases/Order/CreateOrder';
import { deleteOrderController } from '@useCases/Order/DeleteOrder';
import { findAllOrdersController } from '@useCases/Order/FindAllOrders';
import { findOrderByUserController } from '@useCases/Order/FindOrdersByUser';
import { Router } from 'express';
const router = Router();

router
	.route('/')
	.get((req, res) => findAllOrdersController.handle(res))
	.post((req, res) => createOrderController.handle(req, res));

router
	.route('/user/:userId')
	.get((req, res) => findOrderByUserController.handle(req, res));

router
	.route('/:id')
	.delete((req, res) => deleteOrderController.handle(req, res));

export default router;
