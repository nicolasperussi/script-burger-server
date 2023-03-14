import { Request, Response } from 'express';
import { CreateOrderUseCase } from './CreateOrderUseCase';

export class CreateOrderController {
	constructor(private createOrderUseCase: CreateOrderUseCase) {}

	async handle(request: Request, response: Response): Promise<Response> {
		try {
			const { status, userId, totalPrice, productList } = request.body;

			await this.createOrderUseCase.execute({
				status: status || 'WAITING',
				userId,
				totalPrice,
				productList,
			});

			return response.status(201).send();
		} catch (err: any) {
			return response
				.status(400)
				.json({ error: err.message || 'Unexpected error' });
		}
	}
}
