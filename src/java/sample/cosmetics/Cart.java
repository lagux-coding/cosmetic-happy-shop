/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.cosmetics;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class Cart {

    private Map<String, CosmeticDTO> cart;

    public Cart() {
    }

    public Cart(Map<String, CosmeticDTO> cart) {
        this.cart = cart;
    }

    public Map<String, CosmeticDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, CosmeticDTO> cart) {
        this.cart = cart;
    }

    public boolean add(CosmeticDTO cosmetic) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }
            if (this.cart.containsKey(cosmetic.getProductID())) {
                int currentQuantity = this.cart.get(cosmetic.getProductID()).getProductQuantity();
                cosmetic.setProductQuantity(currentQuantity + cosmetic.getProductQuantity());
            }
            this.cart.put(cosmetic.getProductID(), cosmetic);
            check = true;
        } catch (Exception e) {
        }
        return check;
    }

    public boolean remove(String id) {
        boolean check = false;
        try {
            if(this.cart != null){
                this.cart.remove(id);
                check = true;
            }
        } catch (Exception e) {
        }
        return check;
    }

    public boolean update(String id, CosmeticDTO cosmetic) {
        boolean check = false;
        try {
            if(this.cart != null){
                if(this.cart.containsKey(id)){
                    this.cart.replace(id, cosmetic);
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }

}
