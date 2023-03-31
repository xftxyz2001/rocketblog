@echo on
cd vueblog
call npm run build
del /f /s /q ..\rocketblog\src\main\resources\static
xcopy /e /y dist\ ..\rocketblog\src\main\resources\static
cd ..\rocketblog
call ./mvnw clean package -DskipTests
call scp -i id_rsa .\target\rocketblog-0.0.1-SNAPSHOT.jar root@8.130.81.23:/root/
ssh -i id_rsa root@8.130.81.23 "./start.sh"
