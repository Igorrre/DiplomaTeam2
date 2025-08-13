package pageModels;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseURL {

    public Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setLenient().create();

    public static final String GET_USER_URI = "http://82.142.167.37:4879/user/8018";

    public RequestSpecification specification = given()
            .contentType(ContentType.JSON)
            .log().all();
}