mvn clean package assembly:single
mv target/editor-jar-with-dependencies.jar target/editor.jar
jpackage --type app-image -i target/ -n Editor --main-jar editor.jar --runtime-image runtime
rm -rf runtime
