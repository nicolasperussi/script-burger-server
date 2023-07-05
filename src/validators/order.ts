import { NextFunction, Request, Response } from "express";
import { z } from "zod";

function validateOrder(req: Request, res: Response, next: NextFunction): void {
  try {
    const type = z.enum(["DINE_IN", "DELIVERY"]).parse(req.body.type);

    if (type === "DINE_IN") {
      validateDineIn(req.body);
    }

    if (type === "DELIVERY") {
      validateDelivery(req.body);
    }

    next();
  } catch (err: any) {
    res.status(400).json({
      err,
    });
  }
}

const dineInSchema = z.object({
  totalPrice: z.number(),
  productList: z
    .object({
      product: z.object({
        id: z.string(),
        name: z.string().optional(),
        price: z.number().optional(),
        slug: z.string().optional(),
      }),
      quantity: z.number(),
    })
    .array(),
  client: z.string(),
  type: z.string().includes("DINE_IN"),
});

const deliverySchema = z.object({
  totalPrice: z.number(),
  productList: z
    .object({
      product: z.object({
        id: z.string(),
        name: z.string().optional(),
        price: z.number().optional(),
        slug: z.string().optional(),
      }),
      quantity: z.number(),
    })
    .array(),
  type: z.string().includes("DELIVERY"),
  user: z.object({
    id: z.string(),
    name: z.string(),
  }),
  address: z.object({
    id: z.string(),
  }),
});

function validateDineIn(object: any) {
  dineInSchema.parse(object);
}

function validateDelivery(object: any) {
  deliverySchema.parse(object);
}

export default validateOrder;
