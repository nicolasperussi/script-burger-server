import { Request, Response } from 'express';
import { prisma } from '@database/db';

// Create product
export const createProduct = async (req: Request, res: Response) => {
	try {
		const { name, description, price, categoryId } = req.body;

		await prisma.product.create({
			data: {
				name,
				description,
				price,
				category: {
					connect: {
						id: categoryId,
					},
				},
			},
		});

		res.status(201).json({ status: 'success' });
	} catch (error) {
		res.status(500).json({ error });
	}
};

// Get all products
export const getAllProducts = async (req: Request, res: Response) => {
	try {
		const products = await prisma.product.findMany({
			select: {
				id: true,
				name: true,
				description: true,
				price: true,
				category: true,
			},
		});

		res.status(200).json(products);
	} catch (error) {
		res.status(500).json({ error });
	}
};

// Get product by category
export const getProductsByCategory = async (req: Request, res: Response) => {
	try {
		const category = req.params.category;

		const products = await prisma.product.findMany({
			where: { categoryId: category },
			select: {
				id: true,
				name: true,
				description: true,
				price: true,
			},
		});

		res.status(200).json(products);
	} catch (error) {
		res.status(500).json({ error });
	}
};

// Get product by id
export const getProductById = async (req: Request, res: Response) => {
	try {
		const id = req.params.id;

		const product = await prisma.product.findUnique({
			where: { id },
			select: {
				id: true,
				name: true,
				description: true,
				price: true,
				category: true,
			},
		});

		res.status(200).json(product);
	} catch (error) {
		res.status(500).json({ error });
	}
};

// Update product
export const updateProduct = async (req: Request, res: Response) => {
	try {
		const id = req.params.id;
		const data = req.body;

		await prisma.product.update({ where: { id }, data });

		res.status(200).json({ status: 'success' });
	} catch (error) {
		res.status(500).json({ error });
	}
};

// Delete product
export const deleteProduct = async (req: Request, res: Response) => {
	try {
		const id = req.params.id;

		await prisma.product.delete({ where: { id } });

		res.status(200).json({ status: 'success' });
	} catch (error) {
		res.status(500).json({ error });
	}
};
