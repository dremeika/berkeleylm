#!/bin/bash

#estimate a 5-gram kneser-ney language model from the raw text file "big_test.txt"

java -ea -mx1000m -server -cp ../target/berkeleylm-1.0-SNAPSHOT.jar edu.berkeley.nlp.lm.io.MakeKneserNeyArpaFromText 5 ../target/kneserNeyFromText.arpa ../src/test/resources/edu/berkeley/nlp/lm/io/big_test.txt
