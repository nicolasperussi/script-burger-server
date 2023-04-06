import { Request, Response } from 'express';
import { CreateOrderUseCase } from './CreateOrderUseCase';
import { io } from 'src';

export class CreateOrderController {
	constructor(private createOrderUseCase: CreateOrderUseCase) {}

	async handle(request: Request, response: Response): Promise<Response> {
		try {
			const { status, client, totalPrice, productList } = request.body;

			const order = await this.createOrderUseCase.execute({
				status: status || 'WAITING',
				totalPrice,
				productList,
				client,
			});

			io.emit('order@new', order);
			return response.status(201).send();
		} catch (err: any) {
			return response
				.status(400)
				.json({ error: err.message || 'Unexpected error' });
		}
	}
}
