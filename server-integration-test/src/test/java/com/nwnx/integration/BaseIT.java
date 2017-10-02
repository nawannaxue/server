package com.nwnx.integration;

import com.nwnx.integration.dto.UserRequest;
import com.nwnx.integration.dto.UserResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

public class BaseIT {
    private static final Logger logger = LoggerFactory.getLogger(BaseIT.class);

    private static Client client;
    private static WebTarget usersTarget;

    @BeforeAll
    public static void beforeAll() throws Exception {
        logger.info("beforeALL(): called");

        client = ClientCreator.create();
        usersTarget = client.target("http://localhost:8080/server-web/api/users");
    }

    @AfterAll
    public static void afterAll() {
        logger.info("afterAll(): closing client");
        client.close();
        logger.info("afterAll(): closed client");
    }


    @Test
    void createUser() {
        UserRequest user = new UserRequest();
        user.setName("name");
        user.setEmail("abc@email.com");
        user.setFullName("full name");
        user.setPassword("password");

        UserResponse response = usersTarget.request().post(Entity.json(user), UserResponse.class);
        logger.info(response.toString());
    }
}
