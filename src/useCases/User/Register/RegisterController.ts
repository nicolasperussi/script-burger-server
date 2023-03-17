import { Request, Response } from 'express';
import { IRegisterRequestDTO } from './RegisterDTO';
import { RegisterUseCase } from './RegisterUseCase';

export class RegisterController {
	constructor(private registerUseCase: RegisterUseCase) {}

	async handle(request: Request, response: Response): Promise<Response> {
		try {
			const { name, email, password, addresses }: IRegisterRequestDTO =
				request.body;

			await this.registerUseCase.execute({ name, email, password, addresses });

			return response.status(201).send();
		} catch (err: any) {
			return response
				.status(400)
				.json({ error: err.message || 'Unexpected error' });
		}
	}
}
