import { Delivery } from '@entities/Delivery';
import { IDeliveryRepository } from '@repositories/IDeliveryRepository';
import { ICreateDeliveryRequestDTO } from './CreateDeliveryDTO';

export class CreateDeliveryUseCase {
	constructor(private deliveryRepository: IDeliveryRepository) {}

	async execute(data: ICreateDeliveryRequestDTO) {
		const newDelivery = new Delivery(data);

		await this.deliveryRepository.save(newDelivery);
	}
}
