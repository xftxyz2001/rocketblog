@echo on
call npm run build
xcopy /e /y dist\ ..\rocketblog\src\main\resources\static
cd ..\rocketblog
./mvnw clean package -DskipTests
