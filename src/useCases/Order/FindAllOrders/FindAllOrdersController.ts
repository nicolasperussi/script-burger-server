import { Response } from 'express';
import { FindAllOrdersUseCase } from './FindAllOrdersUseCase';

export class FindAllOrdersController {
	constructor(private findAllOrdersUseCase: FindAllOrdersUseCase) {}

	async handle(response: Response): Promise<Response> {
		try {
			const orders = await this.findAllOrdersUseCase.execute();

			return response.status(200).json(orders);
		} catch (err: any) {
			return response
				.status(400)
				.json({ error: err.message || 'Unexpected error' });
		}
	}
}
