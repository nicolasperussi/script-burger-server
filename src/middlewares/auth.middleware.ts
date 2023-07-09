import { Request, Response, NextFunction } from 'express';

import jwt from 'jsonwebtoken';

function authMiddleware(req: Request, res: Response, next: NextFunction) {
  const jwtKey = process.env.JWT_KEY as string;

  if (!req.headers.authorization)
    return res.status(401).send('Access denied. No token was provided.');

  const [parts, token] = req.headers.authorization!!.split(' ');

  if (parts !== 'Bearer')
    return res.status(401).send('Access denied. Token malformatted.');

  jwt.verify(token, jwtKey, (err) => {
    if (err) return res.status(401).send('Access denied. Invalid token.');
  });

  next();
}

export default authMiddleware;
