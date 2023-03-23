import { prisma } from '@database/db';
import { Order } from '@entities/Order';
import { IOrderRepository } from '@repositories/IOrderRepository';

export class PostgresOrderRepository implements IOrderRepository {
	async findAll(): Promise<Order[]> {
		const orders = await prisma.order.findMany({
			include: {
				user: {
					select: {
						id: true,
						name: true,
					},
				},
				address: true,
				productList: {
					select: {
						product: {
							select: {
								id: true,
								name: true,
								price: true,
							},
						},
						quantity: true,
					},
				},
			},
		});

		return orders;
	}

	async findByUser(userId: string): Promise<Order[] | null> {
		throw new Error('Not yet implemented');
	}

	async save(order: Order): Promise<void> {
		const productListCreator = order.productList.map((product) => {
			return {
				product: {
					connect: { id: product.product.id },
				},
				quantity: product.quantity,
			};
		});

		await prisma.order.create({
			data: {
				user: {
					connect: {
						id: order.userId,
					},
				},
				address: {
					connect: {
						id: order.address.id,
					},
				},
				totalPrice: order.totalPrice,
				createdAt: order.createdAt,
				status: order.status,
				productList: {
					create: productListCreator,
				},
			},
		});
	}

	async delete(id: string): Promise<void> {
		await prisma.order.delete({ where: { id } });
	}
}
