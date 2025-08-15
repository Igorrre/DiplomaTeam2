package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseAdapter {

    public final String BASE_URI = "http://82.142.167.37:4879/";
    public final String CAR_URI = "http://82.142.167.37:4879/car";

    public Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public RequestSpecification spec = given()
            .contentType(ContentType.JSON)
            .log().all();
}