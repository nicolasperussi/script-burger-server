import { Order } from "@entities/Order";
import { IOrderRepository } from "@repositories/IOrderRepository";
import { ICreateDineInOrderRequestDTO } from "./CreateDineInOrderDTO";

export class CreateDineInOrderUseCase {
  constructor(private orderRepository: IOrderRepository) {}

  async execute(data: ICreateDineInOrderRequestDTO) {
    const newOrder = new Order(data);

    return await this.orderRepository.saveDineIn(newOrder);
  }
}
