all: 
	make compile
	make test
	make run
compile:
	find ./ -type f -name "*.java" > compfiles.txt ; javac -d bin -cp lib/junit-platform-console-standalone-1.5.2.jar @compfiles.txt
run:
	java -cp bin negocios.Principal
test:
	java -jar lib/junit-platform-console-standalone-1.5.2.jar --class-path bin --scan-class-path
clean:
	rm -r ./bin/*
