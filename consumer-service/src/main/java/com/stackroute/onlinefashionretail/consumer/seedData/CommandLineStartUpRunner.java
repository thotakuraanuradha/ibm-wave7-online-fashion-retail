package com.stackroute.onlinefashionretail.consumer.seedData;

import com.stackroute.onlinefashionretail.consumer.domain.*;
import com.stackroute.onlinefashionretail.consumer.exception.Consumer.ConsumerAlreadyExistsException;
import com.stackroute.onlinefashionretail.consumer.exception.Product.ProductAlreadyExistsException;
import com.stackroute.onlinefashionretail.consumer.service.interfaces.ConsumerOrderService;
import com.stackroute.onlinefashionretail.consumer.service.interfaces.ConsumerService;
import com.stackroute.onlinefashionretail.consumer.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommandLineStartUpRunner implements CommandLineRunner {
    private ConsumerOrderService consumerOrderService;
    private ConsumerService consumerService;
    private ProductService productService;

    /**
     * Use constructor based DI to inject TrackService here
     */
    @Autowired
    public CommandLineStartUpRunner(ConsumerOrderService consumerOrderService,
                                    ConsumerService consumerService,
                                    ProductService productService) {
        this.consumerOrderService = consumerOrderService;
        this.consumerService = consumerService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        /* *Pre-fill the database whenever application starts*/
        Designer designer1 = new Designer("1",
                "Sabyasachi",
                "Kolkata",
                4.7f,
                "designs@sabyasachi.com",
                9847287456L,
                new ArrayList<>());

        Designer designer2 = new Designer("2",
                "Manish Malhotra",
                "Delhi",
                4.6f,
                "designs@manishmalhotra.com",
                9847132560L,
                new ArrayList<>());

        Product product1 = new Product("1",
                "Embroidered Lehenga",
                "Clothing",designer1,4565,23,3.5f,"imageLehenga");

        Product product2 = new Product("2",
                "Pin-Striped Shirt",
                "Clothing",designer2,4565,23,3.5f,"imageShirt");

        Product product3 = new Product("3",
                "Blue Velvet Dress",
                "Clothing",designer2,4565,23,3.5f,"imageDress");

        Product product4 = new Product("4",
                "Metallic Detail Earrings",
                "Jewellery",designer1,4565,23,3.5f,"imageEarrings");

        Address address = new Address("1",
                "Shruti",
                "Saxena",
                "B-708, 5th lane, BWD layout",
                "Shanti nagar",
                "",
                "Uttar Pradesh",
                "Lucknow",
                "222034");

        Consumer consumer1 = new Consumer("1",
                "shruti67",
                "shruti67@gmail.com",
                Map.of("1",address),
                new HashMap<>(),
                List.of(product1,product2));

        Consumer consumer2 = new Consumer("2",
                "mahima",
                "mahi78@gmail.com",
                new HashMap<>(),
                new HashMap<>(),
                List.of(product4,product3));
        try {
            productService.addProduct(product1);
            productService.addProduct(product2);
            productService.addProduct(product3);
            productService.addProduct(product4);
        } catch (ProductAlreadyExistsException exception) {
            exception.printStackTrace();
        }

        try {
            consumerService.addConsumer(consumer1);
            consumerService.addConsumer(consumer2);
        } catch (ConsumerAlreadyExistsException exception) {
            exception.printStackTrace();
        }
    }
}
