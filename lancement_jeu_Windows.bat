javac --module-path .\openjfx\lib\ --add-modules javafx.controls -d bin .\src\*.java
java --module-path .\openjfx\lib\ --add-modules javafx.controls -cp bin Executable
