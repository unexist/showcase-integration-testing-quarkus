/**
 * @package Showcase
 * @file
 * @copyright 2022 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo;

import io.restassured.RestAssured;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.URL;

import static io.restassured.RestAssured.given;

@ExtendWith(ArquillianExtension.class)
public class TestTodoResource {

    @ArquillianResource
    URL url;

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive archive =  ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackage("dev.unexist.showcase.*");

        return archive;
    }

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = this.url.toString();
    }

    @Test
    @RunAsClient
    public void testGetId() {
        given()
                .when().get("/todo/1234")
                .then()
                .statusCode(404);
    }
}
