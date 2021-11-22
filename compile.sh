#!/bin/env bash

mainmenu () {   
    echo "----------------------------------------------" 
    echo "-      Entrenamos.uy compiling utility       -" 
    echo "----------------------------------------------" 
    echo "- Please select an option                    -"
    echo "- 1. Compile All Components                  -"
    echo "- 2. Compile ServidorCentral                 -"
    echo "- 3. Compile ServidorWeb                     -"
    echo "- 4. Exit                                    -"
    echo "----------------------------------------------" 
    read -n 1 -p "Input Selection:" mainmenuinput
    if [ "$mainmenuinput" = "1" ]; then
        installAll
    elif [ "$mainmenuinput" = "2" ]; then
        installCentral
    elif [ "$mainmenuinput" = "3" ]; then
        installWeb
    elif [ "$mainmenuinput" = "4" ]; then
        exit
    else
        clear
        mainmenu
    fi
}

installWeb () {
    echo "Compiling ServidorWeb..."
    cd servidorWeb
    ant clean compile war
    cd ..
    cp servidorWeb/dist/ServidorWeb.war ./web.war
}

installCentral (){
    echo "Compiling ServidorCentral..."
    cd servidorCentral
    mvn clean install
    cd ..
    cp servidorCentral/target/servidorCentral-1.0-jar-with-dependencies.jar ./servidor.jar
    cp -r servidorCentral/assets ./assets
}


installAll () {
    installCentral
    installWeb
}

mainmenu
echo "Compilation successfull."
