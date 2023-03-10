import { createProductController } from '@useCases/Product/CreateProduct';
import { Router } from 'express';

const router = Router();

router.route('/').post((req, res) => createProductController.handle(req, res));

export default router;
