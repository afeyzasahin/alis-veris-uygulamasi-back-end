package com.Spring.ShopApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Spring.ShopApp.JWTConfiguration.ShoppingConfiguration;
import com.Spring.ShopApp.controller.RequestPojo.ApiResponse;
import com.Spring.ShopApp.model.AddtoCart;
import com.Spring.ShopApp.model.CheckoutCart;
import com.Spring.ShopApp.model.Products;
import com.Spring.ShopApp.service.CartService.CartService;
import com.Spring.ShopApp.service.ProductService.ProductServices;

@RestController
@RequestMapping("api/order")
public class OrderController {

	/*
	 * Bu sınıfta requestler alınacak ve servisteki fonksiyonların çalışması için
	 * bağlantı kurulacak
	 */

	@Autowired
	CartService cartService;
	ProductServices proService;

	@RequestMapping("checkout_order")
	public ResponseEntity<?> checkout_order(@RequestBody HashMap<String, String> addCartRequest) {
		try {
			String keys[] = { "userId", "total_price", "pay_type", "deliveryAddress" };
			if (ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {

			}
			long user_Id = Long.parseLong(addCartRequest.get("userId"));

			double toplamUcret;
			toplamUcret = Double.parseDouble(addCartRequest.get("total_price"));

			if (cartService.checkTotalAmountAgainstCart(toplamUcret, user_Id)) {

				List<CheckoutCart> tmp = new ArrayList<CheckoutCart>();

				List<AddtoCart> cartItems = cartService.getCartByUserId(user_Id);

				for (AddtoCart addCart : cartItems) {

					String orderId = "" + getOrderId();
					CheckoutCart cart = new CheckoutCart();
					cart.setPayment_type(addCartRequest.get("pay_type"));
					cart.setPrice(toplamUcret);
					cart.setUser_id(user_Id);
					cart.setOrder_id(orderId);
					cart.setProduct(addCart.getProduct());
					cart.setQty(addCart.getQty());
					cart.setDelivery_address(addCartRequest.get("deliveryAddress"));
					tmp.add(cart);
				}
				cartService.saveProductsForCheckout(tmp);
				return ResponseEntity.ok(new ApiResponse("Sipariş gönderildi", ""));
			} else {
				throw new Exception("Toplam ücret hatalı, sipariş gönderilemez.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
	}

	public int getOrderId() {
		Random rd;
		rd = new Random(System.currentTimeMillis());
		return 10000 + rd.nextInt(20000);
	}

	@RequestMapping("getOrdersByUserId")
	public ResponseEntity<?> getOrdersByUserId(@RequestBody HashMap<String, String> ordersRequest) {
		try {
			String keys[] = { "userId" };
			return ResponseEntity.ok(new ApiResponse("Sipariş alındı", ""));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}

	}
}
