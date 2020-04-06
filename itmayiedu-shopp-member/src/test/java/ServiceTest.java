import com.itmayiedu.MemberServer;
import com.itmayiedu.constants.DBTableName;
import com.itmayiedu.dao.UserDao;
import com.itmayiedu.entity.UserEntity;
import com.itmayiedu.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MemberServer.class)
@TestPropertySource(locations = "classpath:application.yml")
@ActiveProfiles("test")
public class ServiceTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void test() {

        UserEntity userEntity = new UserEntity();
        userEntity.setCreated(DateUtils.getTimestamp());
        userEntity.setUpdated(DateUtils.getTimestamp());
        String phone = "111111111";
        String password = "222222";
        userEntity.setPassword(password);
        userEntity.setEmail("3333");
        userEntity.setUserName("zzz");
        userEntity.setPhone(phone);
        userDao.save(userEntity, DBTableName.TABLE_MB_USER);
    }


}
