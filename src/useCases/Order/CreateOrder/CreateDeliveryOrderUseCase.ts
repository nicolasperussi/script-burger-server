import { Order } from "@entities/Order";
import { IOrderRepository } from "@repositories/IOrderRepository";
import { ICreateDeliveryOrderRequestDTO } from "./CreateDeliveryOrderDTO";

export class CreateDeliveryOrderUseCase {
  constructor(private orderRepository: IOrderRepository) {}

  async execute(data: ICreateDeliveryOrderRequestDTO) {
    const newOrder = new Order(data);

    return await this.orderRepository.saveDelivery(newOrder);
  }
}
