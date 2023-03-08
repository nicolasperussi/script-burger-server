import { create } from 'domain';
import { Router } from 'express';
import {
	createCategory,
	deleteCategory,
	getAllCategories,
	getCategoryById,
	updateCategory,
} from '@controllers/CategoryController';

const router = Router();

router.route('/').post(createCategory).get(getAllCategories);
router
	.route('/:id')
	.get(getCategoryById)
	.delete(deleteCategory)
	.patch(updateCategory);

export default router;
