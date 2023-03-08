import { Request, Response } from 'express';
import { prisma } from '@database/db';

// Create category
export const createCategory = async (req: Request, res: Response) => {
	try {
		const { name } = req.body;

		const category = await prisma.category.create({
			data: { name },
		});

		res.status(201).json(category);
	} catch (error) {
		res.status(500).json({ error });
	}
};

// Get all categories
export const getAllCategories = async (req: Request, res: Response) => {
	try {
		const categories = await prisma.category.findMany();

		res.status(200).json(categories);
	} catch (error) {
		res.status(500).json({ error });
	}
};

// Get category by id
export const getCategoryById = async (req: Request, res: Response) => {
	try {
		const id = req.params.id;

		const category = await prisma.category.findUnique({
			where: { id },
		});

		res.status(200).json(category);
	} catch (error) {
		res.status(500).json({ error });
	}
};

// Get category by name
export const getCategoryByName = async (req: Request, res: Response) => {
	try {
		const name = req.params.name;

		const category = await prisma.category.findFirst({
			where: { name },
		});

		res.status(200).json(category);
	} catch (error) {
		res.status(500).json({ error });
	}
};

// Update category
export const updateCategory = async (req: Request, res: Response) => {
	try {
		const id = req.params.id;
		const { name } = req.body;

		await prisma.category.update({ where: { id }, data: { name } });

		res.status(200).json({ status: 'success' });
	} catch (error) {
		res.status(500).json({ error });
	}
};

// Delete category
export const deleteCategory = async (req: Request, res: Response) => {
	try {
		const id = req.params.id;

		await prisma.category.delete({ where: { id } });

		res.status(200).json({ status: 'success' });
	} catch (error) {
		res.status(500).json({ error });
	}
};
