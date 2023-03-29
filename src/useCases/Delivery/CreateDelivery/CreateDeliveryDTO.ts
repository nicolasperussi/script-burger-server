type DeliveryStatus = 'WAITING' | 'IN_PRODUCTION' | 'IN_TRANSIT' | 'DELIVERED';

export interface ICreateDeliveryRequestDTO {
	status: DeliveryStatus;
	totalPrice: number;
	userId: string;
	addressId: string;
	productList: {
		product: { id: string; name?: string; price?: number; slug?: string };
		quantity: number;
	}[];
}
