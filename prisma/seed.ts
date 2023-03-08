import { prisma } from '../src/database/db';

const bootstrap = async () => {
	// Purge all data from database
	await purge();

	// Creating three categories for the products
	await prisma.category.createMany({
		data: [{ name: 'drinks' }, { name: 'sandwiches' }, { name: 'desserts' }],
	});

	const categories = await prisma.category.findMany();

	// Adding products to database
	await prisma.product.create({
		data: {
			name: 'Full Steak',
			price: 39.9,
			description: 'Sanduiche com dois hamburgueres, alface, queijo e bacon',
			category: {
				connect: {
					id: categories[1].id,
				},
			},
		},
	});
	await prisma.product.create({
		data: {
			name: 'Coca-cola',
			price: 6.9,
			description: 'Refrigerante coca-cola',
			category: {
				connect: {
					id: categories[0].id,
				},
			},
		},
	});
	await prisma.product.create({
		data: {
			name: 'Pudim',
			price: 12.9,
			description: 'Sobremesa pudim de leite condensado',
			category: {
				connect: {
					id: categories[2].id,
				},
			},
		},
	});

	const products = await prisma.product.findMany({
		include: {
			category: true,
		},
	});

	const newOrder = await prisma.order.create({
		data: {
			productList: {
				create: [
					{
						product: {
							connect: { id: products[1].id },
						},
						quantity: 1,
					},
					{
						product: {
							connect: { id: products[0].id },
						},
						quantity: 2,
					},
				],
			},
			totalPrice: products[1].price * 1 + products[0].price * 2,
		},
		include: {
			productList: {
				select: {
					product: {
						select: {
							name: true,
							price: true,
						},
					},
					quantity: true,
				},
			},
		},
	});

	console.debug('CATEGORIES', categories);
	console.debug('PRODUCTS', products);
	console.debug('NEW ORDER', JSON.stringify(newOrder));
};

const purge = async () => {
	await prisma.orderProduct.deleteMany();
	await prisma.order.deleteMany();
	await prisma.category.deleteMany();
	await prisma.product.deleteMany();
};

bootstrap();
