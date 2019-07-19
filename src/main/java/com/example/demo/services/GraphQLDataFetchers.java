package com.example.demo.services;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GraphQLDataFetchers {


    private static List<Map<String, String>> books = Arrays.asList(
            ImmutableMap.of("id", "1",
                    "name", "Harry Potter and the Philosopher's Stone",
                    "pageCount", "223",
                    "authorId", "author-1"),
            ImmutableMap.of("id", "2",
                    "name", "Moby Dick",
                    "pageCount", "635",
                    "authorId", "author-2"),
            ImmutableMap.of("id", "3",
                    "name", "Interview with the vampire",
                    "pageCount", "371",
                    "authorId", "author-3")
    );

    private static List<Map<String, String>> authors = Arrays.asList(
            ImmutableMap.of("id", "author-1",
                    "firstName", "Joanne",
                    "lastName", "Rowling"),
            ImmutableMap.of("id", "author-2",
                    "firstName", "Herman",
                    "lastName", "Melville"),
            ImmutableMap.of("id", "author-3",
                    "firstName", "Anne",
                    "lastName", "Rice")
    );

    private static List<Map<String, String>> event = Arrays.asList(
            ImmutableMap.of("id", "event-1",
                    "start", "12:0",
                    "end", "13:0",
                    "staff","staff-1",
                    "customer","cust-1"
                    ),


            ImmutableMap.of("id", "event-2",
                    "start", "18:0",
                    "end", "19:0",
                    "staff","staff-2",
                    "customer","cust-2"
            ),
            ImmutableMap.of("id", "event-3",
                    "start", "10:0",
                    "end", "13:0",
                    "staff","staff-3",
                    "customer","cust-3"
            )
    );
    private static List<Map<String, String>> customers = Arrays.asList(
            ImmutableMap.of("id", "cust-1",
                    "firstName", "Saraswathy",
                    "lastName", "Moorthy"),
            ImmutableMap.of("id", "cust-2",
                    "firstName", "Kaushik",
                    "lastName", "C"),
            ImmutableMap.of("id", "cust-3",
                    "firstName", "Giri",
                    "lastName", "Raj")
    );
    private static List<Map<String, String>> staff = Arrays.asList(
            ImmutableMap.of("id", "staff-1",
                    "firstName", "Sriram",
                    "lastName", "Umapathy"),
            ImmutableMap.of("id", "staff-2",
                    "firstName", "Lokesh",
                    "lastName", "roa"),
            ImmutableMap.of("id", "staff-3",
                    "firstName", "Saif",
                    "lastName", "Ali")
    );


    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return books
                    .stream()
                    .filter(book -> book.get("id").equals(bookId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getALlBookFetcher() {
        return dataFetchingEnvironment -> {
//            String bookId = dataFetchingEnvironment.getArgument("id");
            return books
                    .stream().collect(Collectors.toList());
        };
    }

    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> book = dataFetchingEnvironment.getSource();
            String authorId = book.get("authorId");
            return authors
                    .stream()
                    .filter(author -> author.get("id").equals(authorId))
                    .findFirst()
                    .orElse(null);
        };
    }
    public DataFetcher getAllAuthorData() {
        return dataFetchingEnvironment -> {
            return authors
                    .stream().collect(Collectors.toList());

        };
    }


    public static List<Map<String,String>>  fetchEventsFromApi()
    {
         List<EventDto> eventList = new FetchDataFromSE().fetch();
        List<Map<String,String>> events = new ArrayList<>();
        System.out.println(eventList.size());
        ArrayList<String> list = new ArrayList();
        ArrayList<String> list2 = new ArrayList();
        for(EventDto event :eventList ){

            for(String staff : event.getProvider())
                list.add(staff);

            for(String cust : event.getConsumer())
                list2.add(cust);

            Map<String, String> map = ImmutableMap.of("id",event.getId(),
                            "start",event.getStartDateTime(),
                            "end",event.getEndDateTimeDateTime(),
                            "staff",list.get(0),
                            "customer",list2.get(0));
            events.add(map);

        }
        return events;
    }



    public DataFetcher getEventById() {
        return dataFetchingEnvironment -> {
            String eventId = dataFetchingEnvironment.getArgument("id");
            return event
                    .stream()
                    .filter(event -> event.get("id").equals(eventId))
                    .findFirst()
                    .orElse(null);
        };
    }
    public DataFetcher getstaff() {
        return dataFetchingEnvironment -> {
            Map<String, String> event = dataFetchingEnvironment.getSource();
            String staffId = event.get("staff");
            return staff
                    .stream()
                    .filter(staff -> staff.get("id").equals(staffId))
                    .findFirst()
                    .orElse(null);
        };
    }
    public DataFetcher getCustomer() {
        return dataFetchingEnvironment -> {
            Map<String, String> event = dataFetchingEnvironment.getSource();
            String cusId = event.get("customer");
            return customers
                    .stream()
                    .filter(customer -> customer.get("id").equals(cusId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getAllEvents() {
        return dataFetchingEnvironment -> {
            return event
                    .stream().collect(Collectors.toList());

        };
    }
    public DataFetcher getAllEventsfromApi() {
        return dataFetchingEnvironment -> {
            return fetchEventsFromApi()
                    .stream().collect(Collectors.toList());

        };
    }
}