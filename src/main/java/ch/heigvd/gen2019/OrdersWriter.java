package ch.heigvd.gen2019;

import ch.heigvd.gen2019.size.NoSize;

import java.util.List;

public class OrdersWriter {
    private List<Order> orders;

    public OrdersWriter(List<Order> orders) {
        this.orders = orders;
    }

    public String getContents() {
        StringBuffer sb = new StringBuffer("{\"orders\": [");

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            sb.append("{");
            sb.append("\"id\": ");
            sb.append(order.getOrderId());
            sb.append(", ");
            sb.append("\"products\": [");

            for (int j = 0; j < order.getProductsCount(); j++) {
                Product product = order.getProduct(j);
                appendProduct(sb, product);
            }

            if (order.getProductsCount() > 0) {
                sb.delete(sb.length() - 2, sb.length());
            }

            sb.append("]");
            sb.append("}, ");
        }

        if (orders.size() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }

        return sb.append("]}").toString();
    }

    private void appendProduct(StringBuffer sb, Product product) {
        sb.append("{");
        sb.append("\"code\": \"");
        sb.append(product.getCode());
        sb.append("\", ");
        sb.append("\"color\": \"");
        sb.append(product.getColor());
        sb.append("\", ");

        if (product.getSize().getClass() != NoSize.class) {
            appendProductSize(sb, product);
        }

        sb.append("\"price\": ");
        sb.append(product.getPrice());
        sb.append(", ");
        sb.append("\"currency\": \"");
        sb.append(product.getCurrency());
        sb.append("\"}, ");
    }

    private void appendProductSize(StringBuffer sb, Product product) {
        sb.append("\"size\": \"");
        sb.append(product.getSize().toString());
        sb.append("\", ");
    }
}