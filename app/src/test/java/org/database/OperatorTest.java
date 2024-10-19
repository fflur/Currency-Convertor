import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.database.Operator;
import java.io.IOException;
import nu.xom.*;

public class OperatorTest {
    @Test
    public void operatorTest() throws ParsingException, IOException {
        Operator foo = new Operator();
        Assertions.assertDoesNotThrow(() -> foo.loadOldCurrencies());
        Assertions.assertDoesNotThrow(() -> foo.convertor());
        Assertions.assertDoesNotThrow(() -> foo.writeOut());
    }
}
