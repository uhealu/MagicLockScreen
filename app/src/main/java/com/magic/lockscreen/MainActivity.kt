package com.magic.lockscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MagicLockScreenTheme {
                MagicLockScreenApp()
            }
        }
    }
}

@Composable
fun MagicLockScreenTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(),
        content = content
    )
}

@Composable
fun MagicLockScreenApp() {
    var currentScreen by remember { mutableStateOf("lockscreen") }
    var inputPassword by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    when (currentScreen) {
        "lockscreen" -> LockScreen(
            password = inputPassword,
            showError = showError,
            onPasswordChange = {
                inputPassword = it
                showError = false
            },
            onPasswordSubmit = {
                if (inputPassword.length == 6) {
                    showError = true
                }
            },
            onScreenClick = {
                if (inputPassword.length == 6) {
                    currentScreen = "desktop"
                }
            }
        )
        "desktop" -> DesktopScreen(
            password = inputPassword
        )
    }
}

@Composable
fun LockScreen(
    password: String,
    showError: Boolean,
    onPasswordChange: (String) -> Unit,
    onPasswordSubmit: () -> Unit,
    onScreenClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onScreenClick()
            }
    ) {
        // 时间显示
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val currentTime = remember {
                SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            }
            val currentDate = remember {
                SimpleDateFormat("M月d日 EEEE", Locale.CHINA).format(Date())
            }

            Text(
                text = currentTime,
                fontSize = 88.sp,
                fontWeight = FontWeight.Light,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = currentDate,
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
        }

        // 密码输入区域
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showError) {
                Text(
                    text = "密码错误",
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // 密码点
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                repeat(6) { index ->
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(
                                if (index < password.length) Color.White
                                else Color.White.copy(alpha = 0.3f)
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            // 数字键盘
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                for (row in 0..2) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(40.dp)
                    ) {
                        for (col in 1..3) {
                            val num = row * 3 + col
                            NumberKey(num.toString()) {
                                if (password.length < 6) {
                                    onPasswordChange(password + num)
                                    if (password.length == 5) {
                                        onPasswordSubmit()
                                    }
                                }
                            }
                        }
                    }
                }

                // 最后一行：空白、0、删除
                Row(
                    horizontalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    Spacer(modifier = Modifier.size(70.dp))
                    NumberKey("0") {
                        if (password.length < 6) {
                            onPasswordChange(password + "0")
                            if (password.length == 5) {
                                onPasswordSubmit()
                            }
                        }
                    }
                    NumberKey("←") {
                        if (password.isNotEmpty()) {
                            onPasswordChange(password.dropLast(1))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NumberKey(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(70.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.15f))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 28.sp,
            color = Color.White,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun DesktopScreen(password: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 桌面背景图
        Image(
            painter = painterResource(id = R.drawable.desktop),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 6个密码数字徽章位置（根据你的桌面截图）
        // 坐标是基于1280x2772的原始图片，对应app图标右上角
        val badgePositions = listOf(
            Pair(120f, 165f),  // 左上 "系统应用" 文件夹
            Pair(310f, 350f),  // 中上 "影音视听" 文件夹
            Pair(480f, 350f),  // 中上 "智能家居" 文件夹
            Pair(120f, 540f),  // 左中 "Spend" 文件夹
            Pair(420f, 585f),  // 中间 "USEful" 文件夹
            Pair(650f, 540f)   // 右中 "ROOT" 文件夹
        )

        password.forEachIndexed { index, digit ->
            if (index < badgePositions.size) {
                val (x, y) = badgePositions[index]
                PasswordBadge(
                    digit = digit.toString(),
                    x = x.dp,
                    y = y.dp
                )
            }
        }
    }
}

@Composable
fun PasswordBadge(digit: String, x: androidx.compose.ui.unit.Dp, y: androidx.compose.ui.unit.Dp) {
    Box(
        modifier = Modifier
            .offset(x = x, y = y)
            .size(24.dp)
            .clip(CircleShape)
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = digit,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

