import { Request, Response } from 'express';
import { FindAllDeliveriesUseCase } from './FindAllDeliveriesUseCase';

export class FindAllDeliveriesController {
	constructor(private findAllDeliveriesUseCase: FindAllDeliveriesUseCase) {}

	async handle(response: Response): Promise<Response> {
		try {
			const deliveries = await this.findAllDeliveriesUseCase.execute();

			return response.status(200).json(deliveries);
		} catch (err: any) {
			return response
				.status(400)
				.json({ error: err.message || 'Unexpected error' });
		}
	}
}
