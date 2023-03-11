import { createProductController } from '@useCases/Product/CreateProduct';
import { findAllProductsController } from '@useCases/Product/FindAllProducts';
import { Router } from 'express';

const router = Router();

router
	.route('/')
	.post((req, res) => createProductController.handle(req, res))
	.get((req, res) => findAllProductsController.handle(res));

export default router;
