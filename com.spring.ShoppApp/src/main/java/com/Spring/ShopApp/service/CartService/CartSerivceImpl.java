package com.Spring.ShopApp.service.CartService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Spring.ShopApp.Repository.AddToCartRepo;
import com.Spring.ShopApp.Repository.CheckoutRepo;
import com.Spring.ShopApp.model.AddtoCart;
import com.Spring.ShopApp.model.CheckoutCart;
import com.Spring.ShopApp.model.Products;
import com.Spring.ShopApp.service.ProductService.ProductServices;

@Service
public class CartSerivceImpl implements CartService {

	@Autowired
	AddToCartRepo addCartRepo;
	@Autowired
	CheckoutRepo checkOutRepo;
	@Autowired
	ProductServices proServices;
	private static final Logger logger = LoggerFactory.getLogger(CartSerivceImpl.class);

	@Override
	public List<AddtoCart> addCartbyUserIdAndProductId(long productId, long userId, int qty, double price)
			throws Exception {
		try {
			if (addCartRepo.getCartByProductIdAnduserId(userId, productId).isPresent()) {
				throw new Exception("Bu ürün sepetinizde. Dilerseniz sepetinizden miktari arttirabilirsiniz.");
			}
			AddtoCart obj = new AddtoCart();
			obj.setQty(qty);
			obj.setUser_id(userId);
			Products urun = proServices.getProductsById(productId);
			obj.setProduct(urun);

			/* Budan önce ürünün sepette olup olmadığını kontrol etmeliyim. */

			obj.setPrice(price);
			addCartRepo.save(obj);
			return this.getCartByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public List<AddtoCart> getCartByUserId(long userId) {
		return addCartRepo.getCartByuserId(userId);
	}

	@Override
	public List<AddtoCart> removeCartByUserId(long cartId, long userId) {
		addCartRepo.deleteCartByIdAndUserId(userId, cartId);
		return this.getCartByUserId(userId);
	}

	@Override
	public void updateQtyByCartId(long cartId, int qty, double price) throws Exception {
		addCartRepo.updateQtyByCartId(cartId, price, qty);
	}

	@Override
	// Sistemdeki ve veri tabanındaki tutarların aynı olup olmadıgını kontrol
	// ediyorum.

	public Boolean checkTotalAmountAgainstCart(double totalAmount, long userId) {
		double total_amount = addCartRepo.getTotalAmountByUserId(userId);
		if (total_amount == totalAmount) {
			return true;
		}
		System.out.print("Error");
		return false;
	}

	@Override
	public List<CheckoutCart> getAllCheckoutByUserId(long userId) {
		return checkOutRepo.getByuserId(userId);
	}

	@Override
	public List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp) throws Exception {
		try {
			long user_id = tmp.get(0).getUser_id();
			if (0 < tmp.size()) {
				checkOutRepo.saveAll(tmp);
				this.removeAllCartByUserId(user_id);
				return this.getAllCheckoutByUserId(user_id);
			} else {
				throw new Exception("Error!");
				// Boş olmamalı
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public List<AddtoCart> removeAllCartByUserId(long userId) {
		addCartRepo.deleteAllCartByUserId(userId);
		return null;
	}

}
