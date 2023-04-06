import { prisma } from '@database/db';
import { Order } from '@entities/Order';
import { IOrderRepository } from '@repositories/IOrderRepository';

export class PostgresOrderRepository implements IOrderRepository {
	async findAll(): Promise<Order[]> {
		const orders = await prisma.order.findMany({
			include: {
				productList: {
					select: {
						product: {
							select: {
								id: true,
								name: true,
								price: true,
								slug: true,
							},
						},
						quantity: true,
					},
				},
			},
		});

		return orders;
	}

	async save(order: Order): Promise<Order> {
		const productListCreator = order.productList.map((product) => {
			return {
				product: {
					connect: { id: product.product.id },
				},
				quantity: product.quantity,
			};
		});

		const newOrder = await prisma.order.create({
			data: {
				client: order.client,
				totalPrice: order.totalPrice,
				createdAt: order.createdAt,
				status: order.status,
				productList: {
					create: productListCreator,
				},
			},
			include: {
				productList: {
					select: {
						product: {
							select: {
								id: true,
								name: true,
								price: true,
								slug: true,
							},
						},
						quantity: true,
					},
				},
			},
		});

		return newOrder;
	}

	async delete(id: string): Promise<void> {
		await prisma.order.delete({ where: { id } });
	}

	async nextStep(
		id: string,
		status: 'WAITING' | 'IN_PRODUCTION' | 'DONE'
	): Promise<void> {
		await prisma.order.update({ where: { id }, data: { status } });
	}
}
