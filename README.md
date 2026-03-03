# LegoFlow


</div>

## 📻 项目简介

**LegoFlow**是一个基于讯飞 AstronAgent 二次开发的 AI工作流编排平台，前端使用react，后端使用jdk21、springboot、springAI，数据库使用mysql

### 技术特点

- ✅ 基于企业级开源项目 [AstronAgent](https://github.com/iflytek/astron-agent) 二次开发
- ✅ 微服务架构，支持高可用部署
- ✅ 完整的工作流可视化调试能力

## 🚀 快速开始

### 环境要求

- 8GB+ 可用内存
- 20GB+ 可用磁盘空间

### 访问应用

启动完成后，访问以下地址：

- **应用前端**：http://localhost
- **默认账户**：admin / 123

## 📖 使用指南

### 工作流配置示例

```
用户输入 → DeepSeek 改写 → 超拟人合成 → 输出音频
```

## 🛠️ 技术架构

### 核心服务

| 服务 | 说明 |
|------|------|
| console-hub | 控制台后端服务 |
| console-frontend | 前端界面 |
| core-workflow | 工作流引擎 |
| core-aitools | AI 工具服务（包含超拟人合成） |
| nginx | 反向代理 |
| mysql | MySQL 数据库 |
| redis | Redis 缓存 |
| minio | 对象存储 |

### 数据库说明

- **MySQL**：工具元数据、智能体配置
  - `astron_console.tool_box`：工具注册表
  - `spark-link.tools_schema`：工具 Schema 定义

## 🔧 常见问题

### 工作流执行失败？

检查以下配置：

1. **工具版本号**：确保 `tools_schema` 表中工具版本为 `V1.0`
2. **服务地址**：超拟人合成服务地址应为 `http://core-aitools:18668`
3. **app_id**：确保工具的 `app_id` 与工作流一致

## 🤝 贡献指南

本项目基于 [AstronAgent](https://github.com/iflytek/astron-agent) 二次开发。

如果您有任何建议或发现问题，欢迎提交 Issue 或 Pull Request。

## 📄 开源协议

本项目基于 Apache 2.0 协议开源，可自由商业使用。

## 🙏 致谢

- [讯飞 AstronAgent](https://github.com/iflytek/astron-agent) - 提供强大的智能体开发平台
- [讯飞星火](https://www.xfyun.cn) - 提供大模型和语音合成能力
- [DeepSeek](https://www.deepseek.com) - 提供高性能中文大模型

