package cc.paukner.repositories.reactive;

import cc.paukner.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {

    public static final String DESCRIPTION = "description";

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void saveUom() throws Exception {
        unitOfMeasureReactiveRepository.save(UnitOfMeasure.builder().description(DESCRIPTION).build()).block();

        assertEquals(Long.valueOf(1L), unitOfMeasureReactiveRepository.count().block());
    }

    @Test
    public void findByDescription() throws Exception {
        unitOfMeasureReactiveRepository.save(UnitOfMeasure.builder().description(DESCRIPTION).build()).block();

         UnitOfMeasure fetchedUom = unitOfMeasureReactiveRepository.findByDescription(DESCRIPTION).block();

         assertEquals(DESCRIPTION, fetchedUom.getDescription());
    }
}
