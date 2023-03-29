import { prisma } from '@database/db';
import { Delivery } from '@entities/Delivery';
import { IDeliveryRepository } from '@repositories/IDeliveryRepository';

export class PostgresDeliveryRepository implements IDeliveryRepository {
	async findAll(): Promise<Delivery[]> {
		const deliveries = await prisma.delivery.findMany({
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
				address: true,
				user: {
					select: {
						id: true,
						name: true,
						email: true,
					},
				},
			},
		});

		return deliveries;
	}
	async findByUser(userId: string): Promise<Delivery[]> {
		throw new Error('Method not implemented.');
	}
	async save(delivery: Delivery): Promise<void> {
		const productListCreator = delivery.productList.map((product) => {
			return {
				product: {
					connect: { id: product.product.id },
				},
				quantity: product.quantity,
			};
		});

		await prisma.delivery.create({
			data: {
				totalPrice: delivery.totalPrice,
				address: {
					connect: {
						id: delivery.addressId,
					},
				},
				user: {
					connect: {
						id: delivery.userId,
					},
				},
				productList: {
					create: productListCreator,
				},
				status: delivery.status,
				createdAt: delivery.createdAt,
			},
		});
	}
	async delete(id: string): Promise<void> {
		throw new Error('Method not implemented.');
	}
}
