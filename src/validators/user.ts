import { NextFunction, Request, Response } from 'express';
import { z } from 'zod';

const dataSchema = z.object({
	name: z.string(),
});

function validateUser(req: Request, res: Response, next: NextFunction): void {
	try {
		// TODO: refactor user to add match passwords and validate it
		// dataSchema.parse(req.body);
		console.log('went through validation');

		next();
	} catch (err: any) {
		res.status(400).json({
			err,
		});
	}
}

export default validateUser;
