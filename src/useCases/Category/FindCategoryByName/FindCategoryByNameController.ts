import { Request, Response } from 'express';
import { FindCategoryByNameUseCase } from './FindCategoryByNameUseCase';

export class FindCategoryByNameController {
	constructor(private findCategoryByNameUseCase: FindCategoryByNameUseCase) {}

	async handle(request: Request, response: Response): Promise<Response> {
		const name = request.params.name;

		try {
			const category = await this.findCategoryByNameUseCase.execute({ name });
			return response.status(200).json(category);
		} catch (err: any) {
			return response
				.status(400)
				.json({ message: err.message || 'Unexpected error' });
		}
	}
}
