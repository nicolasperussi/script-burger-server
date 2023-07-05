export interface ICreateDineInOrderRequestDTO {
  totalPrice: number;
  productList: {
    product: { id: string; name?: string; price?: number; slug?: string };
    quantity: number;
  }[];
  client: string;
  type: "DINE_IN";
}
