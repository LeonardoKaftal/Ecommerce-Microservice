package com.ecommerce.inventoryservice.config;

import com.ecommerce.inventoryservice.entity.Producer;
import com.ecommerce.inventoryservice.entity.Product;
import com.ecommerce.inventoryservice.entity.ProductCategory;
import com.ecommerce.inventoryservice.repository.ProducerRepository;
import com.ecommerce.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
@RequiredArgsConstructor
public class SampleDataRunner implements CommandLineRunner {

    private final ProductRepository productRepository;

    private final ProducerRepository producerRepository;
    Random random = new Random();


    @Override
    public void run(String... args) {
        // Sample producers
        List<Producer> producers = Arrays.asList(
                Producer.builder().name("Apple").products(new ArrayList<>()).build(),
                Producer.builder().name("Samsung").products(new ArrayList<>()).build(),
                Producer.builder().name("Google").products(new ArrayList<>()).build(),
                Producer.builder().name("OnePlus").products(new ArrayList<>()).build(),
                Producer.builder().name("Motorola").products(new ArrayList<>()).build(),
                Producer.builder().name("ASUS").products(new ArrayList<>()).build(),
                Producer.builder().name("Microsoft").products(new ArrayList<>()).build(),
                Producer.builder().name("TCL").products(new ArrayList<>()).build(),
                Producer.builder().name("Dell").products(new ArrayList<>()).build(),
                Producer.builder().name("HP").products(new ArrayList<>()).build(),
                Producer.builder().name("Lenovo").products(new ArrayList<>()).build(),
                Producer.builder().name("Acer").products(new ArrayList<>()).build(),
                Producer.builder().name("MSI").products(new ArrayList<>()).build(),
                Producer.builder().name("Gigabyte").products(new ArrayList<>()).build(),
                Producer.builder().name("Sony").products(new ArrayList<>()).build(),
                Producer.builder().name("Canon").products(new ArrayList<>()).build(),
                Producer.builder().name("Nikon").products(new ArrayList<>()).build(),
                Producer.builder().name("Fujifilm").products(new ArrayList<>()).build(),
                Producer.builder().name("Razer").products(new ArrayList<>()).build(),
                Producer.builder().name("Philips").products(new ArrayList<>()).build(),
                Producer.builder().name("Nike").products(new ArrayList<>()).build(),
                Producer.builder().name("Adidas").products(new ArrayList<>()).build(),
                Producer.builder().name("Dr. Martens").products(new ArrayList<>()).build(),
                Producer.builder().name("Converse").products(new ArrayList<>()).build(),
                Producer.builder().name("Birkenstock").products(new ArrayList<>()).build(),
                Producer.builder().name("Timberland").products(new ArrayList<>()).build(),
                Producer.builder().name("Ugg").products(new ArrayList<>()).build(),
                Producer.builder().name("Vans").products(new ArrayList<>()).build(),
                Producer.builder().name("New Balance").products(new ArrayList<>()).build(),
                Producer.builder().name("Hoka One One").products(new ArrayList<>()).build(),
                Producer.builder().name("FromSoftware").products(new ArrayList<>()).build(),
                Producer.builder().name("Nintendo").products(new ArrayList<>()).build(),
                Producer.builder().name("Rockstar North").products(new ArrayList<>()).build(),
                Producer.builder().name("Rockstar Studios").products(new ArrayList<>()).build(),
                Producer.builder().name("Guerrilla Games").products(new ArrayList<>()).build(),
                Producer.builder().name("Santa Monica Studio").products(new ArrayList<>()).build(),
                Producer.builder().name("Sucker Punch Productions").products(new ArrayList<>()).build(),
                Producer.builder().name("CD Projekt Red").products(new ArrayList<>()).build(),
                Producer.builder().name("Mojang Studios").products(new ArrayList<>()).build(),
                Producer.builder().name("Psyonix").products(new ArrayList<>()).build()
        );


        // Sample products
        List<Product> products = Arrays.asList(
                Product.builder()
                        .name("Apple iPhone 14 Pro Max")
                        .price(new BigDecimal("1099"))
                        .description("High-end smartphone with a powerful A16 Bionic chip, triple-lens camera system, and long battery life.")
                        .productCategory(ProductCategory.PHONES)
                        .producer(producers.get(0)) // Apple
                        .build(),
                Product.builder()
                        .name("Samsung Galaxy S23 Ultra")
                        .price(new BigDecimal("1188"))
                        .description("Powerful Android phone with a quad-lens camera system, large S Pen stylus, and long-lasting battery.")
                        .productCategory(ProductCategory.PHONES)
                        .producer(producers.get(1)) // Samsung
                        .build(),
                Product.builder()
                        .name("Google Pixel 7 Pro")
                        .price(new BigDecimal("899"))
                        .description("Affordable flagship phone with excellent camera performance, clean Android experience, and Tensor 2 chip.")
                        .productCategory(ProductCategory.PHONES)
                        .producer(producers.get(2)) // Google
                        .build(),
                Product.builder()
                        .name("OnePlus 11 5G")
                        .price(new BigDecimal("699"))
                        .description("Powerful and fast phone with a smooth 120Hz display, fast charging, and a clean user interface.")
                        .productCategory(ProductCategory.PHONES)
                        .producer(producers.get(3)) // OnePlus
                        .build(),
                Product.builder()
                        .name("Motorola Moto G Stylus (2023)")
                        .price(new BigDecimal("299"))
                        .description("Budget-friendly phone with a built-in stylus for note-taking and drawing, large display, and decent battery life.")
                        .productCategory(ProductCategory.PHONES)
                        .producer(producers.get(4)) // Motorola
                        .build(),
                Product.builder()
                        .name("ASUS ROG Phone 6 Pro")
                        .price(new BigDecimal("999"))
                        .description("Gaming-focused phone with a powerful processor, high refresh rate display, and various gaming-specific features.")
                        .productCategory(ProductCategory.PHONES)
                        .producer(producers.get(5)) // ASUS
                        .build(),
                // Add other products here...
                Product.builder()
                        .name("Apple iPhone 14 Plus")
                        .description("Larger version of the iPhone 14 with a longer battery life, but similar features to the standard iPhone 14.")
                        .productCategory(ProductCategory.PHONES)
                        .producer(producers.get(0)) // Apple
                        .build(),
                Product.builder()
                        .name("Microsoft Surface Duo 2")
                        .description("Unique foldable phone with two separate screens for multitasking and productivity.")
                        .productCategory(ProductCategory.PHONES)
                        .producer(producers.get(6)) // Microsoft
                        .build(),
                Product.builder()
                        .name("TCL 30 XE 5G")
                        .description("Affordable 5G phone with a large display, decent battery life, and basic features.")
                        .productCategory(ProductCategory.PHONES)
                        .producer(producers.get(7)) // TCL
                        .build(),
                Product.builder()
                        .name("Doro 8080")
                        .price(new BigDecimal("1999"))
                        .description("Powerful desktop computer with M1 Max or M1 Ultra chip, ideal for creative professionals and demanding tasks.")
                        .productCategory(ProductCategory.COMPUTERS)
                        .producer(producers.get(0)) // Apple
                        .build(),
                Product.builder()
                        .name("Microsoft Surface Studio 2+")
                        .price(new BigDecimal("4499"))
                        .description("All-in-one desktop computer with a large touchscreen display, powerful performance, and a unique tilting design.")
                        .productCategory(ProductCategory.COMPUTERS)
                        .producer(producers.get(6)) // Microsoft
                        .build(),
                Product.builder()
                        .name("Dell XPS Desktop")
                        .price(new BigDecimal("899"))
                        .description("Customizable desktop computer available in various configurations with different processors, graphics cards, and storage options.")
                        .productCategory(ProductCategory.COMPUTERS)
                        .producer(producers.get(8)) // Dell
                        .build(),
                Product.builder()
                        .name("HP Pavilion Desktop")
                        .price(new BigDecimal("599"))
                        .description("Affordable desktop computer for everyday tasks like browsing the web, checking email, and using productivity software.")
                        .productCategory(ProductCategory.COMPUTERS)
                        .producer(producers.get(9)) // HP
                        .build(),
                Product.builder()
                        .name("Lenovo IdeaCentre AIO 3")
                        .price(new BigDecimal("499"))
                        .description("All-in-one desktop computer with a compact design, ideal for small spaces and basic computing needs.")
                        .productCategory(ProductCategory.COMPUTERS)
                        .producer(producers.get(10)) // Lenovo
                        .build(),

                Product.builder()
                        .name("Apple MacBook Air M2")
                        .price(new BigDecimal("1199"))
                        .description("Thin and light laptop with a powerful M2 chip, long battery life, and a sleek design.")
                        .productCategory(ProductCategory.LAPTOPS)
                        .producer(producers.get(0)) // Apple
                        .build(),
                Product.builder()
                        .name("Microsoft Surface Laptop Studio")
                        .price(new BigDecimal("1599"))
                        .description("Unique laptop with a dynamic hinge that allows it to be used in various positions, including laptop, tablet, and studio mode.")
                        .productCategory(ProductCategory.LAPTOPS)
                        .producer(producers.get(6)) // Microsoft
                        .build(),
                Product.builder()
                        .name("Dell XPS 13")
                        .price(new BigDecimal("999"))
                        .description("Premium laptop with a beautiful display, long battery life, and powerful performance.")
                        .productCategory(ProductCategory.LAPTOPS)
                        .producer(producers.get(8)) // Dell
                        .build(),
                Product.builder()
                        .name("HP Spectre x360")
                        .price(new BigDecimal("1299"))
                        .description("Convertible laptop with a 360-degree hinge that allows it to be used in laptop and tablet mode.")
                        .productCategory(ProductCategory.LAPTOPS)
                        .producer(producers.get(9)) // HP
                        .build(),
                Product.builder()
                        .name("Asus Chromebook Flip C434")
                        .price(new BigDecimal("279"))
                        .description("Affordable Chromebook with a long battery life, perfect for basic tasks like browsing the web, checking email, and using cloud-based applications.")
                        .productCategory(ProductCategory.LAPTOPS)
                        .producer(producers.get(19)) // Asus
                        .build(),
                // Add other products here...
                Product.builder()
                        .name("Acer Predator Orion 3000")
                        .price(new BigDecimal("1299"))
                        .description("Powerful gaming desktop computer with an NVIDIA GeForce RTX 3070 graphics card, ideal for demanding games and applications.")
                        .productCategory(ProductCategory.COMPUTERS)
                        .producer(producers.get(11)) // Acer
                        .build(),
                Product.builder()
                        .name("MSI MEG Trident X")
                        .price(new BigDecimal("3999"))
                        .description("Compact and powerful gaming desktop computer with an innovative design, perfect for gamers who want a high-performance machine in a small footprint.")
                        .productCategory(ProductCategory.COMPUTERS)
                        .producer(producers.get(12)) // MSI
                        .build(),
                Product.builder()
                        .name("Gigabyte Aorus Elite AE")
                        .price(new BigDecimal("1799"))
                        .description("Customizable desktop computer with a stylish design and powerful components, suitable for gaming, content creation, and other demanding tasks.")
                        .productCategory(ProductCategory.COMPUTERS)
                        .producer(producers.get(13)) // Gigabyte
                        .build(),
                Product.builder()
                        .name("Samsung Galaxy Book 2 Pro 360")
                        .price(new BigDecimal("1099"))
                        .description("Premium convertible laptop with a stunning AMOLED display, S Pen stylus support, and long battery life.")
                        .productCategory(ProductCategory.LAPTOPS)
                        .producer(producers.get(1)) // Samsung
                        .build(),
                Product.builder()
                        .name("Lenovo ThinkPad X1 Carbon")
                        .price(new BigDecimal("1499"))
                        .description("Business-oriented laptop known for its durability, security features, and comfortable keyboard.")
                        .productCategory(ProductCategory.LAPTOPS)
                        .producer(producers.get(10)) // Lenovo
                        .build(),
                Product.builder()
                        .name("Razer Blade 15")
                        .price(new BigDecimal("2799"))
                        .description("High-end gaming laptop with a powerful processor, high refresh rate display, and sleek design.")
                        .productCategory(ProductCategory.LAPTOPS)
                        .producer(producers.get(19)) // Razer
                        .build(),
                Product.builder()
                        .name("Apple MacBook Pro M1 Pro")
                        .price(new BigDecimal("2499"))
                        .description("Powerful and professional laptop with a long battery life, M1 Pro chip, and a variety of ports.")
                        .productCategory(ProductCategory.LAPTOPS)
                        .producer(producers.get(0)) // Apple
                        .build(),
                Product.builder()
                        .name("Google Pixelbook Go")
                        .price(new BigDecimal("349"))
                        .description("Affordable Chromebook with a long battery life, lightweight design, and Chrome OS for a fast and secure experience.")
                        .productCategory(ProductCategory.LAPTOPS)
                        .producer(producers.get(2)) // Google
                        .build(),
                // Add other products here...
                Product.builder()
                        .name("Samsung Galaxy SmartTag+")
                        .price(new BigDecimal("39.99"))
                        .description("Item tracker that helps locate lost items using Bluetooth and Ultra Wideband technology.")
                        .productCategory(ProductCategory.SMART_HOME)
                        .producer(producers.get(1)) // Samsung
                        .build(),
                Product.builder()
                        .name("iRobot Roomba i3+")
                        .price(new BigDecimal("599.99"))
                        .description("Robotic vacuum cleaner with self-emptying base for effortless cleaning.")
                        .productCategory(ProductCategory.SMART_HOME)
                        .producer(producers.get(39)) // iRobot
                        .build(),
                Product.builder()
                        .name("Nike Air Force 1 Low")
                        .price(new BigDecimal("100"))
                        .description("Iconic and versatile low-top sneaker, available in various colors and materials.")
                        .productCategory(ProductCategory.FOOTWEAR)
                        .producer(producers.get(20)) // Nike
                        .build(),
                Product.builder()
                        .name("Adidas Ultraboost 22")
                        .price(new BigDecimal("180"))
                        .description("Performance running shoe with a comfortable and responsive boost midsole.")
                        .productCategory(ProductCategory.FOOTWEAR)
                        .producer(producers.get(21)) // Adidas
                        .build(),
                Product.builder()
                        .name("Dr. Martens 1460 8-Eye Boot")
                        .price(new BigDecimal("140"))
                        .description("Durable and stylish boot with a timeless design, known for its comfort and durability.")
                        .productCategory(ProductCategory.FOOTWEAR)
                        .producer(producers.get(22)) // Dr. Martens
                        .build(),
                Product.builder()
                        .name("Converse Chuck Taylor All-Stars")
                        .price(new BigDecimal("55"))
                        .description("Classic canvas sneaker with a timeless design and cultural significance.")
                        .productCategory(ProductCategory.FOOTWEAR)
                        .producer(producers.get(23)) // Converse
                        .build(),
                Product.builder()
                        .name("Birkenstock Arizona Sandal")
                        .price(new BigDecimal("130"))
                        .description("Comfortable and stylish sandal with a supportive cork footbed, ideal for casual wear.")
                        .productCategory(ProductCategory.FOOTWEAR)
                        .producer(producers.get(24)) // Birkenstock
                        .build(),
                Product.builder()
                        .name("Timberland 6-Inch Boot")
                        .price(new BigDecimal("180"))
                        .description("Waterproof and durable boot, ideal for outdoor activities and everyday wear.")
                        .productCategory(ProductCategory.FOOTWEAR)
                        .producer(producers.get(25)) // Timberland
                        .build(),
                Product.builder()
                        .name("Ugg Classic Mini Boot")
                        .price(new BigDecimal("140"))
                        .description("Cozy and stylish boot made from sheepskin, known for its warmth and comfort.")
                        .productCategory(ProductCategory.FOOTWEAR)
                        .producer(producers.get(26)) // Ugg
                        .build(),
                Product.builder()
                        .name("Vans Old Skool")
                        .price(new BigDecimal("70"))
                        .description("Low-top skate shoe with a classic design and customizable options.")
                        .productCategory(ProductCategory.FOOTWEAR)
                        .producer(producers.get(27)) // Vans
                        .build(),
                Product.builder()
                        .name("New Balance 574")
                        .price(new BigDecimal("80"))
                        .description("Versatile and comfortable lifestyle sneaker, known for its classic design and supportive fit.")
                        .productCategory(ProductCategory.FOOTWEAR)
                        .producer(producers.get(28)) // New Balance
                        .build(),
                Product.builder()
                        .name("Hoka One One Bondi 8")
                        .price(new BigDecimal("200"))
                        .description("Maximalist running shoe with a highly cushioned midsole, ideal for long distances and comfort.")
                        .productCategory(ProductCategory.FOOTWEAR)
                        .producer(producers.get(29)) // Hoka One One
                        .build(),
                Product.builder()
                        .name("Elden Ring")
                        .price(new BigDecimal("59.99"))
                        .description("Open-world action RPG set in a fantasy world, known for its challenging gameplay and exploration.")
                        .productCategory(ProductCategory.VIDEOGAMES)
                        .producer(producers.get(30)) // FromSoftware
                        .build(),
                Product.builder()
                        .name("The Legend of Zelda: Breath of the Wild")
                        .price(new BigDecimal("59.99"))
                        .description("Open-world action-adventure game with exploration, puzzle-solving, and combat elements.")
                        .productCategory(ProductCategory.VIDEOGAMES)
                        .producer(producers.get(31)) // Nintendo
                        .build(),
                Product.builder()
                        .name("Grand Theft Auto V")
                        .price(new BigDecimal("29.99"))
                        .description("Open-world action-adventure game set in a fictionalized Los Angeles, known for its mature themes and online multiplayer.")
                        .productCategory(ProductCategory.VIDEOGAMES)
                        .producer(producers.get(32)) // Rockstar North
                        .build(),
                Product.builder()
                        .name("Red Dead Redemption 2")
                        .price(new BigDecimal("39.99"))
                        .description("Open-world action-adventure game set in the wild west, known for its immersive story, detailed world, and online multiplayer.")
                        .productCategory(ProductCategory.VIDEOGAMES)
                        .producer(producers.get(33)) // Rockstar Studios
                        .build(),
                Product.builder()
                        .name("Super Mario Odyssey")
                        .price(new BigDecimal("59.99"))
                        .description("3D platformer game featuring Mario exploring various themed kingdoms, known for its creative level design and lighthearted tone.")
                        .productCategory(ProductCategory.VIDEOGAMES)
                        .producer(producers.get(31)) // Nintendo
                        .build(),
                Product.builder()
                        .name("Horizon Zero Dawn")
                        .price(new BigDecimal("19.99"))
                        .description("Open-world action RPG set in a post-apocalyptic world dominated by machines, known for its unique story and beautiful visuals.")
                        .productCategory(ProductCategory.VIDEOGAMES)
                        .producer(producers.get(34)) // Guerrilla Games
                        .build(),
                Product.builder()
                        .name("God of War (2018)")
                        .price(new BigDecimal("19.99"))
                        .description("Action-adventure game featuring Kratos, the God of War, in a new Norse mythology setting, known for its intense combat and emotional story.")
                        .productCategory(ProductCategory.VIDEOGAMES)
                        .producer(producers.get(35)) // Santa Monica Studio
                        .build(),
                Product.builder()
                        .name("Ghost of Tsushima")
                        .price(new BigDecimal("19.99"))
                        .description("Open-world action-adventure game set in feudal Japan, following a samurai on a quest for revenge, known for its beautiful environments and historical setting.")
                        .productCategory(ProductCategory.VIDEOGAMES)
                        .producer(producers.get(36)) // Sucker Punch Productions
                        .build(),
                Product.builder()
                        .name("The Witcher 3: Wild Hunt")
                        .price(new BigDecimal("29.99"))
                        .description("Open-world action RPG based on the Witcher fantasy novels, known for its rich story, branching narrative, and engaging side quests.")
                        .productCategory(ProductCategory.VIDEOGAMES)
                        .producer(producers.get(37)) // CD Projekt Red
                        .build(),
                Product.builder()
                        .name("Minecraft")
                        .price(new BigDecimal("29.99"))
                        .description("Open-world sandbox game where players can build, explore, and create using blocks in a procedurally generated world.")
                        .productCategory(ProductCategory.VIDEOGAMES)
                        .producer(producers.get(38)) // Mojang Studios
                        .build(),
                Product.builder()
                        .name("Rocket League")
                        .price(new BigDecimal("19.99"))
                        .description("Sports video game where players use rocket-powered vehicles to hit a giant soccer ball into the opponent's goal.")
                        .productCategory(ProductCategory.VIDEOGAMES)
                        .producer(producers.get(39)) // Psyonix
                        .build()
        );

        products = products
                .stream()
                .peek(product -> {
                    product.setQuantity(random.nextInt(10));
                    product.setSkuCode(UUID.randomUUID().toString());
                })
                .toList();

        products.forEach(product -> {
            Producer producer = product.getProducer();
            if (producer != null) {
                List<Product> producerProducts = producer.getProducts();
                producerProducts.add(product);
                producer.setProducts(producerProducts);
            }
        });

        producerRepository.saveAll(producers);
        productRepository.saveAll(products);
    }
}

