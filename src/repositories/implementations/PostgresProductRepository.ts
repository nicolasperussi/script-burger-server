import { prisma } from '@database/db';
import { Product } from '@entities/Product';
import { IProductRepository } from '@repositories/IProductRepository';

export class PostgresProductRepository implements IProductRepository {
	async findAll(): Promise<Product[]> {
		const products = await prisma.product.findMany({
			select: {
				id: true,
				name: true,
				description: true,
				price: true,
				category: true,
			},
		});

		return products;
	}

	async findById(id: string): Promise<Product | null> {
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

		return product;
	}

	async findByCategory(categoryId: string): Promise<Product[]> {
		const products = await prisma.product.findMany({
			where: { categoryId },
			select: {
				id: true,
				name: true,
				description: true,
				price: true,
			},
		});

		return products;
	}

	async save(product: Product): Promise<void> {
		await prisma.product.create({
			data: {
				id: product.id,
				name: product.name,
				description: product.description,
				price: product.price,
				category: {
					connect: { id: product.categoryId },
				},
			},
		});
	}

	async delete(id: string): Promise<void> {
		await prisma.product.delete({ where: { id } });
	}
}
