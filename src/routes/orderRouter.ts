import authMiddleware from "@middlewares/auth.middleware";
import priceMiddleware from "@middlewares/price.middleware";
import { createOrderController } from "@useCases/Order/CreateOrder";
import { deleteOrderController } from "@useCases/Order/DeleteOrder";
import { findAllOrdersController } from "@useCases/Order/FindAllOrders";
import { updateOrderStatusController } from "@useCases/Order/UpdateOrderStatus";
import validateOrder from "@validators/order";
import { Router } from "express";
const router = Router();

router
  .route("/")
  .get((req, res) => findAllOrdersController.handle(res))
  .post(authMiddleware, priceMiddleware, validateOrder, (req, res) =>
    createOrderController.handle(req, res)
  );

router
  .route("/:id")
  .delete(authMiddleware, (req, res) => deleteOrderController.handle(req, res))
  .patch((req, res) => updateOrderStatusController.handle(req, res));

export default router;
