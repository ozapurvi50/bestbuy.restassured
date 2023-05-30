package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.core.IsEqual;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.equalTo;

public class ProductAssertionTest extends TestBase {

    //for asssertion we use body after then method it will give you body method
    static ValidatableResponse response; //because we are using then it is validatable

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
        //response.log().all(); to print all data
    }

    @Test
    //1. Verify the if the total is equal to 51957
    public void test001() {
        response.body("total", equalTo(51957));
    }

    @Test
    // 2. Verify the if the stores of limit is equal to 10
    public void test002() {
        response.body("limit", equalTo(10));
    }

    //3. Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack))
    @Test
    public void test003() {
        response.body("data.name", hasItem("Duracell - AAA Batteries (4-Pack)"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4- Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))
    @Test
    public void test4() {
        response.body("data.name", hasItems("Duracell - AA 1.5V CopperTop Batteries (4-Pack)", "Duracell - AA Batteries (8-Pack)", "Energizer - MAX Batteries AA (4-Pack)"));
    }

    //5. Verify the productId=150115 inside categories of the third name is “Household Batteries”
    @Test
    public void test005() {
        response.body("data[3].id", IsEqual.equalTo(150115));

    }
    //6. Verify the categories second name = “Housewares” of productID = 333179
    @Test
    public void test6() {
        response.body("data[3].categories[1].name", equalTo("Housewares"))
                .body("data[8].id",equalTo(333179));
    }

    //7. Verify the price = 4.99 of forth product
    @Test
    public void test7() {
        response.body("data[3].price",anything("4.99"));}
    //8. Verify the Product name = Duracell - D Batteries (4-Pack) of 6th product
        @Test
        public void test8() {
            response.body("data[5].name",equalTo("Duracell - D Batteries (4-Pack)"));
        }
    //9. Verify the ProductId = 333179 for the 9th product
    @Test
    public void test9() {
        response.body("data[8].id",equalTo(333179));
    }
    //10. Verify the prodctId = 346575 have 5 categories
    @Test
    public void test10() {
        response.body("data[9].categories.size",equalTo(5));
    }
}
