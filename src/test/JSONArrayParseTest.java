import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class JSONArrayParseTest {

    private String text;
    private TypeReference<List<Map<String, Integer>>> typeReference;

    public JSONArrayParseTest(String text, TypeReference<List<Map<String, Integer>>> typeReference) {
        configure(text, typeReference);
    }

    private void configure(String text, TypeReference<List<Map<String, Integer>>> typeReference) {
        this.text = text;
        this.typeReference = typeReference;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
                {"[{id:123}]", new TypeReference<List<Map<String, Integer>>>() {
                }},
                {"", new TypeReference<List<Map<String, Integer>>>() {
                }},
                {null, new TypeReference<List<Map<String, Integer>>>() {
                }}
        });
    }

    @Test
    public void test_array() {
        List<Map<String, Integer>> array = JSON.parseObject(text, typeReference); //Restituisce [{id=123}]

        if (this.text == null || this.text.isEmpty()) {
            Assert.assertNull(array);
        } else {
            String[] split = text.substring(2, text.length() - 1).split("[{}]");
            String[] split2 = split[0].split(":");

            Assert.assertEquals(split.length, array.size()); //array size = 1
            Map<String, Integer> map = array.get(0);
            Assert.assertEquals(Integer.parseInt(split2[split2.length - 1]), map.get("id").intValue());
        }
    }
}
