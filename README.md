# 魔术锁屏应用 (MagicLockScreen)

## 功能说明
这是一个魔术表演用的Android应用，可以：
1. 模拟小米手机锁屏界面
2. 接收6位数字密码输入（任何输入都显示"密码错误"）
3. 输入完成后点击屏幕进入伪桌面
4. 在6个app图标位置显示红色数字徽章（对应输入的6位密码）

## 如何构建APK

### 方法1：使用Android Studio（推荐）

1. **下载并安装Android Studio**
   - 访问：https://developer.android.com/studio
   - 下载最新版本并安装

2. **打开项目**
   - 启动Android Studio
   - 选择 "Open" 打开本文件夹

3. **等待同步**
   - Android Studio会自动下载所需依赖
   - 等待底部显示 "Gradle sync finished"

4. **构建APK**
   - 点击菜单：Build → Build Bundle(s) / APK(s) → Build APK(s)
   - 等待构建完成（首次需要5-10分钟）
   - 构建成功后会弹出通知，点击 "locate" 查看APK位置
   - APK通常在：`app/build/outputs/apk/debug/app-debug.apk`

5. **安装到手机**
   - 将APK传输到手机
   - 在手机上打开APK进行安装
   - 需要允许"未知来源"安装

### 方法2：命令行构建（需要JDK）

如果已安装JDK 17+：

```bash
# Windows
gradlew.bat assembleDebug

# Mac/Linux
./gradlew assembleDebug
```

构建完成后APK位于：`app/build/outputs/apk/debug/app-debug.apk`

## 使用说明

1. 在你的小米手机上安装此应用
2. 魔术表演时打开应用
3. 交给观众输入任意6位数字
4. 观众会看到"密码错误"提示
5. 输入完成后点击屏幕任意位置
6. 进入伪桌面，6个位置会显示红色数字徽章

## 显示位置

红色数字徽章会显示在以下6个位置（按输入顺序）：
1. 左上角 "系统应用" 文件夹
2. 中上 "影音视听" 文件夹
3. 中上 "智能家居" 文件夹
4. 左中 "Spend" 文件夹
5. 中间 "USEful" 文件夹
6. 右中 "ROOT" 文件夹

## 技术栈

- Kotlin
- Jetpack Compose
- Android SDK 26+ (Android 8.0+)
- 目标SDK: 34

## 注意事项

- 此应用仅供魔术表演使用
- 请勿用于任何非法或欺诈用途
- 在自己的设备上演示，不要误导他人

## 故障排除

### Android Studio构建失败
- 确保网络连接正常（需要下载依赖）
- 检查是否安装了Android SDK
- 在 Tools → SDK Manager 中确认已安装 Android 13.0 (API 33) 或更高

### APK无法安装
- 在手机设置中允许"未知来源"安装
- 确保Android版本 ≥ 8.0

### 徽章位置不准确
- 桌面图标布局与提供的截图不同会导致位置偏差
- 如需调整，修改 MainActivity.kt 中的 badgePositions 坐标

## 开发者
- AI辅助开发
- 基于用户提供的桌面截图定制
