package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseAdapter {

    public final
    String BASE_URI = "http://82.142.167.37:4879/";
    String CREATE_USER_URI = BASE_URI + "user";
    String GET_USER_URI = BASE_URI + "user/8018";
    String CAR_URI = BASE_URI + "car";
    String HOUSE_URI = BASE_URI + "house";
    String ADD_MONEY_USER_URI = BASE_URI;


    public Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public RequestSpecification spec = given()
            .contentType(ContentType.JSON)
            .log().all();
}