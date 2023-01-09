
### Redis example

#### Redis was used for both storing(entity) and caching data

#### API endpoints

<pre>
    POST localhost:8080/api/v1/product
        - example request body : 
            {
                "name": "xbox series s",
                "price": "10000"
            }
    GET localhost:8080/api/v1/product/{productId}
    GET localhost:8080/api/v1/product
    PUT localhost:8080/api/v1/product/{productId}
    DELETE localhost:8080/api/v1/product/{productId}
</pre>

#### Cached method :
<pre>
- the cached data clear in every 5 calls of the API

- GET - localhost:8080/api/v1/product

- ProductController -> @GetMapping getAllProducts()
- ProductService    -> @Cacheable(cacheNames = "getAllProductsCache") getAllProducts()
</pre>

#### run redis instance without credentials:

```console
docker run --name redis -p 6379:6379 -d redis
```
#### you can inspect Redis with RedisInsight