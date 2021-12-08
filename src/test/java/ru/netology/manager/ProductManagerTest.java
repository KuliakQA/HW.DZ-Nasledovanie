package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    private Book coreJava = new Book(7, "Hand", 300, "China");
    private Book two = new Book(8,"Home", 100,"Miyoko");
    private Book tree = new Book(9, "Sun", 200, "Kleo Leo");
    private Book four = new Book(10, "Sun2", 400, "Kolenio");
    private Book six = new Book(15, "Moon", 200, "Kleo Lep");
    private Smartphone samsung = new Smartphone(22, "Samsung", 1000, "Korea");
    private Smartphone nokia = new Smartphone(33, "Nokia", 800, "Finland");
    private Smartphone huawei = new Smartphone(44, "Huawei", 500, "China");
    private Smartphone huawei70 = new Smartphone(45, "Huawei", 500, "China");

    @Test
    public void shouldSearchAuthor() {
        manager.add(coreJava);
        manager.add(two);
        manager.add(samsung);
        manager.add(tree);

        Product[] expected = new Product[]{tree};
        Product[] actual = manager.searchBy("Kleo");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchName() {
        manager.add(nokia);
        manager.add(huawei);
        manager.add(samsung);

        Product[] expected = new Product[]{nokia};
        Product[] actual = manager.searchBy("Nokia");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchWith2Letters() {
        manager.add(nokia);
        manager.add(huawei);
        manager.add(samsung);

        Product[] expected = new Product[]{huawei};
        Product[] actual = manager.searchBy("Hu");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSameName() {
        manager.add(four);
        manager.add(tree);

        Product[] expected = new Product[]{four, tree};
        Product[] actual = manager.searchBy("Sun");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAlmostSameAuthor() {
        manager.add(tree);
        manager.add(six);
        manager.add(nokia);
        manager.add(four);

        Product[] expected = new Product[]{six};
        Product[] actual = manager.searchBy("Kleo Lep");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAuthorAndMaker() {
        manager.add(huawei70);
        manager.add(huawei);
        manager.add(coreJava);

        Product[] expected = new Product[]{huawei, huawei70, coreJava};
        Product[] actual = manager.searchBy("China");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNothing() {
        manager.add(huawei70);
        manager.add(huawei);
        manager.add(coreJava);
        manager.add(four);
        manager.add(samsung);
        manager.add(six);

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("0");
        assertArrayEquals(expected, actual);
    }
}