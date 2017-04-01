package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class) // 파라미터화된 테스트 방식 이용
public class StandardIncomeTaxTest {
	@Parameters
	public static Collection data() { // 반드시 정적메소드이여야 하고, Collection을 리턴. 클래스 생성자의 파라미터와 숫자와 타입이 정확히 일치
		return Arrays.asList(new Object[][] {
			{ 0,2008, 0},
			{ 1000, 2008, 80},
			{ 1200,2008, 96},
			{ 1205,2008, 96},
			{ 1206,2008, 97},
			{4600, 2008, 647}
		});
	}
	
	private int income;
	private int taxAmount;
	private int year;
	
	// 테스트 클래스의 생성자인데, 맴버로 선언된 변수에 값이 할당된다. junit이 리플렉션을 이용해 배열에 담긴 값을 생성자 파라미터로 넘긴다
	public StandardIncomeTaxTest(int income, int year, int taxAmount) {
		this.income = income;
		this.year = year;
		this.taxAmount = taxAmount;
	}
	
	@Test
	public void testGetTaxAmount() {
		StandardTex incomeTax = new StandardTex();
		assertThat(this.taxAmount, is(incomeTax.getTaxAmount(this.income)));
	}

}
