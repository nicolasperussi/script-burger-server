import { Order } from "@entities/Order";

export interface IOrderRepository {
  findAll(): Promise<Order[]>;
  saveDineIn(order: Order): Promise<Order>;
  saveDelivery(order: Order): Promise<Order>;
  delete(id: string): Promise<void>;
  nextStep(
    id: string,
    status: "WAITING" | "IN_PRODUCTION" | "IN_TRANSIT" | "DONE"
  ): Promise<void>;
}
