import { Request, Response } from 'express';
import { FindProductsByCategoryUseCase } from './FindProductsByCategoryUseCase';

export class FindProductsByCategoryController {
	constructor(
		private findProductsByCategoryUseCase: FindProductsByCategoryUseCase
	) {}

	async handle(request: Request, response: Response): Promise<Response> {
		try {
			const categoryId = request.params.categoryId;

			const products = await this.findProductsByCategoryUseCase.execute({
				categoryId,
			});

			return response.status(200).json(products);
		} catch (err: any) {
			return response
				.status(400)
				.json({ error: err.message || 'Unexpected error' });
		}
	}
}
