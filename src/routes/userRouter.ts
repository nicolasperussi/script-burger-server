import { authController } from '@useCases/User/Authenticate';
import { registerController } from '@useCases/User/Register';
import validateUser from '@validators/user';
import { Router } from 'express';

const router = Router();

router
	.route('/register')
	.post(validateUser, (req, res) => registerController.handle(req, res));

router.route('/login').post((req, res) => authController.handle(req, res));

export default router;