package cc.paukner.config;

import cc.paukner.domain.Recipe;
import cc.paukner.services.RecipeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class WebConfig {

    @Bean
    public RouterFunction<?> routes(RecipeService recipeService) { // becomes a component, will inject the RecipeService for us
        return RouterFunctions.route(GET("/api/recipes"),
                serverRequest -> ServerResponse.ok()
                                               .contentType(MediaType.APPLICATION_JSON)
                                               .body(recipeService.getRecipes(), Recipe.class));
    }
}
