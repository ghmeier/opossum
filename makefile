all : build run

build :
	mvn package

run :
	rm output.txt
	java -jar target/opossum-0.0.1.jar server > output.txt &