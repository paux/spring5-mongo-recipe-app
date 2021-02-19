package cc.paukner.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
// This will not be a @Document, but contains a db reference to a uom document
public class Ingredient {

//    @Id
    // WTF is going on here? -- Create distinct property or so, because it's nested in a list
    private String id = UUID.randomUUID().toString();
    private String description;
    private BigDecimal amount;

    @DBRef // not supported by MongoDB, will be changed later
    private UnitOfMeasure uom;
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

}
