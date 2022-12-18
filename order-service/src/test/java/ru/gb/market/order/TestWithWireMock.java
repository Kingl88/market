package ru.gb.market.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import ru.gb.market.api.cart.CartDto;
import ru.gb.market.api.cart.CartItemDto;
import ru.gb.market.api.order.OrderDetailsDto;

import java.math.BigDecimal;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)// запускаем веб окружение на случайном порту
public class TestWithWireMock {
    private static WireMockServer wireMockServer;
    @Autowired
    private WebTestClient webTestClient;

    @DynamicPropertySource
    static void overrideWebClientBaseUrl(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("integrations.cart-service.url", wireMockServer::baseUrl);// заменяем URl для тестируемого сервиса из property файла на путь из WireMock
    }

    @BeforeAll
    static void startWireMock(){ // запускаем WireMock перед каждым тестом
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        wireMockServer.start();
    }
    @AfterAll
    static void stopWireMock(){
        wireMockServer.stop();
    }

    @Test
    void testWireMock(){
        System.out.println(wireMockServer.baseUrl());
        Assertions.assertTrue(wireMockServer.isRunning());
    }
    @Test
    void getUserByIdTest() throws JsonProcessingException {
        CartDto cartDto = new CartDto(); // создаем объект, который будет возвращен в качестве ответа из WireMock
        cartDto.setTotalPrice(BigDecimal.valueOf(100));
        cartDto.setItems(List.of(new CartItemDto(1L, "Potato", 2, BigDecimal.valueOf(25), BigDecimal.valueOf(50)),
                new CartItemDto(1L, "Tomato", 5, BigDecimal.valueOf(10), BigDecimal.valueOf(50))));
        wireMockServer.stubFor(// настраиваем WireMock что бы по get-запросу возвращался в качестве ответа JSON созданного нами объекта
                WireMock.get("/api/v1/cart/0")
                        .willReturn(aResponse()
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(new ObjectMapper().writer().forType(CartDto.class).writeValueAsString(cartDto)))
        );
        this.webTestClient// настраиваем запрос с помощью веб-клиента
                .post()// тип метода
                .uri("/api/v1/orders/createOrder")// endpoint контроллера
                .header("username", "username")// подшиваем header
                .body(Mono.just(new OrderDetailsDto("Minsk", "+37544567465")), OrderDetailsDto.class)// формируем тело запроса
                .exchange()// получаем ответ
                .expectStatus().isOk(); // проверяем статус ответа
    }
   }
