import { createProductController } from '@useCases/Product/CreateProduct';
import { findAllProductsController } from '@useCases/Product/FindAllProducts';
import { findProductsByCategoryController } from '@useCases/Product/FindProductsByCategory';
import { Router } from 'express';

const router = Router();

router
	.route('/')
	.post((req, res) => createProductController.handle(req, res))
	.get((req, res) => findAllProductsController.handle(res));
router
	.route('/category/:categoryId')
	.get((req, res) => findProductsByCategoryController.handle(req, res));

export default router;
