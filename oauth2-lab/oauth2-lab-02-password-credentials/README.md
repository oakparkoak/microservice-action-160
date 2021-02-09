### 密码模式: (resource owner password credentials)


#### 1. 获取 token

**Request:**

```text
curl -X POST -u client2:222 http://localhost:8080/oauth/token -H 'accept: application/json' -d 'grant_type=password&username=buser&password=bpwd&scope=read'
```

**Response:**

```json
{
  "access_token":"1d07c728-64d1-4bc4-9cb1-2ae59c4836d4",
  "token_type":"bearer",
  "expires_in":43199,
  "scope":"read"
}
```

#### 2. 访问 api

**Request:**

```text
curl -H 'authorization: Bearer 1d07c728-64d1-4bc4-9cb1-2ae59c4836d4' http://localhost:8080/api/u
```

**Response:**

```json
{
  "username":"buser",
  "email":"captain@gmail.com"
}
```