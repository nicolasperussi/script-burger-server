/*
  Warnings:

  - You are about to drop the column `deliveryId` on the `OrderProduct` table. All the data in the column will be lost.
  - You are about to drop the `Delivery` table. If the table is not empty, all the data it contains will be lost.

*/
-- AlterEnum
ALTER TYPE "OrderStatus" ADD VALUE 'IN_TRANSIT';

-- DropForeignKey
ALTER TABLE "Delivery" DROP CONSTRAINT "Delivery_addressId_fkey";

-- DropForeignKey
ALTER TABLE "Delivery" DROP CONSTRAINT "Delivery_userId_fkey";

-- DropForeignKey
ALTER TABLE "OrderProduct" DROP CONSTRAINT "OrderProduct_deliveryId_fkey";

-- AlterTable
ALTER TABLE "Order" ADD COLUMN     "addressId" TEXT,
ADD COLUMN     "type" "OrderType" NOT NULL DEFAULT 'DINE_IN',
ADD COLUMN     "userId" TEXT;

-- AlterTable
ALTER TABLE "OrderProduct" DROP COLUMN "deliveryId";

-- DropTable
DROP TABLE "Delivery";

-- DropEnum
DROP TYPE "DeliveryStatus";

-- AddForeignKey
ALTER TABLE "Order" ADD CONSTRAINT "Order_userId_fkey" FOREIGN KEY ("userId") REFERENCES "User"("id") ON DELETE SET NULL ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Order" ADD CONSTRAINT "Order_addressId_fkey" FOREIGN KEY ("addressId") REFERENCES "Address"("id") ON DELETE SET NULL ON UPDATE CASCADE;
