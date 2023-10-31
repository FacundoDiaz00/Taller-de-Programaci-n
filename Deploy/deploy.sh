#!/bin/bash

ignorar=""

echo "Algoritmo de liberacion"
echo "Grupo tprog16"

if [ -d "../Servidor Central" ] && [ -d "../ServidorWebDinamico" ] && [ -d "../ServidorMobile" ]; then

    if [ -f "servidor.jar" ]; then
        rm "servidor.jar"
    fi

    if [ -f "web.war" ]; then
        rm "web.war"
    fi

    if [ -f "movil.war" ]; then
        rm "movil.war"
    fi

    echo "Se encontraron las carpetas a hacer deploy con exito"
    echo "A continuacion se iniciara el proceso de deploy, se generaran en este mismo directorio los archivos servidor.jar, web.war y movil.war"
    echo "Toque enter para comenzar"
    read ignorar

    echo "----------------------------------------------------------"
    echo "---------------- Generar Servidor Central ----------------"
    echo "----------------------------------------------------------"
    cd "../Servidor Central"
    mvn clean
    mvn package
    cp "target/ServidorCentral-1.0-SNAPSHOT-jar-with-dependencies.jar" "../Deploy/servidor.jar"


    echo "---------------------------------------------------------"
    echo "--------------- Generar Servidor Dinamico ---------------"
    echo "---------------------------------------------------------"
    cd "../ServidorWebDinamico"
    mvn clean
    mvn package
    cp "target/ServidorWebDinamico-1.0-SNAPSHOT.war" "../Deploy/web.war"


    echo "---------------------------------------------------------"
    echo "--------------- Generar Servidor Movil ------------------"
    echo "---------------------------------------------------------"
    cd "../ServidorMobile"
    mvn clean
    mvn package
    cp "target/ServidorMobile-1.0-SNAPSHOT.war" "../Deploy/mobile.war"


    cd "../Deploy"
else
    echo "No se encontro algunas de las siguientes carpetas en el proyecto: ../Servidor Central, ../ServidorMobile o ../ServidorWebDinamico"
fi







