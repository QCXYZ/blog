git log --oneline
git reset --hard <commit_SHA>

### 接口文档

#### 1. 博客列表接口

- **接口描述：** 获取博客列表信息
- **接口URL：** `/admin/getBlogList`
- **请求方式：** POST
- **请求ContentType：** application/json
- **请求参数：**

| 参数名      | 必选 | 类型     | 说明   |
|----------|----|--------|------|
| pagenum  | 是  | int    | 页码   |
| pagesize | 是  | int    | 每页数量 |
| typeId   | 否  | Long   | 类型ID |
| title    | 否  | String | 博客标题 |

- **请求示例：**

```json
{
  "pagenum": 1,
  "pagesize": 10,
  "typeId": 2,
  "title": "示例标题"
}
```

- **成功返回值：**

| 参数名     | 类型      | 说明     |
|---------|---------|--------|
| success | boolean | 操作是否成功 |
| code    | int     | 状态码    |
| message | String  | 返回信息   |
| data    | Object  | 返回数据   |

- **返回示例：**

```json
{
  "success": true,
  "code": 200,
  "message": "获取博客列表成功",
  "data": {
    ...
  }
  // 返回数据的具体内容
}
```

#### 2. 添加/更新博客接口

- **接口描述：** 添加或更新博客信息
- **接口URL：** `/admin/blogs`
- **请求方式：** POST
- **请求ContentType：** application/json
- **请求参数：**

| 参数名  | 必选 | 类型   | 说明   |
|------|----|------|------|
| blog | 是  | Blog | 博客对象 |

- **请求示例：**

```json
{
  "blog": {
    "id": 1,
    // 若无ID则为添加，有ID则为更新
    "title": "示例博客标题",
    ...
  }
}
```

- **成功返回值：**

| 参数名     | 类型      | 说明     |
|---------|---------|--------|
| success | boolean | 操作是否成功 |
| code    | int     | 状态码    |
| message | String  | 返回信息   |
| data    | Object  | 返回数据   |

- **返回示例：**

```json
{
  "success": true,
  "code": 200,
  "message": "操作成功",
  "data": null
  // 对于添加或更新操作，通常不需要返回数据
}
```

#### 3. 搜索博客接口

- **接口描述：** 根据关键词搜索博客
- **接口URL：** `/admin/search`
- **请求方式：** GET
- **请求参数：**

| 参数名   | 必选 | 类型     | 说明    |
|-------|----|--------|-------|
| query | 是  | String | 搜索关键词 |

- **请求示例：**

```
/admin/search?query=示例关键词
```

- **成功返回值：**

| 参数名     | 类型      | 说明     |
|---------|---------|--------|
| success | boolean | 操作是否成功 |
| code    | int     | 状态码    |
| message | String  | 返回信息   |
| data    | Object  | 返回数据   |

- **返回示例：**

```json
{
  "success": true,
  "code": 200,
  "message": "获取搜索博客成功",
  "data": {
    ...
  }
  // 返回数据的具体内容
}
```

#### 4. 删除博客接口

- **接口描述：** 根据博客ID删除博客
- **接口URL：** `/admin/blogs/{id}/delete`
- **请求方式：** GET
- **请求参数：**

| 参数名 | 必选 | 类型   | 说明   |
|-----|----|------|------|
| id  | 是  | Long | 博客ID |

- **请求示例：**

```
/admin/blogs/1/delete
```

- **成功返回值：**

| 参数名     | 类型      | 说明     |
|---------|---------|--------|
| success | boolean | 操作是否成功 |
| code    | int     | 状态码    |
| message | String  | 返回信息   |

- **返回示例：**

```json
{
  "success": true,
  "code": 200,
  "message": "博客删除成功"
}
```

### 注意事项

- 所有接口返回的`success`字段为`true`时表示操作成功，为`false`时表示操作失败。
- `code`字段表示操作的状态码，`200`表示成功，其他值表示具体的错误类型。
- 接口的`data`字段将包含具体的返回数据，其结构取决于具体的业务逻辑和需求。
- 请求参数中的`id`，`typeId`等字段应确保在调用前已经存在于数据库中，否则可能会导致操作失败。
- 对于`POST`请求，需要客户端设置`Content-Type`为`application/json`，并将请求体（body）中的数据序列化为JSON格式。

### 版本信息

- **文档版本：** 1.0.0
- **接口版本：** v1
- **最后更新：** 2024-04-09

### 联系信息

- **维护者：** 小语咒
- **联系邮箱：** 2339679990@qq.com
