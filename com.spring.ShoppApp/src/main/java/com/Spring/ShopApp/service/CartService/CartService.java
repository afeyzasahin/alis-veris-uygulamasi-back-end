package com.Spring.ShopApp.service.CartService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Spring.ShopApp.model.AddtoCart;
import com.Spring.ShopApp.model.CheckoutCart;
import com.Spring.ShopApp.model.Products;

@Service
public interface CartService {

	/*
	 * Servis sınıfınfa addCartbyUserIdAndProductId() getCartByUserId()
	 * removeCartByUserId() CheckoutCart() metotları olmalı
	 */

	List<AddtoCart> addCartbyUserIdAndProductId(long productId, long userId, int qty, double price) throws Exception;

	void updateQtyByCartId(long cartId, int qty, double price) throws Exception;

	List<AddtoCart> getCartByUserId(long userId);

	List<AddtoCart> removeCartByUserId(long cartId, long userId);

	List<AddtoCart> removeAllCartByUserId(long userId);

	Boolean checkTotalAmountAgainstCart(double totalAmount, long userId);

	List<CheckoutCart> getAllCheckoutByUserId(long userId);

	List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp) throws Exception;

}
