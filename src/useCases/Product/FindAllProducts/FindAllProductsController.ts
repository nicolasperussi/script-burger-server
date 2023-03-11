import { Response } from 'express';
import { FindAllProductsUseCase } from './FindAllProductsUseCase';

export class FindAllProductsController {
	constructor(private findAllProductsUseCase: FindAllProductsUseCase) {}

	async handle(response: Response): Promise<Response> {
		try {
			const products = await this.findAllProductsUseCase.execute();

			return response.status(200).json(products);
		} catch (err: any) {
			return response
				.status(400)
				.json({ error: err.message || 'Unexpected error' });
		}
	}
}
