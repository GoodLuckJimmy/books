package spingbook.learningtest.template;

import java.io.*;

public interface BufferedReaderCallback {
	Integer doSomethingWithReader(BufferedReader br) throws IOException;
}
