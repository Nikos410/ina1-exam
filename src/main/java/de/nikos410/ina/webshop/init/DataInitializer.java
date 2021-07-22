package de.nikos410.ina.webshop.init;

import de.nikos410.ina.webshop.model.entity.StockArticle;
import de.nikos410.ina.webshop.model.entity.User;
import de.nikos410.ina.webshop.repository.InMemoryStockArticleRepository;
import de.nikos410.ina.webshop.repository.InMemoryUserRepository;
import de.nikos410.ina.webshop.repository.StockArticleRepository;
import de.nikos410.ina.webshop.repository.UserRepository;
import de.nikos410.ina.webshop.security.PasswordEncoder;
import de.nikos410.ina.webshop.security.Sha256PasswordEncoder;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;

/**
 * Initializes some default data.
 */
public class DataInitializer {

    private final PasswordEncoder passwordEncoder = new Sha256PasswordEncoder();
    private final UserRepository userRepository = InMemoryUserRepository.getInstance();
    private final StockArticleRepository stockArticleRepository = InMemoryStockArticleRepository.getInstance();

    public void initializeData() {
        initializeUsers();
        initializeStockArticles();
    }

    private void initializeUsers() {
        userRepository.add(buildUser1());
        userRepository.add(buildUser2());
    }

    private User buildUser1() {
        final var user1 = new User();
        user1.setUsername("user1");
        user1.setPasswordHash(passwordEncoder.encode("pw1"));
        user1.setFullName("Testy McTesticles");
        user1.setEmailAddress("t.mytesty@example.com");

        return user1;
    }

    private User buildUser2() {
        final var user2 = new User();
        user2.setUsername("user2");
        user2.setPasswordHash(passwordEncoder.encode("pw2"));
        user2.setFullName("John Doe");
        user2.setEmailAddress("john.doe@example.com");

        return user2;
    }

    private void initializeStockArticles() {
        stockArticleRepository.add(buildArticle1());
        stockArticleRepository.add(buildArticle2());
        stockArticleRepository.add(buildArticle3());
        stockArticleRepository.add(buildArticle4());
        stockArticleRepository.add(buildArticle5());
    }

    private StockArticle buildArticle1() {
        final var article1 = new StockArticle();
        article1.setName("Clean Code: A Handbook of Agile Software Craftsmanship. By Robert C. Martin");
        article1.setDescription("""
                Even bad code can function. But if code isn’t clean, it can bring a development organization to its
                knees. Every year, countless hours and significant resources are lost because of poorly written code.
                But it doesn’t have to be that way.
                """);
        article1.setStockQuantity(TEN);

        return article1;
    }

    private StockArticle buildArticle2() {
        final var article2 = new StockArticle();
        article2.setName("Effective Java, Third Edition. By Joshua Bloch");
        article2.setDescription("""
                Since this Jolt-award winning classic was last updated in 2008, the Java programming environment has
                changed dramatically. Java 7 and Java 8 introduced new features and functions including, forEach()
                method in Iterable interface, default and static methods in Interfaces, Functional Interfaces and
                Lambda Expressions, Java Stream API for Bulk Data Operations on Collections, Java Time API, Collection
                API improvements, Concurrency API improvements, and Java IO improvements.
                """);
        article2.setStockQuantity(ONE);

        return article2;
    }

    private StockArticle buildArticle3() {
        final var article3 = new StockArticle();
        article3.setName("Test Driven Development: By Example. By Kent Beck.");
        article3.setDescription("""
                Quite simply, test-driven development is meant to eliminate fear in application development. While some
                fear is healthy (often viewed as a conscience that tells programmers to "be careful!"), the author
                believes that byproducts of fear include tentative, grumpy, and uncommunicative programmers who are
                unable to absorb constructive criticism. When programming teams buy into TDD, they immediately see
                positive results. They eliminate the fear involved in their jobs, and are better equipped to tackle the
                difficult challenges that face them. TDD eliminates tentative traits, it teaches programmers to
                communicate, and it encourages team members to seek out criticism However, even the author admits that
                grumpiness must be worked out individually! In short, the premise behind TDD is that code should be
                continually tested and refactored. Kent Beck teaches programmers by example, so they can painlessly and
                dramatically increase the quality of their work.
                """);
        article3.setStockQuantity(ZERO);

        return article3;
    }

    private StockArticle buildArticle4() {
        final var article4 = new StockArticle();
        article4.setName("Refactoring: Improving the Design of Existing Code, Second Edition. By Martin Fowler.");
        article4.setDescription("""
                For more than twenty years, experienced programmers worldwide have relied on Martin Fowler’s Refactoring
                to improve the design of existing code and to enhance software maintainability, as well as to make
                existing code easier to understand.
                
                This eagerly awaited new edition has been fully updated to reflect crucial changes in the programming
                landscape. Refactoring, Second Edition, features an updated catalog of refactoring's and includes
                JavaScript code examples, as well as new functional examples that demonstrate refactoring without
                classes.
                """);
        article4.setStockQuantity(new BigDecimal("410"));

        return article4;
    }

    private StockArticle buildArticle5() {
        final var article5 = new StockArticle();
        article5.setName("Design Patterns: Elements of Reusable Object-Oriented Software. By Erich Gamma, Richard Helm, Ralph Johnson and John Vlissides");
        article5.setDescription("""
                Capturing a wealth of experience about the design of object-oriented software, four top-notch designers
                present a catalog of simple and succinct solutions to commonly occurring design problems. Previously
                undocumented, these 23 patterns allow designers to create more flexible, elegant, and ultimately
                reusable designs without having to rediscover the design solutions themselves.
                """);
        article5.setStockQuantity(new BigDecimal("3"));

        return article5;
    }
}
