import { Decimal } from "@prisma/client/runtime";
import { Request, Response, NextFunction } from "express";

function priceMiddleware(
  req: Request,
  res: Response,
  next: NextFunction
): void {
  const products = req.body.productList;

  const discount = req.body.discount;
  const deliveryFee = 6.9;
  const productTotal = products
    .map((product: any) => product.product.price * product.quantity)
    .reduce(
      (accumulator: any, currentValue: any) => accumulator + currentValue,
      0
    );

  req.body.totalPrice = Number(
    new Decimal(productTotal).plus(deliveryFee).minus(discount).toFixed(2)
  );

  req.body.discount = undefined;

  next();
}

export default priceMiddleware;
