package ch.heigvd.gen2019;

import ch.heigvd.gen2019.color.*;
import ch.heigvd.gen2019.size.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrdersWriterTest {
    List<Order> orders = new ArrayList<>();
    Order order111 = new Order(111);

    @BeforeEach
    public void SetupOneOrder() {
        orders.add(order111);
    }

    @Test
    public void NoOrder() {
        Assertions.assertEquals("{\"orders\": []}", new OrdersWriter(new ArrayList<>()).getContents());
    }

    @Test
    public void OneOrder() {
        String order111 = "{\"id\": 111, \"products\": []}";
        assertEquals("{\"orders\": [" + order111 + "]}", new OrdersWriter(orders).getContents());
    }

    @Test
    public void TwoOrders() {
        orders.add(new Order(222));

        String order111Json = JsonOrder111WithProduct("");
        String order222Json = "{\"id\": 222, \"products\": []}";
        assertEquals("{\"orders\": [" + order111Json + ", " + order222Json + "]}", new OrdersWriter(orders).getContents());
    }

    @Test
    public void OneOrderWithOneProduct() {
        order111.AddProduct(new Product("Shirt", new Blue(), new M(), 2.99, "TWD"));

        String order111Json = JsonOrder111WithProduct("{\"code\": \"Shirt\", \"color\": \"blue\", \"size\": \"M\", \"price\": 2.99, \"currency\": \"TWD\"}");
        assertEquals("{\"orders\": [" + order111Json + "]}", new OrdersWriter(orders).getContents());
    }

    @Test
    public void OneOrderWithOneProductNoSize() {
        order111.AddProduct(new Product("Pot", new Red(), new NoSize(), 16.50, "SGD"));

        String order111Json = JsonOrder111WithProduct("{\"code\": \"Pot\", \"color\": \"red\", \"price\": 16.5, \"currency\": \"SGD\"}");
        assertEquals("{\"orders\": [" + order111Json + "]}", new OrdersWriter(orders).getContents());
    }

    private String JsonOrder111WithProduct(String productJson) {
        return "{\"id\": 111, \"products\": [" + productJson + "]}";
    }
}
