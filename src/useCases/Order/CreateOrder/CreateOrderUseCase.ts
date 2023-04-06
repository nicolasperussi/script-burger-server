import { Order } from '@entities/Order';
import { IOrderRepository } from '@repositories/IOrderRepository';
import { ICreateOrderRequestDTO } from './CreateOrderDTO';

export class CreateOrderUseCase {
	constructor(private orderRepository: IOrderRepository) {}

	async execute(data: ICreateOrderRequestDTO) {
		const newOrder = new Order(data);

		return await this.orderRepository.save(newOrder);
	}
}
