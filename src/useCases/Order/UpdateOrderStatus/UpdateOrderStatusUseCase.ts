import { IOrderRepository } from '@repositories/IOrderRepository';
import { IUpdateOrderStatusRequestDTO } from './UpdateOrderStatusDTO';

export class UpdateOrderStatusUseCase {
	constructor(private orderRepository: IOrderRepository) {}

	async execute(data: IUpdateOrderStatusRequestDTO) {
		await this.orderRepository.nextStep(data.id, data.status);
	}
}
