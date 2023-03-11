import { Router } from 'express';

import { createCategoryController } from '@useCases/Category/CreateCategory';
import { findCategoryByNameController } from '@useCases/Category/FindCategoryByName';
import { deleteCategoryController } from '@useCases/Category/DeleteCategory';
import { findAllCategoriesController } from '@useCases/Category/FindAllCategories';

const router = Router();

router
	.route('/')
	.post((req, res) => createCategoryController.handle(req, res))
	.get((req, res) => findAllCategoriesController.handle(res));
router
	.route('/:id')
	.delete((req, res) => deleteCategoryController.handle(req, res));
router
	.route('/name/:name')
	.get((req, res) => findCategoryByNameController.handle(req, res));

export default router;
