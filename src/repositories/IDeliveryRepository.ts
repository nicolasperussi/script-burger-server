import { Delivery } from '@entities/Delivery';

export interface IDeliveryRepository {
	findAll(): Promise<Delivery[]>;
	findByUser(userId: string): Promise<Delivery[]>;
	save(delivery: Delivery): Promise<void>;
	delete(id: string): Promise<void>;
}
