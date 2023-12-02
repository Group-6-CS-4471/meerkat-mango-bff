# meerkat-mango-bff
Meerkat Mango's BFF (Backend for front end)

## Run in Debug Mode
```shell
./mvnw spring-boot:run  -Dspring-boot.run.profiles=$PROFILE -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=6060"
```

## API Docs

### Cart

curl --location 'http://localhost:50003/cart/534928f2-50e3-4c4d-bddc-206ffa654ffd/tes-user-1' <-- get a user's cart
- general format: http://localhost:50003/cart/{cartId}/{userId}
```
curl --location --request DELETE 'http://localhost:8090/cart/534928f2-50e3-4c4d-bddc-206ffa654ffd/tes-user-1' \
--header 'Content-Type: application/json' \
--data '{
    "productId": "154968876a45c0e99d35b5d57290d0b3",
    "provider": "Walmart",
    "amount": 3
}'
``` 
^ delete item from cart; general format http://localhost:50003/cart/{cartId}/{userId}
```
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

```
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

#### Example Response

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
curl --location 'http://localhost:8090/search?keyword=keyword' 
```
```json

```