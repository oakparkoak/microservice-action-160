### 客户端模式: (client credentials)


#### 1. 获取 token

**Request:**

```text
curl -X POST -u client4:444 http://localhost:8080/oauth/token -d 'grant_type=client_credentials&username=duser&password=dpwd&scope=devops'
```

**Response:**

```json
{
  "access_token":"4c9f4165-71ae-48ba-bec9-17913c292a03",
  "token_type":"bearer",
  "expires_in":43199,
  "scope":"devops"
}
```

#### 2. 访问 api

**Request:**

```text
curl -H 'authorization: Bearer 4c9f4165-71ae-48ba-bec9-17913c292a03' http://localhost:8080/api/u
```

**Response:**

```json
{
  "username":"duser",
  "email":"captain@gmail.com"
}
```