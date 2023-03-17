import { Request, Response } from 'express';
import { AuthUseCase } from './AuthUseCase';

export class AuthController {
	constructor(private authUseCase: AuthUseCase) {}

	async handle(request: Request, response: Response): Promise<Response> {
		try {
			const { email, password } = request.body;
			const user = await this.authUseCase.execute({ email, password });
			return response.status(200).json(user);
		} catch (err: any) {
			return response
				.status(400)
				.json({ error: err.message || 'Unexpected error' });
		}
	}
}
