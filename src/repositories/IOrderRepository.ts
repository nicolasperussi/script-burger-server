import { Order } from '@entities/Order';

export interface IOrderRepository {
	findAll(): Promise<Order[]>;
	save(order: Order): Promise<Order>;
	delete(id: string): Promise<void>;
	nextStep(
		id: string,
		status: 'WAITING' | 'IN_PRODUCTION' | 'DONE'
	): Promise<void>;
}
