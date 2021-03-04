### 授权码模式 - RestTemplate

#### 1. 启动 A05-authorization-code-server

#### 2. 启动 A05-authorization-code-client

#### 3. 请求 http://localhost:8081/index 登录-授权-回调-登录-获取数据

#### 4. client 会把 token 缓存到数据库, server 端认证是使用的内存模式; 所以, 重启server之后, token失效, 需要清空数据库, 重新生成token.
