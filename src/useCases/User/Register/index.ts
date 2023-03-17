import { PostgresUserRepository } from '@implementations/PostgresUserRepository';
import { RegisterController } from './RegisterController';
import { RegisterUseCase } from './RegisterUseCase';

const postgresUserRepository = new PostgresUserRepository();

const registerUseCase = new RegisterUseCase(postgresUserRepository);

const registerController = new RegisterController(registerUseCase);

export { registerUseCase, registerController };
