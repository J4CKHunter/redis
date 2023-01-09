
### Redis example

#### Redis was used for both storing(entity) and caching data

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