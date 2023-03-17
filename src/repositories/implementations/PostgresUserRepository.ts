import { prisma } from '@database/db';
import { User } from '@entities/User';
import { IUserRepository } from '@repositories/IUserRepository';

export class PostgresUserRepository implements IUserRepository {
	async login(email: string): Promise<User | null> {
		const user = prisma.user.findFirst({
			where: { email },
			include: {
				addresses: true,
			},
		});

		return user;
	}

	async register(user: User): Promise<void> {
		const addressesListCreator = user.addresses.map((address) => {
			return {
				cep: address.cep,
				street: address.street,
				number: address.number,
			};
		});

		console.log(addressesListCreator);

		await prisma.user.create({
			data: {
				id: user.id,
				name: user.name,
				email: user.email,
				password: user.password,
				addresses: {
					create: addressesListCreator,
				},
			},
		});
	}

	async findByEmail(email: string): Promise<Omit<User, 'password'> | null> {
		const user = prisma.user.findFirst({
			where: { email },
			select: {
				id: true,
				email: true,
				name: true,
				password: false,
				addresses: true,
			},
		});

		return user;
	}
}
