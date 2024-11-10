### Spin up redis docker image
`docker run -d --name redis-stack-server -p 6379:6379 redis/redis-stack-server:latest`

### Inspect keys stored in redis
- `docker exec -it redis-stack-server redis-cli`  
- `KEYS *`