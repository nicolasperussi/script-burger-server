import { Order } from '@entities/Order';

export interface IOrderRepository {
	findAll(): Promise<Order[]>;
	save(order: Order): Promise<void>;
	delete(id: string): Promise<void>;
}
