import { randomUUID } from 'crypto';

type OrderStatus = 'WAITING' | 'IN_PRODUCTION' | 'IN_TRANSIT' | 'DELIVERED';

export class Delivery {
	public readonly id: string;
	public status: OrderStatus;
	public createdAt: Date;
	public totalPrice: number;
	public userId: string;
	public addressId: string;
	public user?: {
		id: string;
		name: string;
		email: string;
	};
	public address?: {
		id: string;
		cep: string;
		street: string;
		number: string;
	};
	public productList: {
		product: { id: string; name?: string; price?: number; slug?: string };
		quantity: number;
	}[];
	constructor(
		props: Omit<Delivery, 'id' | 'createdAt'>,
		id?: string,
		createdAt?: Date
	) {
		this.id = id || randomUUID();
		this.status = props.status;
		this.totalPrice = props.totalPrice;
		this.createdAt = createdAt || new Date();
		this.productList = props.productList;
		this.userId = props.userId;
		this.addressId = props.addressId;
	}
}
