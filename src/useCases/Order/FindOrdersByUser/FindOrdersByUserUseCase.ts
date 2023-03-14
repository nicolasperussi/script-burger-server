import { IOrderRepository } from '@repositories/IOrderRepository';
import { IFindOrdersByUserRequestDTO } from './FindOrdersByUserDTO';

export class FindOrdersByUserUseCase {
	constructor(private orderRepository: IOrderRepository) {}

	async execute(data: IFindOrdersByUserRequestDTO) {
		const orders = await this.orderRepository.findByUser(data.id);

		return orders;
	}
}
