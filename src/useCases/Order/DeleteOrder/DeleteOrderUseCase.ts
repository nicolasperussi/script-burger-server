import { IOrderRepository } from '@repositories/IOrderRepository';
import { IDeleteOrderRequestDTO } from './DeleteOrderDTO';

export class DeleteOrderUseCase {
	constructor(private orderRepository: IOrderRepository) {}

	async execute(data: IDeleteOrderRequestDTO) {
		await this.orderRepository.delete(data.id);
	}
}
