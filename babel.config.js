module.exports = {
	presets: [
		[
			'@babel/preset-env',
			{
				targets: {
					node: 'current',
				},
			},
		],
		'@babel/preset-typescript',
	],
	plugins: [
		[
			'module-resolver',
			{
				alias: {
					'@routes': './src/routes',
					'@database': './src/database',
					'@entities': './src/entities',
					'@middlewares': './src/middlewares',
					'@implementations': './src/repositories/implementations',
					'@repositories': './src/repositories',
					'@useCases': './src/useCases',
					'@validators': './src/validators',
				},
			},
		],
	],
	ignore: ['**/*.spec.ts'],
};
