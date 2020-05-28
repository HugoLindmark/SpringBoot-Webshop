package com.g09.webshopspringboot.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.g09.webshopspringboot.domain.*;
import com.g09.webshopspringboot.domain.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PopulateDatabase {

    RecordRepository recordRepository;
    UserRepository userRepository;
    OrderPurchaseRepository orderPurchaseRepository;

    @Autowired
    public PopulateDatabase(RecordRepository recordRepository,
                            UserRepository userRepository,
                            OrderPurchaseRepository orderPurchaseRepository) {
        this.recordRepository = recordRepository;
        this.userRepository = userRepository;
        this.orderPurchaseRepository = orderPurchaseRepository;
        fillDb();
    }



    public void fillDb() {

        List<Record> recordList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        //RECORDS
        final Record r1 = new Record("Toto", "Old is new", "toto-1.jpg", 149);
        final Record r2 = new Record("Excalibur", "Live In Brocéliande", "excalibur-1.jpg", 209);
        final Record r3 = new Record("John Hiatt", "Ottawa", "johnhiatt-1.jpg", 199);
        final Record r4 = new Record("Streaplers", "En gång till", "streaplers-1.jpg", 79);
        final Record r5 = new Record("Adele", "25", "adele-25.jpg", 99);
        final Record r6 = new Record("Avicii", "Stories", "avicii-stories.jpg", 225);
        final Record r7 = new Record("David Bowe", "The man who sold the world", "bowe-soldworld.jpg", 175);
        final Record r8 = new Record("The Clash", "London calling", "clash-london.jpg", 50);
        final Record r9 = new Record("Elton John", "Diamonds", "ej-diamonds.jpg", 125);
        final Record r10 = new Record("Elegia", "New order", "elegia-order.jpg", 99);
        final Record r11 = new Record("Eminem", "Encore", "eminem-encore.jpg", 199);
        final Record r12 = new Record("Five Finger Death Punch", "F8", "ffdp-f8.jpg", 50);
        final Record r13 = new Record("Imagine Dragons", "Evolve", "id-evolve.jpg", 99);
        final Record r14 = new Record("Bon Jovi", "2020", "jovi-2020.jpg", 149);
        final Record r15 = new Record("Michael Jackson", "Thriller", "mj-thriller.jpg", 199);
        final Record r16 = new Record("Michael Jackson", "Bad", "mj-bad.jpg", 199);
        final Record r17 = new Record("N.W.A", "Straight outta compton", "nwa-compton.jpg", 99);
        final Record r18 = new Record("Queen", "Greatest hits", "queen-greatest.jpg", 225);
        final Record r19 = new Record("Frank Sinatra", "Ultimate Sinatra", "sinatra-ultimate.jpg", 500_000);
        final Record r20 = new Record("Slipknot", "Iowa", "slipknot-iowa.jpg", 275);

        recordList.add(r1);
        recordList.add(r2);
        recordList.add(r3);
        recordList.add(r4);
        recordList.add(r5);
        recordList.add(r6);
        recordList.add(r7);
        recordList.add(r8);
        recordList.add(r9);
        recordList.add(r10);
        recordList.add(r11);
        recordList.add(r12);
        recordList.add(r13);
        recordList.add(r14);
        recordList.add(r15);
        recordList.add(r16);
        recordList.add(r17);
        recordList.add(r18);
        recordList.add(r19);
        recordList.add(r20);

        recordList.forEach(record -> recordRepository.save(record));

        //USERS
        final User u1 = new User("mrtest", "Test", "Testsson", "losen", Role.CUSTOMER);
        final User u2 = new User("jacob1", "Jacob", "Andersson", "password", Role.CUSTOMER);
        final User u3 = new User("sandra85", "Sandra", "Berg", "mittlosen", Role.CUSTOMER);
        final User u4 = new User("Larssa2000", "Lars", "Lerin", "Junior123", Role.PREMIUM);
        final User u5 = new User("HockeyDanne", "Daniel", "Sedin", "qwerty", Role.ADMIN);
        final User u6 = new User("sa", "Förnamn", "Efternamn", "sa", Role.PREMIUM);
        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        userList.add(u4);
        userList.add(u5);
        userList.add(u6);

        //userList.forEach(user -> em.persist(user));
        userList.forEach(user -> userRepository.save(user));


        // ORDERINFO
        List<OrderInfo> orderInfoHistoryList1 = new ArrayList<>();
        List<OrderInfo> orderInfoHistoryList2 = new ArrayList<>();
        List<OrderInfo> orderInfoHistoryList3 = new ArrayList<>();

        orderInfoHistoryList1.add(new OrderInfo(r1, 6));
        orderInfoHistoryList1.add(new OrderInfo(r2, 3000));
        orderInfoHistoryList2.add(new OrderInfo(r2, 150));
        orderInfoHistoryList2.add(new OrderInfo(r4, 15));
        orderInfoHistoryList2.add(new OrderInfo(r3, 15));
        orderInfoHistoryList3.add(new OrderInfo(r1, 3355));

        //ORDER
        addOrder(u4, orderInfoHistoryList1, LocalDate.of(2020, 1, 3), 627894);
        addOrder(u1, orderInfoHistoryList2, LocalDate.of(2019, 11, 15), 35520);
        addOrder(u2, orderInfoHistoryList3, LocalDate.of(2019, 8, 27), 499895);


    }

    private void addOrder(User user, List<OrderInfo> itemList, LocalDate date, int orderTotal) {
        OrderPurchase order = new OrderPurchase(user,itemList,date, orderTotal);
        for (OrderInfo oi: itemList) {
            oi.setOrder(order);
        }
        order.setItems(itemList);
        user.getOrders().add(order);
        order.setUser(user);
        orderPurchaseRepository.save(order);
    }
}
