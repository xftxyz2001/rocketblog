@echo on
cd vueblog
call npm run build
del /f /s /q ..\rocketblog\src\main\resources\static
xcopy /e /y dist\ ..\rocketblog\src\main\resources\static
cd ..\rocketblog
call ./mvnw clean package -DskipTests
scp .\target\rocketblog-0.0.1-SNAPSHOT.jar root@8.130.81.23:/root/
ssh -tt root@8.130.81.23 "./start.sh"
