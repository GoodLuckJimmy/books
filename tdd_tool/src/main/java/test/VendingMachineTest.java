package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class VendingMachineTest {

	
	@Test
	public void testReturnChangeCoinSet_oneCoin_50() {
		ChangeModule module = new ChangeModule();
		CoinSet expectedCoinSet = new CoinSet();
		expectedCoinSet.add(50);
		assertThat("700원 투입후 650우너 음료선택", expectedCoinSet, is(module.getChangeCoinSet(50)));
	}
	
	@Test
	public void testReturnChangeCoinSet_oneCoin_180() {
		ChangeModule module = new ChangeModule();
		CoinSet expectedCoinSet = new CoinSet();
		expectedCoinSet.add(100);
		expectedCoinSet.add(50);
		expectedCoinSet.add(10);
		expectedCoinSet.add(10);
		expectedCoinSet.add(10);
		assertThat("700원 투입 후 520원 음료선택", expectedCoinSet, is(module.getChangeCoinSet(180)));
	}

//	@Test
//	public void testGetChangeAmount()  {
//		VendingMachine machine = new VendingMachine();
//		machine.putCoin(100);
//		assertThat("투입금액 100원", 100 , is(machine.getChangeAmount()));
//		
//		machine.putCoin(500);
//		assertThat("추가투입금액 500원", 600 , is(machine.getChangeAmount()));
//	}
//
//	@Test
//	public void testReturnChangeCoinSet_oneCoin_50() {
//		VendingMachine machine = new VendingMachine();
//		machine.putCoin(100);
//		machine.putCoin(100);
//		machine.putCoin(500);
//		machine.selectDrink(new Drink("Cola", 650));
//		
//		CoinSet expectedCoinSet = new CoinSet();
//		expectedCoinSet.add(50);
//		assertThat("700원 투입 후 650원 음료선택", expectedCoinSet, is(machine.getChangeCoinSet()));
//	}
//	
//	@Test
//	public void testReturnChangeCoinSet_coin_180() {
//		VendingMachine machine = new VendingMachine();
//		machine.putCoin(100);
//		machine.putCoin(100);
//		machine.putCoin(500);
//		machine.selectDrink(new Drink("Soda", 520));
//
//		CoinSet expectedCoinSet = new CoinSet();
//		expectedCoinSet.add(100);
//		expectedCoinSet.add(50);
//		expectedCoinSet.add(10);
//		expectedCoinSet.add(10);
//		expectedCoinSet.add(10);
//		assertThat("700원 투입 후 520원 음료선택", expectedCoinSet, is(machine.getChangeCoinSet()));
//
//	}
}
