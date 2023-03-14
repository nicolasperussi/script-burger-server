import { Request, Response } from 'express';
import { DeleteOrderUseCase } from './DeleteOrderUseCase';

export class DeleteOrderController {
	constructor(private deleteOrderUseCase: DeleteOrderUseCase) {}

	async handle(request: Request, response: Response): Promise<Response> {
		const id = await request.params.id;

		try {
			await this.deleteOrderUseCase.execute({ id });

			return response.status(200).send();
		} catch (err: any) {
			return response
				.status(400)
				.json({ error: err.message || 'Unexpected error' });
		}
	}
}
