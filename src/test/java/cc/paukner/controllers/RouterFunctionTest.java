package cc.paukner.controllers;

import cc.paukner.config.WebConfig;
import cc.paukner.domain.Recipe;
import cc.paukner.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;

public class RouterFunctionTest {

    WebTestClient webTestClient;

    @Mock // we emulate what Spring Framework would do
    RecipeService recipeService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        WebConfig webConfig = new WebConfig();

        RouterFunction<?> routerFunction = webConfig.routes(recipeService);

        webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build();
    }

    @Test
    public void getRecipes() throws Exception {

        when(recipeService.getRecipes()).thenReturn(Flux.empty());

        webTestClient.get().uri("/api/recipes")
                .accept(MediaType.APPLICATION_JSON)
                .exchange() // invoke the web client
                .expectStatus().isOk();
    }

    @Test
    public void getRecipesWithData() throws Exception {

        when(recipeService.getRecipes()).thenReturn(Flux.just(new Recipe(), new Recipe()));

        webTestClient.get().uri("/api/recipes")
                .accept(MediaType.APPLICATION_JSON)
                .exchange() // invoke the web client
                .expectStatus().isOk()
                .expectBodyList(Recipe.class);
    }
}
