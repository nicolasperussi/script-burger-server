import { Request, Response } from 'express';
import { CreateProductUseCase } from './CreateProductUseCase';

export class CreateProductController {
	constructor(private createProductUseCase: CreateProductUseCase) {}

	async handle(request: Request, response: Response): Promise<Response> {
		const { name, description, overview, price, categoryId } = request.body;

		try {
			await this.createProductUseCase.execute({
				name,
				description,
				overview,
				price,
				categoryId,
			});
			return response.status(201).send();
		} catch (err: any) {
			return response
				.status(400)
				.json({ message: err.message || 'Unexpected error' });
		}
	}
}
