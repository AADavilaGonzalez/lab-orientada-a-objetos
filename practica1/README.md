# Proceso de Instalacion de un Entorno de Desarrollo de Java

1. Instalar el JDK(Java Development Kit):
El JDK es el conjunto de herramientas que ofrece Oracle para empezar a
desarrollar programas en Java. El proceso de instalacion difiere
dependiendo del sistema operativo que utilicemos esta guia muestra como
instalar un entorno de desarrolo para un sistema corriendo Fedora Linux

Para instalar el un JDK utilizando el gestor de paquetes corre el comando
*dnf search openjdk-devel*
y revisa las versiones disponibles al momento
Para instalar una version en particular ejecuta:
*sudo dnf install java-[version]-openjdk-devel*

Si deseas verificar tu instalacion en este punto, corre los siguientes
comandos en la terminal
1. Java Virtual Machine:
*java --version*
2. Java Compiler
*javac --version*

2. Configuracion del Entrono de Desarrollo
Para desarrollar projectos en java se recomienda tener algun entro de
desarrollo para facilitar el proceso. Algunas de las recomendaciones
mas comunes son: Eclipse, ItelliJ IDEA o Visual Studio Code
Cada entorno tiene pasos diferentes para configurarse. Debido a que no
me gusta utilizar entornos de desarrollo, esta guia recomendara varios
pluggins de neovim para trabajar con proyectos de Java facilmente.

**Se asume que ya sabes utizar alguno de los gestores de paquetes
populares para neovim, ej. Lazy.nvim**

* nvim-treesitter: https://github.com/nvim-treesitter/nvim-treesitter/tree/main
    Proporciona un syntax highlighting mas detallado que el por default
    instalar el paquete y realizar el siguiente commando en modo normal
    dentro del editor '*:TSInstall java*'

* nvim-lspconfig: https://github.com/neovim/nvim-lspconfig
  && jdtls: https://projects.eclipse.org/projects/eclipse.jdt.ls
    Proporciona una configuracion para integrar jdtls(Eclipse JDT Language Server)
    con el protocolo de Language Server de neovim. Es necesario instalar tanto el
    plugin nvim-lpsconfig como el servidor jdtls para que funcione correctamente,
    ya que son dos herramientas separadas y no una sola.
    Una vez instalados los dos, escribir lo siguiente dentro de algun archivo de
    configuracion .lua alcanzable por medio de init.lua 'vim.lsp.enable("jdtls")'


