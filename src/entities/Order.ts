import { randomUUID } from 'crypto';
import { User } from './User';

type OrderStatus = 'WAITING' | 'IN_PRODUCTION' | 'IN_TRANSIT' | 'DELIVERED';

export class Order {
	public readonly id: string;
	public status: OrderStatus;
	public createdAt: Date;
	public totalPrice: number;
	public userId: string;
	public address: {
		id?: string;
		cep?: string;
		street?: string;
		number?: string;
	};
	public user?: {
		id: string;
		name: string;
	};
	public productList: {
		product: { id: string; name?: string; price?: number };
		quantity: number;
	}[];
	constructor(
		props: Omit<Order, 'id' | 'createdAt'>,
		id?: string,
		createdAt?: Date
	) {
		this.id = id || randomUUID();
		this.status = props.status;
		this.totalPrice = props.totalPrice;
		this.address = props.address;
		this.createdAt = createdAt || new Date();
		this.userId = props.userId;
		this.productList = props.productList;
	}
}
