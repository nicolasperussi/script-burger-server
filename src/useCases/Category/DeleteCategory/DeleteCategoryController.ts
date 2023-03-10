import { Request, Response } from 'express';
import { DeleteCategoryUseCase } from './DeleteCategoryUseCase';

export class DeleteCategoryController {
	constructor(private deleteCategoryUseCase: DeleteCategoryUseCase) {}

	async handle(request: Request, response: Response): Promise<Response> {
		const id = request.params.id;

		try {
			await this.deleteCategoryUseCase.execute({ id });
			return response.status(200).send();
		} catch (err: any) {
			return response
				.status(400)
				.json({ error: err.message || 'Unexpected error' });
		}
	}
}
