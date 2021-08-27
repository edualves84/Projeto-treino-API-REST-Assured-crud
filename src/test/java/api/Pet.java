package api;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Pet {
    
    int petId = 1984;
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));

    }

    @Test
    public void incluirPet() throws IOException {

       String jsonBody = lerJson("src/test/resources/data/pet.json");



        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post("https://petstore.swagger.io/v2/pet")

        .then()
                .log().all()
                .statusCode(200)
                .body("id", is (1984));

    }

       @Test
    public void consultarPet(){


        given()
                .contentType("application/json")
                .log().all()
        .when() .get("https://petstore.swagger.io/v2/pet/"+ petId)

        .then()
                .log().all()
                .statusCode(200)
                .body("status", is ("available"));

       }

       @Test
    public void AlterarPet() throws IOException {

        String jsonBody = lerJson("src/test/resources/data/newpet.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .put("https://petstore.swagger.io/v2/pet")

        .then()
                .log().all()
                .statusCode(200)
                .body("status", is("sold"));

       }
          @Test
    public void deletarPEt(){
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .delete("https://petstore.swagger.io/v2/pet" +"/" + petId)
        .then()
                .log().all()
                .statusCode(200);

          }
}
