import { PostgresUserRepository } from '@implementations/PostgresUserRepository';
import { AuthController } from './AuthController';
import { AuthUseCase } from './AuthUseCase';

const postgresUserRepository = new PostgresUserRepository();

const authUseCase = new AuthUseCase(postgresUserRepository);

const authController = new AuthController(authUseCase);

export { authUseCase, authController };
