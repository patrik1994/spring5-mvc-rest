package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;

import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import guru.springframework.model.CustomerDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {
    public static final Long ID = 2L;
    public static final String FIRSTNAME = "Lois";
    public static final String LASTNAME = "Griffin";
    CustomerService customerService;

    MockMvc mockMvc;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void getAllCustomers() throws Exception {
        //given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        //then
        assertEquals(3, customerDTOS.size());
    }

    @Test
    public void getCustomerById() throws Exception {
        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(customer));

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        assertEquals(FIRSTNAME, customerDTO.getFirstname());
        assertEquals(LASTNAME, customerDTO.getLastname());
    }

    @Test
    public void saveCustomerByDTO() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(1l);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDTO = customerService.saveCustomerByDTO(1l, customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDTO.getFirstname());
        assertEquals("/api/v1/customers/1", savedDTO.getCustomerUrl());
    }

    @Test
    public void deleteCustomerById() throws Exception {
        Long id = 1L;
        customerRepository.deleteById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }

}
