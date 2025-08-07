package com.gdse.aad.spring_deployment.service.impl;

import com.gdse.aad.spring_deployment.entity.Customer;
import com.gdse.aad.spring_deployment.repo.CustomerRepository;
import com.gdse.aad.spring_deployment.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Customer not found with id: " + id)
        );
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer existCustomer = getCustomerById(customer.getId());
        existCustomer.setFirstName(customer.getFirstName());
        existCustomer.setLastName(customer.getLastName());
        return customerRepository.save(existCustomer);
    }

    @Override
    public void deleteCustomerById(int id) {
        Customer existCustomer = getCustomerById(id);
        customerRepository.delete(existCustomer);
    }
}
