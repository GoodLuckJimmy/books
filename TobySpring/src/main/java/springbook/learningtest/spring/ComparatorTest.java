package springbook.learningtest.spring;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

public class ComparatorTest {

	@Test
	public void comparator() {
		
		String str = "dBcAaEC";
		
		String sorted = sort(str);
		
		assertThat(sorted , is("aABcCdE"));
	}

	private String sort(String str) {
		
		char[] charArray = str.toCharArray();
		
		for (int i = 0; i < charArray.length; i ++) {
			compare(charArray);
		}

		System.out.println(charArray);

		return new String(charArray);
	}
	
	private void compare(char[] charArray) {
		for (int i = 0; i < charArray.length - 1; i++) {
			if (Character.toUpperCase(charArray[i]) > Character.toUpperCase(charArray[i + 1]) ) {
				char tmp = charArray[i];
				charArray[i] = charArray[i + 1];
				charArray[i + 1] = tmp;
				
			}
		}	
		
		for (int i = 0; i < charArray.length - 1; i++) {
			if (Character.toUpperCase(charArray[i]) == Character.toUpperCase(charArray[i + 1]) &&
					charArray[i] < charArray[i + 1]) {
				char tmp = charArray[i];
				charArray[i] = charArray[i + 1];
				charArray[i + 1] = tmp;
			}
		}	
	}
	
}
