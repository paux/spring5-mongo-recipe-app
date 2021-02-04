package cc.paukner.repositories.reactive;

import cc.paukner.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void save() throws Exception {
        categoryReactiveRepository.save(Category.builder().description("Cat").build()).block();

        assertEquals(Long.valueOf(1L), categoryReactiveRepository.count().block());
    }

    @Test
    public void findByDescription() throws Exception {
        categoryReactiveRepository.save(Category.builder().description("Cat").build()).block();

        Category fetchedCat = categoryReactiveRepository.findByDescription("Cat").block();

        assertNotNull(fetchedCat.getId());
    }
}
