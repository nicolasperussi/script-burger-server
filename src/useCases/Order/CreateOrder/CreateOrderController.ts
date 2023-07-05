import { Request, Response } from "express";
import { CreateDineInOrderUseCase } from "./CreateDineInOrderUseCase";
import { io } from "src";
import { CreateDeliveryOrderUseCase } from "./CreateDeliveryOrderUseCase";

export class CreateOrderController {
  constructor(
    private createDineInOrderUseCase: CreateDineInOrderUseCase,
    private createDeliveryOrderUseCase: CreateDeliveryOrderUseCase
  ) {}

  async handle(request: Request, response: Response): Promise<Response> {
    try {
      const orderProps = request.body;

      const order =
        orderProps.type === "DINE_IN"
          ? await this.createDineInOrderUseCase.execute(orderProps)
          : await this.createDeliveryOrderUseCase.execute(orderProps);

      io.emit("order@new", order);
      return response.status(201).send();
    } catch (err: any) {
      return response
        .status(400)
        .json({ error: err.message || "Unexpected error" });
    }
  }
}
