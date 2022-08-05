import org.junit.Assert;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class JSONFieldTest {
    private String result;
    private Integer id;
    private String name;

    public JSONFieldTest(Integer id, String name, String result) {
        configure(id, name, result);
    }

    private void configure(Integer id, String name, String result) {
        this.id = id;
        this.name = name;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
                {123, "xx", "{\"id\":123}"},
                {1, "xx", "{\"id\":1}"},
                {null, "xx", null}
        });
    }

    @Test
    public void test_jsonField() {
        VO vo = new VO();

        if (this.id == null) {
            Assert.assertNull(this.result);
        } else {
            vo.setId(this.id);
            vo.setName(this.name);

            String text = JSON.toJSONString(vo);
            Assert.assertEquals(this.result, text);
        }
    }

}
