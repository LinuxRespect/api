package trainingxyz;/* Copyright © 2023 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;

import org.junit.jupiter.api.Test;

import models.Product;

public class ApiTests {

    @Test
    public void getCategories() {
        String endpoint = "http://localhost:80/api_testing/category/read.php";
        given().when().get(endpoint).then()
            .log()
            .headers()
            .assertThat().statusCode(200)
            .header("Content-Type", "application/json; charset=UTF-8")
            .body("records.size()", equalTo(5))
            .body("records.id", everyItem(notNullValue()));
    }

    @Test
    public void getProducts() {
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        given().queryParam("id", 2).when().get(endpoint).then()
            .log().body()
            .assertThat().statusCode(200)
            .body("id", equalTo("2"));
    }

    @Test
    public void createSerializedProduct() {
        String endpoint = "http://localhost:80/api_testing/product/create.php";
        Product product = new Product(
            "Water better",
            "Blue water",
            12,
            3
        );
        var response = given().body(product).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void getDeserializedProduct() {
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        Product expectedProduct = new Product(
            2,
            "Cross-Back Training Tank",
            "The most awesome phone of 2013!",
            299.00,
            2,
            "Active Wear - Women"
        );
        Product actualProduct = given().
            queryParam("id", "2")
            .when()
            .get(endpoint)
            .as(Product.class);
        assertThat(actualProduct,samePropertyValuesAs(expectedProduct));

    }
}
