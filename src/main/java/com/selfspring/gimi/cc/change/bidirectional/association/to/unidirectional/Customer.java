/**
 * 
 */
package change.bidirectional.association.to.unidirectional;

import junit.framework.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * @author meng.sun
 *
 */
public class Customer {
	
	private Set<Order> _orders = new HashSet<Order>();
	
	Set<Order> friendOrders() {
		return _orders;
	}
	
	void addOrder(Order order) {
		order.setCustomer(this);
	}
	
	double getDiscount() {
		return 1;
	}
	
	double getPriceFor (Order order) {
		Assert.assertTrue(_orders.contains(order));
		return order.getDisCountedPrice();
	}
}
