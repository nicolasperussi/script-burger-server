import { Category } from '@entities/Category';

export interface ICategoryRepository {
	findByName(name: string): Promise<Category | null>;
	save(category: Category): Promise<void>;
	delete(id: string): Promise<void>;
}
