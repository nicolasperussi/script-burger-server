import bcrypt from 'bcryptjs';

import { IUserRepository } from '@repositories/IUserRepository';
import { IAuthRequestDTO } from './AuthDTO';

export class AuthUseCase {
	constructor(private userRepository: IUserRepository) {}

	async execute(data: IAuthRequestDTO) {
		const user = await this.userRepository.login(data.email);

		if (!user) {
			throw new Error('Incorrect e-mail or password');
		}

		if (bcrypt.compareSync(data.password, user.password)) {
			return {
				id: user.id,
				name: user.name,
				email: user.email,
				addresses: user.addresses,
			};
		} else {
			throw new Error('Incorrect e-mail or password');
		}
	}
}
