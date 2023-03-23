type OrderStatus = 'WAITING' | 'IN_PRODUCTION' | 'IN_TRANSIT' | 'DELIVERED';

export interface ICreateOrderRequestDTO {
	status: OrderStatus;
	totalPrice: number;
	userId: string;
	address: { id?: string; cep: string; street: string; number: string };
	productList: {
		product: { id: string };
		quantity: number;
	}[];
}
