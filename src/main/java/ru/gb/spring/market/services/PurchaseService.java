package ru.gb.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.spring.market.dto.CartItem;
import ru.gb.spring.market.entities.*;
import ru.gb.spring.market.repositories.PurchaseDao;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PurchaseService {
    private final CartService cartService;
    private final ProductService productService;
    private final PurchaseDao purchaseDao;
    private final PurchaseProductService purchaseProductService;
    private final WarehouseService warehouseService;
    private final UserService userService;

    public Customer getCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customerName = authentication.getName();
        Customer customer = userService.findByUserUsername(customerName);
        if (customer == null) {
            customer = new Customer();
        }

        return customer;
    }

    public Purchase savePurchase(Customer customer) {
        Purchase purchase = new Purchase();
        purchase.setCustomer(customer);
        return purchaseDao.saveAndFlush(purchase);
    }

    public void savePurchaseProducts(Purchase purchase) {
        List<CartItem> cartItems = cartService.getCart();

        for (CartItem cartItem : cartItems) {
            PurchaseProduct purchaseProduct = new PurchaseProduct();
            Product product = productService.findById(cartItem.getId()).orElseThrow();
            Warehouse warehouse = product.getWarehouse();
            Double amountInWarehouse = warehouse.getAmount();
            Double amount = cartItem.getAmount();
            Double totalPrice = cartItem.getTotalPrice();
            if (amount > amountInWarehouse) {
                amount = amountInWarehouse;
                totalPrice = amount * cartItem.getPrice();
            }

            warehouse.setAmount(amountInWarehouse - amount);
            warehouseService.changeAmount(warehouse);

            purchaseProduct.setPurchase(purchase);
            purchaseProduct.setProduct(product);
            purchaseProduct.setProductCount(amount);
            purchaseProduct.setProductPrice(totalPrice);

            purchaseProductService.saveProduct(purchaseProduct);
        }

        purchaseProductService.flush();

        cartService.clearCart();
    }

    public void createPurchase() {
        Customer customer = getCustomer();
        Purchase purchase = savePurchase(customer);
        savePurchaseProducts(purchase);
    }
}
