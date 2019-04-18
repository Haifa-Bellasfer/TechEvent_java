package utils;

import entity.Cart;
import entity.Event;
import service.CartService;


/**
 *
 * @author ihebc_000
 */
public class Session {
     public static Event current_event=null;
    public static double total=0;
    public static int user=3;
    public static int idCart;
    public Session() {
        CartService d=CartService.getInstance();
        Cart o=new Cart(1,Session.user,0);
        d.insert(o);
    }

    public static int getIdCart() {
      CartService d=CartService.getInstance();
     Cart c=d.DisplayByIdCart(user);

        return c.getId_cart();
    }

    public static void setIdCart(int idCart) {
        Session.idCart = idCart;
    }
}
