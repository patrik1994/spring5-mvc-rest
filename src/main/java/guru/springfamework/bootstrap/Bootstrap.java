package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner{

    private CategoryRepository categoryRespository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRespository,
                     CustomerRepository customerRepository,
                     VendorRepository vendorRepository) {
        this.categoryRespository = categoryRespository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRespository.save(fruits);
        categoryRespository.save(dried);
        categoryRespository.save(fresh);
        categoryRespository.save(exotic);
        categoryRespository.save(nuts);

        System.out.println("Categories loaded");
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setFirstname("Michale");
        customer1.setLastname("Weston");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2l);
        customer2.setFirstname("Sam");
        customer2.setLastname("Axe");
        customerRepository.save(customer2);

        System.out.println("Customers loaded");
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setId(672l);
        vendor1.setName("Western Tasty Fruits Ltd.");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setId(32l);
        vendor2.setName("Exotic Fruits Company");
        vendorRepository.save(vendor2);

        Vendor vendor3 = new Vendor();
        vendor3.setId(501l);
        vendor3.setName("Home Fruits");
        vendorRepository.save(vendor3);

        Vendor vendor4 = new Vendor();
        vendor4.setId(810l);
        vendor4.setName("Fun Fresh Fruits Ltd.");
        vendorRepository.save(vendor4);

        System.out.println("Vendors loaded");
    }
}
