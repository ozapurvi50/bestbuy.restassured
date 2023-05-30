package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class StoresExtractionTest {

    //for assertion we use body after then method it will give you body method
    static ValidatableResponse response; //because we are using then it is validatable

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);

    }

    // 1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("The limit is : " + limit);
    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("The total is : " + total);
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");
        System.out.println("The 5th store name is : " + name);
    }

    //4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> storeName = response.extract().path("data.name");
        System.out.println("The all store name is : " + storeName);
    }

    //5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<String> id = response.extract().path("data.id");
        System.out.println("The all store id is : " + id);
    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        List<String> data = response.extract().path("data");
        int data1 = data.size();
        System.out.println("The all store data list size is : " + data1);
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<String> value = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("The value of the store St Cloud is : " + value);
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<HashMap<String, ?>> storeName = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("The address of store is : " + storeName);
    }

    //9. Get all the services of 8th store
    @Test
    public void test009() {
        List<String> services = response.extract().path("data[7].services");
        System.out.println("Get all the services of 8th store : " + services);
    }

    //10. Get store services of the store where service name = Windows Store
    @Test
    public void test010() {
        List<LinkedHashMap> services = response.extract().path("data.findAll{it.name='Window Store'}.services");
        System.out.println("Get all the services of Window store : " + services);
    }

    //11. Get all the storeId of all the stores
    @Test
    public void test011() {
        List<Integer> ids = response.extract().path("data.services.id");
        System.out.println("Get all the storeId services of all stores : " + ids);
    }

    //12. Get id of all the store
    @Test
    public void test012() {
        List<Integer> ids = response.extract().path("data.id");
        System.out.println("Get all the storeId of all stores : " + ids);
    }

    //13. Find the store names Where state = ND
    @Test
    public void test013() {
        List<HashMap<String, ?>> productListMap = response.extract().path("data.findAll{it.state == 'MN'}");
        System.out.println("Find the store names where state is MN :" + productListMap);

    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<HashMap<String, ?>> services = response.extract().path("data.findAll{it.name == 'Rochester'}");
        HashMap<String, ?> productMap = services.get(0);
        int number = (Integer) productMap.get("number");
        System.out.println("The number of services for Rochester store : " + number);
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {
        List<LinkedHashMap> createAt = response.extract().path("data.findAll{it.services='Window Store'}.createdAt");
        System.out.println("Get all the services of Window store : " + createAt);
    }

    //16. Find the name of all services Where store name = “Fargo”

    @Test
    public void test016() {
        List<HashMap<String, ?>> services = response.extract().path("data.findAll{it.name == 'Fargo'}.services.storeservices");
        System.out.println("The number of services for Fargo store : " + services);
    }

    //17. Find the zip of all the store
    @Test
    public void test017() {
        List<String> zip = response.extract().path("data.zip");
        System.out.println("Get all the storeId services of all stores : " + zip);
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test018() {
        List<String> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("The zip of Roseville is : " + zip);
    }

    //19. Find the store services details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        List<String> storeservice = response.extract().path("data.services.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("The store services of Magnolia Home Theater  : " + storeservice);
    }

    //20. Find the lat of all the stores
    @Test
    public void test20() {
        List<HashMap<String, ?>> lat = response.extract().path("data.lat");
        System.out.println("Created At : " + lat);
    }

}
