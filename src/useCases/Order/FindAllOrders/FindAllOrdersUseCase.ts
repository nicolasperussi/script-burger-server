import { IOrderRepository } from '@repositories/IOrderRepository';

export class FindAllOrdersUseCase {
	constructor(private orderRepository: IOrderRepository) {}

	async execute() {
		const orders = await this.orderRepository.findAll();

		return orders;
	}
}
