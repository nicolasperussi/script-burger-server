import { Request, Response } from 'express';
import { UpdateOrderStatusUseCase } from './UpdateOrderStatusUseCase';

export class UpdateOrderStatusController {
	constructor(private updateOrderStatusUseCase: UpdateOrderStatusUseCase) {}

	async handle(request: Request, response: Response): Promise<Response> {
		const id = request.params.id;
		const { status } = request.body;
		try {
			await this.updateOrderStatusUseCase.execute({ id, status });

			return response.status(200).send();
		} catch (err: any) {
			return response
				.status(400)
				.json({ error: err.message || 'Unexpected error' });
		}
	}
}
