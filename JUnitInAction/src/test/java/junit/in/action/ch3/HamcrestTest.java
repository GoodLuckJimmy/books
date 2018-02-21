package junit.in.action.ch3;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class HamcrestTest {
	private List<String> values;
	
	@Before
	public void setUList() {
		values = new ArrayList<>();
		values.add("x");
		values.add("y");
		values.add("z");
	}
	
//	@Test
//	public void testWithoutHamcrest() {
//		assertTrue(values.contains("one") 
//				|| values.contains("two")
//				|| values.contains("x"));
//	}
//	
//	@Test
//	public void testWithHamcrest() {
//		assertThat(values, hasItem(anyOf(equalTo("one"), equalTo("two"),
//				equals("three"))));
//	}

}
