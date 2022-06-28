package ru.job4j.collection;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.*;

public class OrderConvertTest {

    @Test
    public void whenSingleOrder() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("3sfe", "Dress"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.get("3sfe"), is(new Order("3sfe", "Dress")));
    }

    @Test
    public void whenMultipleOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("3sfe", "Dress"));
        orders.add(new Order("4fh", "Shirt"));
        orders.add(new Order("5fgh", "Hat"));
        orders.add(new Order("3sfe", "Skirt"));
        orders.add(new Order("4fh", "Trainers"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.size(), is(3));
    }
}