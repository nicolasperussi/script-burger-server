import { IDeliveryRepository } from '@repositories/IDeliveryRepository';

export class FindAllDeliveriesUseCase {
	constructor(private deliveryRepository: IDeliveryRepository) {}

	async execute() {
		const deliveries = await this.deliveryRepository.findAll();

		return deliveries;
	}
}
