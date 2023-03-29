import { NextFunction, Request, Response } from 'express';
import { z } from 'zod';

const dataSchema = z.object({
	name: z.string(),
	description: z.string(),
	overview: z.string(),
	price: z.number(),
	categoryId: z.string(),
});

function validateProduct(
	req: Request,
	res: Response,
	next: NextFunction
): void {
	try {
		dataSchema.parse(req.body);

		next();
	} catch (err: any) {
		res.status(400).json({
			err,
		});
	}
}

export default validateProduct;
