import org.junit.Assert;
import org.junit.Test;

public class TestOffByN {
    static CharacterComparator offByN = new OffByN(5);


    @Test
    public void testOffByN() {
        Assert.assertTrue(offByN.equalChars('a', 'f'));
        Assert.assertTrue(offByN.equalChars('g', 'b'));
        Assert.assertFalse(offByN.equalChars('f', 'h'));

    }

}
