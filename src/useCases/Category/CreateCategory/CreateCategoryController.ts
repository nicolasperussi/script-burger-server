import { Request, Response } from 'express';
import { CreateCategoryUseCase } from './CreateCategoryUseCase';

export class CreateCategoryController {
	constructor(private createCategoryUseCase: CreateCategoryUseCase) {}

	async handle(request: Request, response: Response): Promise<Response> {
		const { name } = request.body;

		try {
			await this.createCategoryUseCase.execute({ name });
			return response.status(201).send();
		} catch (err: any) {
			console.error(err);
			return response
				.status(400)
				.json({ message: err.message || 'Unexpected error' });
		}
	}
}
