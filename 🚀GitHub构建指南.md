# 通过 GitHub Actions 构建 APK 完整指南

## ✅ 本地仓库已准备好

项目已经初始化为 Git 仓库，所有文件已提交。现在只需推送到 GitHub。

---

## 📋 操作步骤

### 第一步：在 GitHub 上创建仓库

1. **登录 GitHub**
   - 访问 https://github.com
   - 登录你的账号

2. **创建新仓库**
   - 点击右上角的 **+** 号
   - 选择 **New repository**

3. **填写仓库信息**
   - **Repository name**: `MagicLockScreen` （或其他你喜欢的名字）
   - **Description**: `魔术锁屏应用 - 一个有趣的魔术道具应用`
   - **Privacy**: 
     - ✅ **Public** - 推荐（免费使用 GitHub Actions）
     - ⚠️ Private - 也可以，但 Actions 有时长限制
   - **⚠️ 重要**: 
     - ❌ **不要勾选** "Add a README file"
     - ❌ **不要勾选** "Add .gitignore"
     - ❌ **不要勾选** "Choose a license"
     （因为我们已经有这些文件了）

4. **点击 "Create repository"**

---

### 第二步：推送代码到 GitHub

创建仓库后，GitHub 会显示一个页面，上面有推送命令。

**在你的项目目录执行：**（我已经帮你准备好了）

```bash
# 1. 添加远程仓库（替换成你的 GitHub 用户名）
git remote add origin https://github.com/你的用户名/MagicLockScreen.git

# 2. 重命名主分支为 main（GitHub 默认使用 main）
git branch -M main

# 3. 推送代码
git push -u origin main
```

**或者你可以直接复制以下命令，我帮你写好了：**

---

## 🚀 快速推送命令

请告诉我你的 GitHub 用户名，我会生成完整的推送命令。

**或者你可以手动执行：**

```bash
# 假设你的 GitHub 用户名是 username，仓库名是 MagicLockScreen
git remote add origin https://github.com/username/MagicLockScreen.git
git branch -M main
git push -u origin main
```

**首次推送时会要求你登录：**
- **方式1**：输入 GitHub 用户名和密码（现在需要用 Personal Access Token 代替密码）
- **方式2**：使用 SSH 密钥（更方便，但需要预先配置）

---

### 第三步：等待 GitHub Actions 自动构建

1. **推送成功后，访问你的仓库页面**
   ```
   https://github.com/你的用户名/MagicLockScreen
   ```

2. **点击顶部的 "Actions" 标签**
   - 你会看到一个正在运行的工作流："Build Android APK"
   - 状态会显示橙色圆圈（正在运行）

3. **等待构建完成**（约 3-5 分钟）
   - 完成后会显示绿色勾号 ✅
   - 失败会显示红色叉号 ❌（如果失败，告诉我错误信息）

---

### 第四步：下载 APK

#### 方法1：从 Actions 下载（每次推送）

1. 点击 **Actions** 标签
2. 点击最新的工作流运行（绿色勾号的那个）
3. 滚动到页面底部，找到 **Artifacts** 部分
4. 点击 **magic-lockscreen-apk** 下载
5. 解压 ZIP 文件，里面就是 `app-debug.apk`

#### 方法2：从 Releases 下载（自动发布）

1. 仓库首页右侧，点击 **Releases**
2. 会看到自动创建的 Release（版本号自动递增）
3. 直接下载 `app-debug.apk`（无需解压）

**推荐使用方法2**，因为：
- 直接就是 APK 文件，不需要解压
- 有版本记录，方便管理
- 可以写版本说明

---

## 🔄 后续更新流程

如果你修改了代码（比如调整徽章位置、更换桌面图片），只需：

```bash
# 1. 查看修改
git status

# 2. 添加修改的文件
git add .

# 3. 提交
git commit -m "更新：调整徽章位置

Co-Authored-By: Claude Opus 5.5 <noreply@anthropic.com>"

# 4. 推送
git push
```

推送后，GitHub Actions 会自动构建新的 APK！

---

## 🔐 GitHub 身份验证

### 如果推送时要求密码（推荐方式）

GitHub 现在不允许用密码推送，需要使用 **Personal Access Token**：

1. **创建 Token**
   - 访问 https://github.com/settings/tokens
   - 点击 **Generate new token** → **Generate new token (classic)**
   - 勾选 **repo** 权限（全部勾上）
   - 点击底部 **Generate token**
   - **复制 Token**（只显示一次！）

2. **推送时使用 Token**
   - 用户名：你的 GitHub 用户名
   - 密码：粘贴刚才复制的 Token

### 如果想避免每次输入（可选）

**方式1：使用 Git Credential Manager**（Windows 推荐）
```bash
git config --global credential.helper wincred
```
首次输入 Token 后会自动保存。

**方式2：使用 SSH**（更安全）
需要生成 SSH 密钥并添加到 GitHub，步骤较复杂。

---

## ⚙️ GitHub Actions 工作流说明

我已经配置好了 `.github/workflows/build-apk.yml`，它会：

### 触发时机
1. **推送到 main/master 分支时**
2. **创建 Pull Request 时**
3. **手动触发**（在 Actions 页面点击 "Run workflow"）

### 构建过程
1. 安装 JDK 17
2. 下载项目依赖
3. 编译 Kotlin 代码
4. 构建 Debug APK
5. 上传为 Artifact
6. 自动创建 Release（仅在推送到主分支时）

### 构建环境
- **操作系统**: Ubuntu Latest（Linux）
- **Java**: OpenJDK 17（Temurin）
- **Gradle**: 自动使用项目配置的版本

---

## 📱 安装到手机

APK 下载后：

### 小米手机安装步骤
1. 将 `app-debug.apk` 传输到手机
2. 点击 APK 文件
3. 如果提示"禁止安装未知应用"：
   - 点击"设置"
   - 允许"文件管理"或"浏览器"安装未知应用
4. 点击"安装"
5. 安装完成，点击"打开"

---

## 🛠️ 故障排查

### 问题1：推送失败 "Permission denied"
**原因**：身份验证失败
**解决**：使用 Personal Access Token 代替密码

### 问题2：Actions 构建失败
**查看错误日志**：
1. Actions → 点击失败的运行
2. 点击 "build" job
3. 展开失败的步骤
4. 复制错误信息告诉我

### 问题3：没有 Artifacts 下载选项
**原因**：构建失败或还在运行中
**解决**：等待构建完成（绿色勾号）

### 问题4：Actions 显示 "Workflows aren't being run on this forked repository"
**原因**：可能是 fork 的仓库，Actions 默认禁用
**解决**：Actions 页面点击 "I understand, enable them"

---

## 💰 费用说明

- **Public 仓库**：GitHub Actions **完全免费**，无限制
- **Private 仓库**：每月 2000 分钟免费，通常够用

本项目单次构建约 3-5 分钟。

---

## 📞 需要帮助？

告诉我你的 GitHub 用户名，我会生成完整的推送命令。

或者遇到任何错误，把错误信息发给我！

---

## ✨ 优势总结

相比本地构建：
- ✅ 不需要安装 Android Studio（节省 8GB+ 空间）
- ✅ 不需要下载 Android SDK（节省 3GB+ 空间）
- ✅ 构建在云端进行（不占用本地资源）
- ✅ 每次修改自动构建（推送即可）
- ✅ 版本管理（Release 自动标记版本）
- ✅ 随时随地下载（只要有网络）

**推送一次，等 5 分钟，APK 就好了！** 🚀
