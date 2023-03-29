import { createProductController } from '@useCases/Product/CreateProduct';
import { deleteProductController } from '@useCases/Product/DeleteProduct';
import { findAllProductsController } from '@useCases/Product/FindAllProducts';
import { findProductsByCategoryController } from '@useCases/Product/FindProductsByCategory';
import validateProduct from '@validators/product';
import { Router } from 'express';

const router = Router();

router
	.route('/')
	.post(validateProduct, (req, res) => createProductController.handle(req, res))
	.get((req, res) => findAllProductsController.handle(res));
router
	.route('/:id')
	.delete((req, res) => deleteProductController.handle(req, res));
router
	.route('/category/:categoryId')
	.get((req, res) => findProductsByCategoryController.handle(req, res));

export default router;
