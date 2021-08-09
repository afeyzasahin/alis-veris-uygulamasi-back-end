package com.Spring.ShopApp.controller;

import java.util.HashMap;
import java.util.List;
import com.Spring.ShopApp.JWTConfiguration.ShoppingConfiguration;
import com.Spring.ShopApp.controller.RequestPojo.ApiResponse;
import com.Spring.ShopApp.model.AddtoCart;
import com.Spring.ShopApp.service.CartService.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/addtocart")
public class AddtoCartController {
	
	/* Bu sınıfta requestler alınacak ve servisteki
	fonksiyonların çalışması için bağlantı kurulacak */

	@Autowired
	CartService cartService;

	@RequestMapping("addProduct")
	
	public ResponseEntity<?> addCartwithProduct(@RequestBody HashMap<String, String> addCartRequest) {
		try {
			String keys[] = { "productId", "userId", "qty", "price" };
			
			if (ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {}
			
			long productId = Long.parseLong(addCartRequest.get("productId"));
			long userId = Long.parseLong(addCartRequest.get("userId"));
			double price = Double.parseDouble(addCartRequest.get("price"));
			int qty = Integer.parseInt(addCartRequest.get("qty"));

			
			List<AddtoCart> obj = cartService.addCartbyUserIdAndProductId(productId, userId, qty, price);
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}

	}

	@RequestMapping("updateQtyForCart")
	public ResponseEntity<?> updateQtyForCart(@RequestBody HashMap<String, String> addCartRequest) {
		try {
			String keys[] = { "cartId", "userId", "qty", "price" };
			if (ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {

			}
			long cartId;
			long userId;
			int qty;
			double price;

			cartId = Long.parseLong(addCartRequest.get("cartId"));
			userId = Long.parseLong(addCartRequest.get("userId"));
			qty = Integer.parseInt(addCartRequest.get("qty"));
			price = Double.parseDouble(addCartRequest.get("price"));

			cartService.updateQtyByCartId(cartId, qty, price);
			
			List<AddtoCart> obj = cartService.getCartByUserId(userId);
			
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}

	}

	@RequestMapping("removeProductFromCart")
	public ResponseEntity<?> removeCartwithProductId(@RequestBody HashMap<String, String> removeCartRequest) {
		try {
			String keys[] = { "userId", "cartId" };
			if (ShoppingConfiguration.validationWithHashMap(keys, removeCartRequest)) {

			}
			List<AddtoCart> obj;
			obj = cartService.removeCartByUserId(Long.parseLong(removeCartRequest.get("cartId")),
					Long.parseLong(removeCartRequest.get("userId")));
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
	}

	@RequestMapping("getCartsByUserId")
	public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String, String> getCartRequest) {
		try {
			String keys[] = { "userId" };
			if (ShoppingConfiguration.validationWithHashMap(keys, getCartRequest)) {
			}
			List<AddtoCart> obj;
			obj = cartService.getCartByUserId(Long.parseLong(getCartRequest.get("userId")));
			return ResponseEntity.ok(obj);
		}   
		    catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
	}
}