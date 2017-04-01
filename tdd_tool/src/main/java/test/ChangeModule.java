package test;

public class ChangeModule {
	enum COIN {
		KRW500(500), KRW100(100), KRW50(50), KRW10(10);
		private int value;
		COIN(int value) {
			this.value = value;
		}
	}

	public CoinSet getChangeCoinSet(int changeAmount) {
		CoinSet coinSet = new CoinSet();
		
		final int KRW500 = COIN.KRW500.value;
		final int KRW100 = 100;
		final int KRW50 = 50;
		final int KRW10 = 10;

		changeAmount = addCoinsToCoinSet(changeAmount, coinSet, KRW500);
		changeAmount = addCoinsToCoinSet(changeAmount, coinSet, KRW100);
		changeAmount = addCoinsToCoinSet(changeAmount, coinSet, KRW50);
		changeAmount = addCoinsToCoinSet(changeAmount, coinSet, KRW10);

		return coinSet;
	}

	private int addCoinsToCoinSet(int changeAmount, CoinSet coinSet, int KRW500) {
		while(changeAmount >= KRW500) {
			changeAmount -= KRW500;
			coinSet.add(KRW500);
		}
		return changeAmount;
	}

}
