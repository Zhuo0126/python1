# 使用一個基礎的 Java 鏡像
FROM openjdk:17-alpine

# 設置工作目錄
WORKDIR /app

# 複製 JAR 文件的容器中
COPY line-01-uber.jar .

# 定義啟動命令
CMD ["java", "-jar", "line-01-uber.jar"]