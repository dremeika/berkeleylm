This is a fork of the original repo at http://berkeleylm.googlecode.com which is not actively maintained.

List of changes:

* Fixed probability scoring in ``ComputeLogProbabilityOfTextStream''.
* Computes **perplexity** instead of log probability sum.
* Added possibility to log per line probability and normalized probability for debug.

# Berkeley Language Model

To compile this software just type "mvn clean package" in the root directory (assuming Maven is installed and properly configured).

For examples of command-line usage of this software for manipulating language model files, see the examples/ directory.

Please see javadoc in edu.berkeley.nlp.lm.io.LmReaders file for documentation.

