import { Category } from '@entities/Category';

export interface ICategoryRepository {
	findAll(): Promise<Category[]>;
	findByName(name: string): Promise<Category | null>;
	save(category: Category): Promise<void>;
	delete(id: string): Promise<void>;
	// TODO: create update method
}
