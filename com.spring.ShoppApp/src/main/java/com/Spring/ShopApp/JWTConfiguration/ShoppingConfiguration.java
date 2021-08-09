package com.Spring.ShopApp.JWTConfiguration;

import java.util.HashMap;

public class ShoppingConfiguration {

	public static Boolean validationWithHashMap(String keys[], HashMap<String, String> request) throws Exception {
		Boolean status;
		status = false;
		try {
			int i;
			for (i = 0; i < keys.length; i++) {

				/*
				 * ilk if döngüsünde olup olmadığını ikinci if döngüsünde boş olup olmadığı
				 * kontrol ediliyor.
				 */

				if (request.containsKey(keys[i])) {
					if (request.get(keys[i]).equals("")) {
						throw new Exception(keys[i] + " Bos olmamali!");
					}
				} 
				else {
					throw new Exception(keys[i] + " bulunamadı");
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("HATA: " + e.getMessage());
		}
		return status;
	}
}
