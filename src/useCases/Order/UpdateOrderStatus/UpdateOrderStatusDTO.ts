export interface IUpdateOrderStatusRequestDTO {
	id: string;
	status: 'WAITING' | 'IN_PRODUCTION' | 'DONE';
}
