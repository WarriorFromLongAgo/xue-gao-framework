package check;

import com.xuegao.util.UuidUtil;
import org.junit.Test;

public class UuidUtilTest {

    @Test
    public void uuidUtilTest() {
        String uuid = UuidUtil.uuid();
        System.out.println("test 1 = " + uuid);
        uuid = UuidUtil.uuid();
        System.out.println("test 2 = " + uuid);
        uuid = UuidUtil.uuid();
        System.out.println("test 3 = " + uuid);
        uuid = UuidUtil.uuid();
        System.out.println("test 4 = " + uuid);
        uuid = UuidUtil.uuid();
        System.out.println("test 5 = " + uuid);
    }

}
