/*
  Warnings:

  - Added the required column `overview` to the `Product` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE "Product" ADD COLUMN     "overview" TEXT NOT NULL;
