package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.api.AuthorizationRq;
import dto.api.AuthorizationRs;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class AuthorizationAdapter {
    private final String BASE_URI = "http://82.142.167.37:4879/";
    public Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public RequestSpecification spec = given()
            .contentType(ContentType.JSON)
            .log().all();

    public AuthorizationRs authorization(AuthorizationRq authorizationRq) {
        return spec
                .body(gson.toJson(authorizationRq))
                .when()
                .post(BASE_URI + "login")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(AuthorizationRs.class, ObjectMapperType.GSON);

    }
}
