import { Request, Response } from 'express';
import { FindOrdersByUserUseCase } from './FindOrdersByUserUseCase';

export class FindOrdersByUserController {
	constructor(private findOrdersByUserUseCase: FindOrdersByUserUseCase) {}

	async handle(request: Request, response: Response): Promise<Response> {
		try {
			const userId = request.params.userId;

			const orders = await this.findOrdersByUserUseCase.execute({ id: userId });

			return response.status(200).json(orders);
		} catch (err: any) {
			return response
				.status(400)
				.json({ error: err.message || 'Unexpected error' });
		}
	}
}
