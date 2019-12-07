package vn.ltp.core.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	
	private String cartId;
	private int productId;
	private int price;
	private short quantity;
	private String imageUrl;
	private String title;
}
