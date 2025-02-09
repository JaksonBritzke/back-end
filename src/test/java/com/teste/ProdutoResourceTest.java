package com.teste;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class ProdutoResourceTest {

    @Test
    public void testCriarProduto() {
        String requestBody = """
                {
                    "descricao": "Caderno",
                    "situacao": "ATIVO"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/produtos")
                .then()
                .statusCode(201)
                .body("codigo", notNullValue())
                .body("descricao", is("Caderno"))
                .body("situacao", is("ATIVO"));
    }

    @Test
    public void testCriarProdutoSemDescricao() {
        String requestBody = """
                {
                    "situacao": "ATIVO"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/produtos")
                .then()
                .statusCode(400)
                .body("message", is("A descrição do produto é obrigatória"));
    }

    @Test
    public void testCriarProdutoComDescricaoVazia() {
        String requestBody = """
                {
                    "descricao": "",
                    "situacao": "ATIVO"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/produtos")
                .then()
                .statusCode(400)
                .body("message", is("A descrição do produto é obrigatória"));
    }

    @Test
    public void testCriarProdutoSemSituacao() {
        String requestBody = """
                {
                    "descricao": "Caderno"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/produtos")
                .then()
                .statusCode(400)
                .body("message", is("A situação do produto é obrigatória"));
    }
}