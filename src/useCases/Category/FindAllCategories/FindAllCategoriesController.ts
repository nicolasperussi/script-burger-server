import { Request, Response } from 'express';
import { FindAllCategoriesUseCase } from './FindAllCategoriesUseCase';

export class FindAllCategoriesController {
	constructor(private findAllCategoriesUseCase: FindAllCategoriesUseCase) {}

	async handle(response: Response): Promise<Response> {
		try {
			const categories = await this.findAllCategoriesUseCase.execute();

			return response.status(200).json(categories);
		} catch (err: any) {
			return response
				.status(400)
				.json({ error: err.message || 'Unexpected error' });
		}
	}
}
