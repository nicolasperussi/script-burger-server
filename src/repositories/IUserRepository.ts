import { User } from '@entities/User';

export interface IUserRepository {
	login(email: string): Promise<User | null>;
	register(user: User): Promise<void>;
	findByEmail(email: string): Promise<Omit<User, 'password'> | null>;
}
