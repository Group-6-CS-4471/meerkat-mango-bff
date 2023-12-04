# meerkat-mango-bff
Meerkat Mango's BFF (Backend for front end)

## Run in Debug Mode
```shell
./mvnw spring-boot:run  -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=6060"
```

## API Docs

### Cart

```shell
curl --location 'http://localhost:50003/cart/534928f2-50e3-4c4d-bddc-206ffa654ffd/tes-user-1'
```
^ get a user's cart
- general format: http://localhost:50003/cart/{cartId}/{userId}
```shell
curl --location --request DELETE 'http://localhost:50003/cart/534928f2-50e3-4c4d-bddc-206ffa654ffd/tes-user-1' \
--header 'Content-Type: application/json' \
--data '{
    "productId": "154968876a45c0e99d35b5d57290d0b3",
    "provider": "Walmart",
    "amount": 3
}'
``` 
^ delete item from cart; general format http://localhost:50003/cart/{cartId}/{userId}
```shell
curl --location 'http://localhost:50003/cart' \
--header 'Content-Type: application/json' \
--data '{
"cartId": "534928f2-50e3-4c4d-bddc-206ffa654ffd",
"userId": "tes-user-1",
"products": [
{
"productId": "154968876a45c0e99d35b5d57290d0b3",
"provider": "Walmart",
"amount": 2
}
]
}'
```
^ add item to cart; general format: http://localhost:50003/cart 

**NOTE** To add to / create a new cart, provide at least a `userId` and at least an empty list of `products`

```shell
curl --location --request PUT 'http://localhost:50003/cart/534928f2-50e3-4c4d-bddc-206ffa654ffd/tes-user-1' \
--header 'Content-Type: application/json' \
--data '{
"productId": "154968876a45c0e99d35b5d57290d0b3",
"provider": "Walmart",
"amount": 3
}'
```
^ modify item in cart (i.e. change the amount); general format http://localhost:50003/cart/{cartId}/{userId}

**NOTE** with delete and modification, there is no response, since just static changes on the FE should be needed

### Example Response

Add to and get Cart:
```json
{
    "cartId": "534928f2-50e3-4c4d-bddc-206ffa654ffd",
    "userId": "tes-user-1",
    "products": [
        {
            "provider": "Amazon",
            "amount": 2,
            "productId": null
        }
    ]
}
```

## Search
```shell
curl --location 'http://localhost:50003/search?keyword=keyword' 
```

### Example Response
```json
[
    {
        "productId": "0973b37acd0c664e3de26e97e5571454",
        "name": "Alisha Solid Men's Feminine Cycling Shorts",
        "provider": "Amazon",
        "currentPrice": 6.99,
        "normalPrice": 6.99,
        "imageUrl": "http://img5a.flixcart.com/image/short/6/2/h/altght-11-alisha-38-original-imaeh2d5uq9thnyg.jpeg"
    },
    {
        "productId": "e9eb010ed8313f65f0e049fc3fcffe87",
        "name": "Ethical Rapter BNC-179 BNC Wire Connector (Silver)",
        "provider": "Walmart",
        "currentPrice": 22.99,
        "normalPrice": 22.99,
        "imageUrl": "http://img6a.flixcart.com/image/wire-joint-connector/n/r/f/bnc-057-rapter-100-1100x1100-imaehffvdkwk4jzh.jpeg"
    },
    {
        "productId": "f3ff4d066fa5290970f46762caa5c73a",
        "name": "Painted Latias Women's Anarkali Kurta",
        "provider": "Walmart",
        "currentPrice": 18.49,
        "normalPrice": 18.49,
        "imageUrl": "http://img6a.flixcart.com/image/kurta/q/d/m/2422-beige-libas-xxl-original-imaegfjeybmdzcfy.jpeg"
    },
    {
        "productId": "f80ba21b9e77fe3a4d0e944f86305f8d",
        "name": "Reckler Jeans",
        "provider": "Walmart",
        "currentPrice": 53.98,
        "normalPrice": 53.98,
        "imageUrl": "http://img6a.flixcart.com/image/jean/q/c/q/rac-comboof2-8-reckler-32-original-imaecjywwb8afbzy.jpeg"
    },
    {
        "productId": "fabe2ce4a6b7510fa1bac6922156ab7b",
        "name": "Blue Kurta designed by Roland",
        "provider": "Walmart",
        "currentPrice": 9.99,
        "normalPrice": 9.99,
        "imageUrl": "http://img6a.flixcart.com/image/kurta/q/h/z/m003pcotgreflo-masara-xl-original-imaeheqbwjhesjwq.jpeg"
    }
]
```

## Reviews

```shell
curl --location 'http://localhost:50003/reviews/534928f2-50e3-4c4d-bddc-206ffa654ffd/Amazon'
```

### Example Response
```json
{
  "productId": "154968876a45c0e99d35b5d57290d0b3",
  "provider": "Amazon",
  "reviews": [
    {
      "name": "John Smith",
      "review": "This is pretty good!"
    }
  ]
}
```
