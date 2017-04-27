EGGJAR=./egg.jar
JAVAC=javac $(JOPTS)
JAVA=java
JAR=jar
JAVADOC=javadoc

#################

all : legg compile doc exec

doc :
	mkdir doc && \
	cd doc && \
	$(JAVADOC) ../fr/n7/stl/block/ast/*.java ../fr/n7/stl/block/ast/impl/*.java \
	../fr/n7/stl/tam/ast/*.java ../fr/n7/stl/tam/ast/impl/*.java \
	../fr/n7/stl/util/*.java

legg : 
	$(JAVA) -jar $(EGGJAR) MiniJava.egg

compile :
	cd egg && \
	$(JAVAC) -classpath ../$(EGGJAR):../.:. *.java&& \
	cd .. && \
	$(JAVAC) -classpath $(EGGJAR):. Main.java

exec :
    $(JAVA) -classpath $(EGGJAR):. Main tests/test_integer.bloc;

tests : clean legg compile
	for FICHIER in tests/test*.bloc; do\
		echo Working with $$FICHIER; \
		$(JAVA) -classpath $(EGGJAR):. Main $$FICHIER 2> /dev/null > $${FICHIER%%.*}.ast; \
	done

clean :
	-rm Main.class
	-rm -rf egg
	-rm -rf doc
	-rm -rf fr/n7/stl/block/ast/*.class fr/n7/stl/block/ast/impl/*.class
	-rm -rf fr/n7/stl/tam/ast/*.class fr/n7/stl/tam/ast/impl/*.class
	-rm -rf fr/n7/stl/util/*.class
	-rm -rf tests/*.tam
	-rm -rf tests/*.tamo
	-rm -rf tests/*.ast
	-rm -rf tests/*.res

debug : clean legg compile
	$(JAVA) -classpath $(EGGJAR):. Main tests/test_integer.bloc;
