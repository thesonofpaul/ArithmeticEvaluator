import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Created by Zach P on 7/5/2017.
 */
public class EvaluatorTest {

    Evaluator evaluator;

    @Test
    public void testSimpleAdd() {
        final String expression = "2 + 2";
        evaluator = new Evaluator(expression);
        double ans = evaluator.parse();
        assertEquals(ans, (double) 4, 0);
    }

    @Test
    public void testSimpleSub() {
        final String expression = "8 - 2";
        evaluator = new Evaluator(expression);
        double ans = evaluator.parse();
        assertEquals(ans, (double)6, 0);
    }

    @Test
    public void testSimpleMult() {
        final String expression = "4 * 4";
        evaluator = new Evaluator(expression);
        double ans = evaluator.parse();
        assertEquals(ans, (double)16, 0);
    }

    @Test
    public void testSimpleDiv() {
        final String expression = "20 / 5";
        evaluator = new Evaluator(expression);
        double ans = evaluator.parse();
        assertEquals(ans, (double)4, 0);
    }

    @Test
     public void testComplex1() {
        final String expression = "2 + ( 4 * 4 ) - 3";
        evaluator = new Evaluator(expression);
        double ans = evaluator.parse();
        assertEquals(ans, (double)15, 0);
    }

    @Test
    public void testComplex2() {
        final String expression = "( 8 / 3 ) + ( 5 * 2 )";
        evaluator = new Evaluator(expression);
        double ans = evaluator.parse();
        assertEquals(ans, ( 8. / 3. ) + ( 5. * 2. ), 0);
    }

    @Test
    public void testComplex3() {
        final String expression = "100 / 50 / 2 * 6";
        evaluator = new Evaluator(expression);
        double ans = evaluator.parse();
        assertEquals(ans, 100. / 50. / 2. * 6., 0);
    }

    @Test
    public void testDecimal() {
        final String expression = "8.5 + ( 6.1 * 4.2 )";
        evaluator = new Evaluator(expression);
        double ans = evaluator.parse();
        assertEquals(ans, 8.5 + ( 6.1 * 4.2 ), 0);
    }

    @Test
    public void testNegative() {
        final String expression = "-( 4 + 2 ) * 2";
        evaluator = new Evaluator(expression);
        double ans = evaluator.parse();
        assertEquals(ans, (-( 4. + 2. ) * 2.), 0);
    }

    @Test
    public void testSpacing() {
        final String expression = "(42+3 )   * 64 + (8-  3  )";
        evaluator = new Evaluator(expression);
        double ans = evaluator.parse();
        assertEquals(ans, (42.+3.)*64.+(8.-3.), 0);
    }

    @Test (expected = RuntimeException.class)
    public void testInvalid1() {
        final String expression = "5 *-+/ 6";
        evaluator = new Evaluator(expression);
        double ans = evaluator.parse();
    }

    @Test (expected = RuntimeException.class)
    public void testInvalid2() {
        final String expression = "5 / 6 +";
        evaluator = new Evaluator(expression);
        double ans = evaluator.parse();
    }

}