import cn.hutool.crypto.digest.BCrypt;
import org.junit.Test;
import org.junit.runner.RunWith;

public class test {
    @Test
    public void test(){
        String secret = BCrypt.hashpw("secret", BCrypt.gensalt());
        System.out.println(secret);
    }
}
