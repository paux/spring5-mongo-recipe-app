package cc.paukner.converters;

import cc.paukner.commands.UnitOfMeasureCommand;
import cc.paukner.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final String DESCRIPTION = "description";
    public static final String LONG_VALUE = "1";

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();

    }

    @Test
    public void testNullParamter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure uom = converter.convert(command);

        //then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());

    }

}