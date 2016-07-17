package movealarm.kmitl.net.user;

import movealarm.kmitl.net.MoveAlarmServerApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by oat90 on 7/16/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MoveAlarmServerApplication.class)
@WebAppConfiguration
@ConfigurationProperties(locations = "/src/test/resources/application-test.properties")
public class UserSeviceTest {
}
