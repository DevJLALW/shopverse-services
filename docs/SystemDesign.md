# Design E-Commerce platform
Online shopping allows user to buy products

## Functional Requirements
- User should be able to search and find products based on names ot title
- User should be able to view the details of product(description, imagem available quantity, reviews)
- User shoudl be adle to select the quantity and move the item to the cart
-  User should be able to check the status of the order
- Manage purchase of items with limited stocks

## Non Functional Requirements:
- Scale: 10 Million monthly active users, 10  Orders/sec
- CAP Theorem: Highly available wrt searching and viewing and highly consistent wrt placing order and payment.
- latency: ~200ms

## Core entities
- User
- Product
- Cart
- Orders
- Checkout

## API Designing

- GET: /v1/product/search?q={searchTerm} -> List<ProductId (Partial)> list: Pagination
- GET: /v1/product/{productId} -> return the product details (json)
- POST: /v1/cart/add {post body: all product Id} -> return cartId
- POST: /v1/checkout {post body: all product Id with qty and price} -> return orderId
        /v1/payment {post body: OrderId & payment details} -> confirmation
- GET: /v1/status/{orderId}

## Diagrams

![HLD Diagram](HLD_ecommerce.drawio.svg)

![LLD Diagram](LLD_ecommerce.drawio.svg)



