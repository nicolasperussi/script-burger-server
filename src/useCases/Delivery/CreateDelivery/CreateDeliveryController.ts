import { Request, Response } from 'express';
import { CreateDeliveryUseCase } from './CreateDeliveryUseCase';

export class CreateDeliveryController {
	constructor(private createDeliveryUseCase: CreateDeliveryUseCase) {}

	async handle(request: Request, response: Response): Promise<Response> {
		try {
			const { status, totalPrice, productList, userId, addressId } =
				request.body;

			await this.createDeliveryUseCase.execute({
				status,
				totalPrice,
				productList,
				userId,
				addressId,
			});

			return response.status(201).send();
		} catch (err: any) {
			return response
				.status(400)
				.json({ error: err.message || 'Unexpected error' });
		}
	}
}
