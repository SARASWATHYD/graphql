package com.example.demo.services;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import java.awt.print.Book;
import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class GraphQLProvider {


    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    private GraphQL graphQL;

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("static/schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL =  GraphQL.newGraphQL(graphQLSchema).build();
        GraphQLDataFetchers.fetchEventsFromApi();
        
//        ExecutionResult executionResult  = graphQL.execute("{bookById(id: book-2)}");
//        System.out.println(executionResult.getData().toString());
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher())
                        .dataFetcher("allBook",graphQLDataFetchers.getALlBookFetcher())
                        .dataFetcher("allAuthor",graphQLDataFetchers.getAllAuthorData())
                        .dataFetcher("specificEvent",graphQLDataFetchers.getEventById())
                        .dataFetcher("allEvents",graphQLDataFetchers.getAllEvents())
                        .dataFetcher("allEventsApi",graphQLDataFetchers.getAllEventsfromApi())

                )

                .type(newTypeWiring("Book")
                        .dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher()))
                .type(newTypeWiring("EventDto")
                        .dataFetcher("staff", graphQLDataFetchers.getstaff()))
                .type(newTypeWiring("EventDto")
                        .dataFetcher("customer", graphQLDataFetchers.getCustomer()))


                .build();
    }

    @Bean
    public GraphQL graphQL() {
    	System.out.println(graphQL.execute("{bookById(id :2){id name pageCount author{firstName lastName}}}", Book.class));
    	System.out.println(graphQL.execute("{bookById(id :1){id name author{firstName lastName}}}", Book.class));
        return graphQL;
    }

}