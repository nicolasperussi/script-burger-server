import {
	createProduct,
	deleteProduct,
	getAllProducts,
	getProductById,
	getProductsByCategory,
	updateProduct,
} from '@controllers/ProductController';
import { Router } from 'express';

const router = Router();

router.route('/').post(createProduct).get(getAllProducts);
router
	.route('/:id')
	.get(getProductById)
	.patch(updateProduct)
	.delete(deleteProduct);
router.route('/category/:category').get(getProductsByCategory);

export default router;
