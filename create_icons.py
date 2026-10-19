from PIL import Image, ImageDraw

# 创建不同尺寸的图标
sizes = {
    'mdpi': 48,
    'hdpi': 72,
    'xhdpi': 96,
    'xxhdpi': 144,
    'xxxhdpi': 192
}

for density, size in sizes.items():
    # 创建黑色背景的圆形图标
    img = Image.new('RGBA', (size, size), (0, 0, 0, 255))
    draw = ImageDraw.Draw(img)
    
    # 画白色圆圈
    margin = size // 8
    draw.ellipse([margin, margin, size-margin, size-margin], 
                 fill=None, outline=(255, 255, 255, 255), width=size//16)
    
    # 保存launcher图标
    img.save(f'app/src/main/res/mipmap-{density}/ic_launcher.png')
    img.save(f'app/src/main/res/mipmap-{density}/ic_launcher_round.png')

print("Icons created successfully")
