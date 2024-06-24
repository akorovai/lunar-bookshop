package dev.s24377.lunar_bookshop;

import dev.s24377.lunar_bookshop.book.Book;
import dev.s24377.lunar_bookshop.book.BookRepository;
import dev.s24377.lunar_bookshop.client.Client;
import dev.s24377.lunar_bookshop.client.ClientRepository;
import dev.s24377.lunar_bookshop.client.regular.RegularClientRepository;
import dev.s24377.lunar_bookshop.client.vip.VIPClient;
import dev.s24377.lunar_bookshop.client.regular.RegularClient;
import dev.s24377.lunar_bookshop.client.vip.VIPClientRepository;
import dev.s24377.lunar_bookshop.complaint.Complaint;
import dev.s24377.lunar_bookshop.complaint.ComplaintRepository;
import dev.s24377.lunar_bookshop.employee.cashier.Cashier;
import dev.s24377.lunar_bookshop.employee.EmployeeRepository;
import dev.s24377.lunar_bookshop.employee.section_manager.SectionManager;
import dev.s24377.lunar_bookshop.employee.section_manager.SectionManagerRepository;
import dev.s24377.lunar_bookshop.enums.*;
import dev.s24377.lunar_bookshop.order.Order;
import dev.s24377.lunar_bookshop.order.OrderRepository;
import dev.s24377.lunar_bookshop.order_book.OrderBook;
import dev.s24377.lunar_bookshop.order_book.OrderBookRepository;
import dev.s24377.lunar_bookshop.promotion.Promotion;
import dev.s24377.lunar_bookshop.promotion.PromotionRepository;
import dev.s24377.lunar_bookshop.purchase.Purchase;
import dev.s24377.lunar_bookshop.purchase.PurchaseRepository;
import dev.s24377.lunar_bookshop.section.Section;
import dev.s24377.lunar_bookshop.section.SectionRepository;
import dev.s24377.lunar_bookshop.supplier.Address;
import dev.s24377.lunar_bookshop.supplier.AddressRepository;
import dev.s24377.lunar_bookshop.supplier.Supplier;
import dev.s24377.lunar_bookshop.supplier.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final ComplaintRepository complaintRepository;
    private final EmployeeRepository employeeRepository;
    private final OrderRepository orderRepository;
    private final PromotionRepository promotionRepository;
    private final PurchaseRepository purchaseRepository;
    private final SectionRepository sectionRepository;
    private final SupplierRepository supplierRepository;
    private final AddressRepository addressRepository;
    private final OrderBookRepository orderBookRepository;
    private final SectionManagerRepository sectionManagerRepository;
    private final RegularClientRepository regularClientRepository;
    private final VIPClientRepository vipClientRepository;

    @Override
    public void run(String... args) {
        seedPromotions();
        seedSections();
        seedBooks();
        seedClients();
        seedComplaints();
        seedEmployees();
        seedSuppliers();
        seedOrders();
        seedPurchases();
    }

    private void seedSections() {
        Section fiction = Section.builder().name("Fiction").area(200.0).type(SECTION_TYPE.FICTION).subsections(List.of()).books(List.of()).manager(null).build();

        Section science = Section.builder().name("Science").area(150.0).type(SECTION_TYPE.SCIENCE).subsections(List.of()).books(List.of()).manager(null).build();

        sectionRepository.saveAll(List.of(fiction, science));
    }

    private void seedBooks() {
        Section fiction = sectionRepository.findByName("Fiction").orElse(null);
        Section science = sectionRepository.findByName("Science").orElse(null);

        if (fiction != null && science != null) {
            Book book1 = Book.builder().isbn(generateUniqueISBN()).title("The Great Gatsby").author("F. Scott Fitzgerald").price(10.99).publicationDate(LocalDate.of(1925, 4, 10)).booksInWarehouse(50).sections(List.of(fiction)).promotion(promotionRepository.findByTitle("Summer Sale").orElse(null)).build();

            Book book2 = Book.builder().isbn(generateUniqueISBN()).title("A Brief History of Time").author("Stephen Hawking").price(15.99).publicationDate(LocalDate.of(1988, 4, 1)).booksInWarehouse(30).sections(List.of(science)).promotion(promotionRepository.findByTitle("New Year's Special").orElse(null)).build();

            Book book3 = Book.builder().isbn(generateUniqueISBN()).title("To Kill a Mockingbird").author("Harper Lee").price(12.99).publicationDate(LocalDate.of(1960, 7, 11)).booksInWarehouse(40).sections(List.of(fiction)).build();

            Book book4 = Book.builder().isbn(generateUniqueISBN()).title("The Origin of Species").author("Charles Darwin").price(18.99).publicationDate(LocalDate.of(1859, 11, 24)).booksInWarehouse(20).sections(List.of(science)).build();

            Book book5 = Book.builder().isbn(generateUniqueISBN()).title("1984").author("George Orwell").price(14.99).publicationDate(LocalDate.of(1949, 6, 8)).booksInWarehouse(60).sections(List.of(fiction)).build();

            Book book6 = Book.builder().isbn(generateUniqueISBN()).title("The Selfish Gene").author("Richard Dawkins").price(16.99).publicationDate(LocalDate.of(1976, 11, 1)).booksInWarehouse(35).sections(List.of(science)).build();

            bookRepository.saveAll(List.of(book1, book2, book3, book4, book5, book6));

            fiction.getBooks().addAll(List.of(book1, book3, book5));
            science.getBooks().addAll(List.of(book2, book4, book6));

            sectionRepository.saveAll(List.of(fiction, science));
        }
    }


    private long generateUniqueISBN() {
        Random random = new Random();
        return Math.abs(random.nextLong());
    }


    private void seedClients() {
        Client client1 = new VIPClient("John", "Doe", GENDER.MAN, LocalDate.of(2020, 1, 1),  true);
        Client client2 = new RegularClient("Jane", "Doe", GENDER.WOMAN, LocalDate.of(2021, 1, 1));

        clientRepository.saveAll(List.of(client1, client2));

//        client1 = new RegularClient(client1, clientRepository);
//        clientRepository.save(client1);

    }

    private void seedComplaints() {
        if (clientRepository.count() > 0 && bookRepository.count() > 0) {
            Client client = clientRepository.findAll().getFirst();
            Book book = bookRepository.findAll().getFirst();

            Complaint complaint = Complaint.builder().problemDescription("Pages are torn").submittedDate(LocalDate.now()).status(COMPLAINT_STATUS.SUBMITTED).decision("Pending").client(client).book(book).build();

            complaintRepository.save(complaint);
        }
    }

    private void seedEmployees() {
        if (sectionRepository.count() > 0) {
            Section section = sectionRepository.findAll().getFirst();

            Cashier cashier = new Cashier();
            cashier.setName("Alice");
            cashier.setSurname("Smith");
            cashier.setGender(GENDER.WOMAN);
            cashier.setHireDate(LocalDate.of(2022, 5, 10));
            cashier.setAccessLevel(ACCESS_LEVEL.BASIC);
            employeeRepository.save(cashier);

            SectionManager manager = SectionManager.builder().section(section).name("Bob").surname("Johnson").gender(GENDER.MAN).hireDate(LocalDate.of(2021, 3, 15)).section(section).build();

            section.setManager(manager);
            sectionRepository.save(section);
        }
    }


    private void seedSuppliers() {

        Address address = Address.builder().street("123 Main St").city("Anytown").postalCode("12345").country("Country").build();

        addressRepository.save(address);

        Supplier supplier = Supplier.builder().companyName("Book Distributors Inc.").contactNumber(1234567890L).type(SUPPLIER_TYPE.REGULAR).address(address).build();

        supplierRepository.save(supplier);
    }

    private void seedOrders() {
        Supplier supplier = supplierRepository.findByCompanyName("Book Distributors Inc.").orElse(null);
        Book book1 = bookRepository.findByTitle("The Great Gatsby").orElse(null);
        Book book2 = bookRepository.findByTitle("A Brief History of Time").orElse(null);

        if (supplier != null && book1 != null && book2 != null) {
            Order order1 = Order.builder()
                    .orderedDate(LocalDate.now())
                    .type(ORDER_TYPE.PENDING)
                    .supplier(supplier)
                    .build();

            OrderBook orderBook1 = OrderBook.builder()
                    .order(order1)
                    .book(book1)
                    .quantity(10)
                    .build();

            OrderBook orderBook2 = OrderBook.builder()
                    .order(order1)
                    .book(book2)
                    .quantity(15)
                    .build();

            order1.setOrderBooks(List.of(orderBook1, orderBook2));
            orderRepository.save(order1);

            Order order2 = Order.builder()
                    .orderedDate(LocalDate.now().minusDays(1))
                    .type(ORDER_TYPE.DONE)
                    .supplier(supplier)
                    .build();

            OrderBook orderBook3 = OrderBook.builder()
                    .order(order2)
                    .book(book2)
                    .quantity(20)
                    .build();

            order2.setOrderBooks(List.of(orderBook3));
            orderRepository.save(order2);
        }
    }



    private void seedPromotions() {

        Promotion promotion1 = Promotion.builder().title("Summer Sale").description("Get 20% off on all books").startDate(LocalDate.of(2024, 6, 1)).period(30).type(PROMOTION_TYPE.SEASONAL).books(new ArrayList<>()).build();


        Promotion promotion2 = Promotion.builder().title("New Year's Special").description("Start the new year with our bestsellers!").startDate(LocalDate.of(2024, 1, 1)).period(15).type(PROMOTION_TYPE.ONE_TIME).books(new ArrayList<>()).build();


        Promotion promotion3 = Promotion.builder().title("Back-to-School").description("Prepare for the new school year with our educational books.").startDate(LocalDate.of(2024, 8, 15)).period(45).type(PROMOTION_TYPE.SEASONAL).books(new ArrayList<>()).build();


        promotionRepository.saveAll(List.of(promotion1, promotion2, promotion3));
    }


    private void seedPurchases() {
        Client client = clientRepository.findAll().getFirst();
        Book book = bookRepository.findAll().getFirst();
        Purchase purchase = Purchase.builder().purchaseDate(LocalDate.now()).type(PURCHASE_TYPE.ONLINE).client(client).books(List.of(book)).build();
        purchaseRepository.save(purchase);
    }
}
