package check;

import com.xuegao.util.check.AbstractCheckService;
import com.xuegao.util.check.AbstractCheckServiceV2;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * @author xuegao
 * @date 2022年10月20日 14:46
 */
@SpringBootTest
public class AbstractCheckServiceV2Test implements AbstractCheckServiceV2 {

    @Test
    public void checkIsAllNull() {
        // 正常报错
        // checkIsAllNull("111", null);
        // 正常报错
        // checkIsAllNull("111", null, null);
        // 正常报错
        // checkIsAllNull("111", null, null, null);
        // 不报错
        checkIsAllNull("111", null, null, 1);
    }
}
