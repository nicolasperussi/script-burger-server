import { Order } from '@entities/Order';

export interface IOrderRepository {
	findAll(): Promise<Order[]>;
	findByUser(userId: string): Promise<Order[] | null>;
	save(order: Order): Promise<void>;
	delete(id: string): Promise<void>;
}
