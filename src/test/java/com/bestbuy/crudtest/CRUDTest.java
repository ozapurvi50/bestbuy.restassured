package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CRUDTest {

    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }




    static String name = "Priyaa";
    static String type = "SoftGood";
    static double price = 5.69;
    static String upc = "567891";
    static int shipping = 0;
    static String description = "Very good quality";
    static String manufacturer = "Energizer";
    static String model ="MN2400B4A";
    static String url = "string";
    static int id;

    @Test
    public void test001(){
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post();
        id =response.then().extract().path("id");
        response.then().log().body();
    }

    @Test
    public void test002() {

        Response response= given()
                .when()
                .get("/" + id);
        response.then().statusCode(200);


    }

    @Test
    public void test003() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name + "_123");
        productPojo.setType(type + "_good");


        Response response = given()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .patch("/" + id);
        response.then().statusCode(200);
        response.then().log().body();
    }

    @Test
    public void test004() {
        given()
                .when()
                .delete("/" + id)
                .then()
                .statusCode(200);

    }


}
