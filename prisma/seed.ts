import { Order } from '@entities/Order';
import { slugify } from 'src/utils/slugify';
import { prisma } from '../src/database/db';
import mockProducts from './products';

const bootstrap = async () => {
	// Purge all data from database
	await purge();

	// Creating four categories for the products
	await prisma.category.createMany({
		data: [
			{ name: 'drink' },
			{ name: 'sandwich' },
			{ name: 'side' },
			{ name: 'dessert' },
		],
	});

	await createProducts(mockProducts);

	const john = await prisma.user.create({
		data: {
			name: 'John Doe',
			email: 'john.doe@gmail.com',
			password: 'password',
			addresses: {
				create: [
					{ cep: '03924-260', street: 'Rua dos Quichuas', number: '32' },
				],
			},
		},
	});
	const jane = await prisma.user.create({
		data: {
			name: 'Jane Doe',
			email: 'jane.doe@gmail.com',
			password: 'password',
			addresses: {
				create: [
					{ cep: '03924-250', street: 'Rua Primeiro Sorriso', number: '270' },
				],
			},
		},
	});
	const fulano = await prisma.user.create({
		data: {
			name: 'Fulano Detal',
			email: 'fulano.detal@gmail.com',
			password: 'password',
			addresses: {
				create: [{ cep: '03988-000', street: 'Av. Sapopemba', number: '8838' }],
			},
		},
	});
};

const purge = async () => {
	await prisma.orderProduct.deleteMany();
	await prisma.order.deleteMany();
	await prisma.user.deleteMany();
	await prisma.address.deleteMany();
	await prisma.category.deleteMany();
	await prisma.product.deleteMany();
};

const createProducts = async (products: any) => {
	products.forEach(
		async (product: {
			name: any;
			description: any;
			overview: any;
			price: any;
			category: any;
		}) => {
			await prisma.product.create({
				data: {
					name: product.name,
					description: product.description,
					slug: slugify(product.name),
					overview: product.overview,
					price: product.price,
					category: {
						connect: {
							name: product.category,
						},
					},
				},
			});
		}
	);
};

bootstrap();
