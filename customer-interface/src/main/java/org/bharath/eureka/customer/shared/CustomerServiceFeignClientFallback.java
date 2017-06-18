package org.bharath.eureka.customer.shared;

import org.springframework.stereotype.Component;

@Component
public class CustomerServiceFeignClientFallback implements CustomerServiceFeignClient {

    public Customer getCustomer(int id) {
        return new Customer(12, "Fallback", "Customer");
    }

}
