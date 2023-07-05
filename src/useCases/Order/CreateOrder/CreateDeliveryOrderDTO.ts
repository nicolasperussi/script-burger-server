export interface ICreateDeliveryOrderRequestDTO {
  totalPrice: number;
  productList: {
    product: { id: string; name?: string; price?: number; slug?: string };
    quantity: number;
  }[];
  type: "DELIVERY";
  user: {
    id: string;
    name: string;
  };
  address: {
    id: string;
  };
}
