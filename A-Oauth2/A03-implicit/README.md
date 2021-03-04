### 简化模式: (implicit)


#### 1. 获取 token

**Request: 浏览器请求 -> Security 认证 (cuser / cpwd) -> 页面授权**

```text
http://localhost:8080/oauth/authorize?client_id=client3&redirect_uri=http://localhost:8080/callback&response_type=token&scope=read
```

**Response:**

```text
http://localhost:8080/callback#access_token=746531a0-fbe3-4b52-bf7a-cdf85c55d79b&token_type=bearer&expires_in=119
```

#### 2. 访问 api

**Request:**

```text
curl -H 'authorization: Bearer 746531a0-fbe3-4b52-bf7a-cdf85c55d79b' http://localhost:8080/api/u
```

**Response:**

```json
{
  "username":"cuser",
  "email":"captain@gmail.com"
}
```