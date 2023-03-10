import { prisma } from '@database/db';
import { Category } from '@entities/Category';
import { ICategoryRepository } from '@repositories/ICategoryRepository';

export class PostgresCategoryRepository implements ICategoryRepository {
	async findByName(name: string): Promise<Category | null> {
		const category = await prisma.category.findFirst({
			where: { name },
		});

		return category;
	}

	async save(category: Category): Promise<void> {
		await prisma.category.create({
			data: category,
		});
	}

	async delete(id: string): Promise<void> {
		await prisma.category.delete({ where: { id } });
	}
}
