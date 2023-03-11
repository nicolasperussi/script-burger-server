import { Request, Response } from 'express';
import { DeleteProductUseCase } from './DeleteProductUseCase';

export class DeleteProductController {
	constructor(private deleteProductUseCase: DeleteProductUseCase) {}

	async handle(request: Request, response: Response): Promise<Response> {
		const id = request.params.id;

		try {
			await this.deleteProductUseCase.execute({ id });

			return response.status(200).send();
		} catch (err: any) {
			return response
				.status(400)
				.json({ error: err.message || 'Unexpected error' });
		}
	}
}
