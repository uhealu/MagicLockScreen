# 快速构建 APK 指南

## 方法1：使用命令提示符（推荐）

1. **打开命令提示符（CMD）**
   - 按 `Win + R`
   - 输入 `cmd`
   - 按回车

2. **进入项目目录**
   ```cmd
   cd /d D:\DATA\software\code\MagicLockScreen
   ```

3. **运行构建命令**
   ```cmd
   gradlew.bat assembleDebug
   ```

4. **等待完成**（首次约 5-10 分钟）

5. **找到 APK**
   ```
   app\build\outputs\apk\debug\app-debug.apk
   ```

---

## 方法2：使用文件资源管理器

1. **打开项目文件夹**
   ```
   D:\DATA\software\code\MagicLockScreen
   ```

2. **在地址栏输入 `cmd` 并回车**
   - 会自动在该目录打开命令提示符

3. **输入构建命令**
   ```cmd
   gradlew.bat assembleDebug
   ```

---

## 方法3：双击脚本

如果上面的方法遇到问题，尝试：

1. 在项目根目录，按住 `Shift`，右键空白处
2. 选择"在此处打开 PowerShell 窗口"或"在此处打开命令窗口"
3. 输入：
   ```
   .\gradlew.bat assembleDebug
   ```

---

## 首次构建会做什么？

1. 自动下载 Gradle 构建工具
2. 下载 Android SDK 和依赖库
3. 编译 Kotlin 代码
4. 打包生成 APK

**这需要良好的网络连接，预计 5-10 分钟。**

---

## 如果构建成功

你会看到：
```
BUILD SUCCESSFUL in XXs
```

APK 位置：
```
app\build\outputs\apk\debug\app-debug.apk
```

---

## 如果遇到错误

### 错误1：找不到 Java
**解决**：Gradle Wrapper 会自动下载 JDK，耐心等待

### 错误2：网络连接失败
**解决**：
- 检查网络连接
- 如果有代理，配置代理（项目已配置阿里云镜像）

### 错误3：空间不足
**需要约 2-3GB 空间用于依赖和构建**

---

## 构建后的操作

1. **传输到手机**
   - 使用数据线复制
   - 或通过微信/QQ 发送给自己

2. **安装**
   - 在手机上点击 APK
   - 允许"安装未知来源"
   - 完成安装

3. **使用**
   - 打开"魔术锁屏"应用
   - 开始魔术表演！

---

**推荐使用方法1或方法2，在 Windows 命令提示符中构建！**
