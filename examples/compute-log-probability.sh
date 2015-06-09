#!/bin/bash

#first, build a hash-based language model binary from the file big_test.arpa
java -ea -mx1000m -server -cp ../target/berkeleylm-1.0-SNAPSHOT.jar edu.berkeley.nlp.lm.io.MakeLmBinaryFromArpa ../src/test/resources/edu/berkeley/nlp/lm/io/big_test.arpa ../target/big_test.binary

#now score a sample sentence
echo "This is a sample sentence ." | java -ea -mx1000m -server -cp ../target/berkeleylm-1.0-SNAPSHOT.jar edu.berkeley.nlp.lm.io.ComputeLogProbabilityOfTextStream  ../target/big_test.binary
