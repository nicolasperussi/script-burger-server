import { prisma } from '@database/db';
import { Product } from '@entities/Product';
import { IProductRepository } from '@repositories/IProductRepository';

export class PostgresProductRepository implements IProductRepository {
	async findAll(): Promise<Product[]> {
		const products = await prisma.product.findMany({
			include: {
				category: true,
			},
		});

		return products;
	}

	async findByCategory(categoryId: string): Promise<Product | null> {
		throw new Error('Method not yet implemented');
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
		throw new Error('Method not yet implemented');
	}
}
