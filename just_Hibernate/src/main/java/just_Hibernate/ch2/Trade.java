package just_Hibernate.ch2;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // xml에 매핑대신
@Table(name="TRRADES") // 테이블이름과 클래스명 같으면 생략가능
public class Trade {
	@Id
	private long tradeId = -1;
	private double quantity = 0;
	private String security = null;
	
	public long getTradeId() {
		return tradeId;
	}
	public void setTradeId(long tradeId) {
		this.tradeId = tradeId;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	
	

}
