# Usa la imagen base de OpenJDK 22
# Usa la imagen base de Oracle Linux 8
FROM oraclelinux:8
FROM openjdk:22

# Define el directorio de trabajo dentro del contenedor
WORKDIR /agileorganizer

# Expone el puerto en el que la aplicación escuchará
EXPOSE 8080
EXPOSE 9898

# Copia el archivo .jar al contenedor y lo renombra
COPY target/MyTodoList-0.0.1-SNAPSHOT.jar MyTodoList.jar
COPY Walet/Wallet_reacttodoa2fwz Wallet_reacttodoa2fwz

# Configura las variables de entorno
ENV db_url="jdbc:oracle:thin:@reacttodoa2fwz_tp?TNS_ADMIN=Wallet_reacttodoa2fwz"
ENV db_user="TODOUSER"
ENV dbpassword="None74108520"

# Inicia el contenedor en bash
CMD ["/bin/bash"]
ENTRYPOINT ["java","-jar","MyTodoList.jar"]
