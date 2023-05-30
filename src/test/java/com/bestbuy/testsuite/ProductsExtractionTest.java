package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {

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


    //1. Extract the limit
    @Test
    public void test01() {

        int limit = response.extract().path("limit");
        System.out.println("-------StartingTest---------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("-------End of Test---------");
    }

    //2. Extract the total
    @Test
    public void test02() {

        int total = response.extract().path("total");
        System.out.println("-------StartingTest---------");
        System.out.println("The value of total is : " + total);
        System.out.println("-------End of Test---------");
    }

    //3. Extract the name of 5th product
    @Test
    public void test03() {
        String name = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th store is : " + name);
        System.out.println("------------------End of Test---------------------------");

    }

    //4. Extract the names of all the products
    @Test
    public void test04() {
        List<String> listOfNames = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfNames);
        System.out.println("------------------End of Test---------------------------");

    }

    //5. Extract the productId of all the products
    @Test
    public void test05() {
        List<Integer> listOfIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }

    //6. Print the size of the data list
    @Test
    public <Objects> void test06() {
        List<Objects> listOfdata = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data is : " + listOfdata.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test07() {
        List<HashMap<String, ?>> productname = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the store where product name = Energizer - MAX Batteries AA (4-Pack): " + productname);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test08() {
        // List<HashMap<String,?>>addressofstorename=response.extract().path("data.findAll{it.addeess == ' Energizer - N Cell E90 Batteries (2-Pack)'}");
        List<String> addressofproductname = response.extract().path("data.findAll{it.name == ' Energizer - N Cell E90 Batteries (2-Pack)'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The address of the store where store name = Energizer - N Cell E90 Batteries (2-Pack): " + addressofproductname);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the categories of 8th products
    @Test
    public void test09() {

        List<HashMap<String, ?>> productCategories = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  all the services of 8th store : " + productCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get categories of the store where product id = 150115
    @Test
    public void test010() {

        List<HashMap<String, ?>> productCategories = response.extract().path("data.findAll{it.id==150115}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Get categories of the store where product id = 150115 " + productCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all the descriptions of all the products
    @Test
    public void test011() {
        List<Integer> listOfAlldescriptions = response.extract().path("data.description");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the storeId of all the store : " + listOfAlldescriptions);
        System.out.println("------------------End of Test---------------------------");

    }

    //12. Get id of all the all categories of all the products
    @Test
    public void test012() {
        List<String> ids = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of all the all categories of all the products : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the product names Where type = HardGood
    @Test
    public void test013() {

        List<String> productName = response.extract().path("data.findAll{it.type='HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store names Where state 'ND' are : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of categories for the product where product name = Duracell - AA
    //1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test014() {

        List<Integer> totalNoOfcategories = response.extract().path("data.findAll{it.name='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the Total number of services for the store where store name = Rochester : " + totalNoOfcategories.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all products whose price < 5.49
    @Test
    public void test015() {
        List<String> listOfcreatedAt = response.extract().path("data.findAll{it.price=='5.49'}.createdAt");
        // List<?> createdAtWindowsStore = response.extract().path("data.findAll{it.name=='Windows Store'}.services.find{it.createdAt}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the createdAt for all products whose price < 5.49 " + listOfcreatedAt);
        System.out.println("------------------End of Test---------------------------");

    }


    //16. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4- Pack)”
    @Test
    public void test016() {
        List<HashMap<String, ?>> listOfcategories = response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4- Pack)'}.categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  Find the name of  categories Where product name = “Energizer - MAX Batteries AA (4- Pack)" + listOfcategories);
        System.out.println("------------------End of Test---------------------------");

    }

    //17. Find the manufacturer of all the products
    @Test
    public void test017() {
        List<HashMap<String, ?>> listOfmanufacturer = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  Find the name of  categories Where product name = “Energizer - MAX Batteries AA (4- Pack)" + listOfmanufacturer);
        System.out.println("------------------End of Test---------------------------");

    }

    //18. Find the imge of products whose manufacturer is = Energizer
    @Test
    public void test018() {
        List<String> imageofproduct = response.extract().path("data.findAll{it.manufacturer=='Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the zip of store name = Roseville " + imageofproduct);
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test019() {
        List<String> createdAt = response.extract().path("data.findAll{it.price > 5.99}.categories.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all categories products whose price > 5.99 : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the uri of all the product
    @Test
    public void test020() {
        List<Integer> listOfurl = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the lat of all the stores" + listOfurl);
        System.out.println("------------------End of Test---------------------------");
    }
}
