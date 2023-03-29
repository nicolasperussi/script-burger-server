import { NextFunction, Request, Response } from 'express';
import { z } from 'zod';

const dataSchema = z.object({
	status: z
		.enum(['WAITING', 'IN_PRODUCTION', 'IN_TRANSIT', 'DELIVERED'])
		.default('WAITING'),
	totalPrice: z.number(),
	userId: z.string(),
	addressId: z.string(),
	productList: z
		.object({
			product: z.object({
				id: z.string(),
				name: z.string().optional(),
				price: z.number().optional(),
				slug: z.string().optional(),
			}),
			quantity: z.number(),
		})
		.array(),
});

function validateDelivery(
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

export default validateDelivery;
