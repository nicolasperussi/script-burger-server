import bcrypt from 'bcryptjs';

import { User } from '@entities/User';
import { IUserRepository } from '@repositories/IUserRepository';
import { IRegisterRequestDTO } from './RegisterDTO';

export class RegisterUseCase {
	constructor(private userRepository: IUserRepository) {}

	async execute(user: IRegisterRequestDTO) {
		const userAlreadyExists = await this.userRepository.findByEmail(user.email);

		if (userAlreadyExists) {
			throw new Error('This e-mail is already in use.');
		}

		const newUser = new User(user);

		const hashPassword = await bcrypt.hash(user.password, 10);

		await this.userRepository.register({
			id: newUser.id,
			name: newUser.name,
			email: newUser.email,
			password: hashPassword,
			addresses: newUser.addresses,
		});
	}
}
