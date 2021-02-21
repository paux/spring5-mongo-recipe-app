package cc.paukner.services;

import cc.paukner.domain.Recipe;
import cc.paukner.repositories.reactive.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * Created by jt on 7/3/17.
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {


    private final RecipeReactiveRepository recipeReactiveRepository;

    public ImageServiceImpl(RecipeReactiveRepository recipeReactiveRepository) {

        this.recipeReactiveRepository = recipeReactiveRepository;
    }

    @Override
    public Mono<Void> saveImageFile(String recipeId, MultipartFile file) {

        Mono<Recipe> recipeMono = recipeReactiveRepository.findById(recipeId)
                .map(recipe -> {
                    Byte[] bytes = new Byte[0];
                    try {
                        bytes = new Byte[file.getBytes().length];

                        int i = 0;
                        for (byte b : file.getBytes()) {
                            bytes[i++] = b;
                        }
                        recipe.setImage(bytes);

                        return recipe;
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                });
        recipeReactiveRepository.save(recipeMono.block()).block();
        return Mono.empty();
    }
}
