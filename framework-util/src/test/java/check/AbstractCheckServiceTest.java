package check;

import com.xuegao.util.check.AbstractCheckService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xuegao
 * @date 2022年10月20日 14:46
 */
@SpringBootTest
public class AbstractCheckServiceTest implements AbstractCheckService {


    @Test
    public void checkIsNumber() {
        String str = "11";
        System.out.println(str + " 是不是数字: " + checkIsNumber("111", str));
        String str1 = "11.1";
        System.out.println(str1 + " 是不是数字: " + checkIsNumber("111", str1));
        String str2 = "-11";
        System.out.println(str2 + " 是不是数字: " + checkIsNumber("111", str2));
        String str3 = "-11.2";
        System.out.println(str3 + " 是不是数字: " + checkIsNumber("111", str3));

        // String str4 = "11.1a";
        // try {
        //     System.out.println(str4 + " 是不是数字: " + checkIsNumber("111", str4));
        // }catch (Exception e){
        //     log
        // }
    }


}
