import express from 'express';
import dotenv from 'dotenv';
import cors from 'cors';
import path from 'path';

import categoryRouter from '@routes/categoryRouter';
import productRouter from '@routes/productRouter';
import orderRouter from '@routes/orderRouter';
import userRouter from '@routes/userRouter';
import logMiddleware from '@middlewares/log.middleware';

const app = express();

dotenv.config();

app.use(express.json());
app.use('/images', express.static(path.resolve(__dirname, '..', 'images')));
app.use(cors());
app.use(logMiddleware);

app.use('/category', categoryRouter);
app.use('/product', productRouter);
app.use('/order', orderRouter);
app.use('/user', userRouter);

const PORT = process.env.PORT || 3002;

app.listen(PORT, () => {
	console.clear();
	// console.log(`🚀 Server running on port ${PORT}`);
	console.log(
		`${'🚀 \x1b[31m server.js \x1b[0m listening to port \x1b[33m'}${PORT}\x1b[0m`
	);
	console.log(`\x1b[96m http://localhost:${PORT}\x1b[0m`);
});

// TODO: implement user authentication, authorization and ordering, adding address to order
// TODO: create order middlewares
// TODO: implement adding images/icons to products and perhaps, categories
