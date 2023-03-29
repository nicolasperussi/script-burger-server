type OrderStatus = 'WAITING' | 'IN_PRODUCTION' | 'DONE';

export interface ICreateOrderRequestDTO {
	status: OrderStatus;
	totalPrice: number;
	client: string;
	productList: {
		product: { id: string; name?: string; price?: number; slug?: string };
		quantity: number;
	}[];
}
