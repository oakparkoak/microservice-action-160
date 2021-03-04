### 授权码模式: (authorization code)

#### 1. 获取授权码

**Request: 浏览器请求 -> Security 认证 (auser / apwd) -> 页面授权**

```text
http://localhost:8080/oauth/authorize?client_id=client1&redirect_uri=http://localhost:8080/callback&response_type=code&scope=read
```

**Response:**

```text
http://localhost:8080/callback?code=jcFSJn
```

#### 2. 获取 token

**Request:**

```text
curl -X POST -u client1:111 http://localhost:8080/oauth/token -d 'code=jcFSJn&grant_type=authorization_code&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fcallback&scope=read'
```

**Response:**

```json
{
  "access_token":"81bb2389-ce32-460b-bf6e-2faf048196af",
  "token_type":"bearer",
  "expires_in":42855,
  "scope":"read"
}
```

#### 3. 访问 api

**Request:**

```text
curl -H 'authorization: Bearer 81bb2389-ce32-460b-bf6e-2faf048196af' http://localhost:8080/api/u
```

**Response:**

```json
{
  "username":"auser",
  "email":"captain@gmail.com"
}
```