package movealarm.kmitl.net;

import movealarm.kmitl.net.common.SQLInquirer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoveAlarmServerApplication {

    public static void main(String[] args) {
        SQLInquirer sqlInquirer = SQLInquirer.getInstance();
        System.out.println("database server connection : " + ((sqlInquirer.isConnecting()) ? "connected" : "not connected"));
        SpringApplication.run(MoveAlarmServerApplication.class, args);
        /*HashMap<String, Object> user = new HashMap<>();
        HashMap<String, Object> JSON = new HashMap<>();
        user.put("id", 1);
        JSON.put("user", Converter.getInstance().HashMapToJSON(user));
        JSON.put("type", 0);
        UserProgressController controller = new UserProgressController();
        controller.getByUser(Converter.getInstance().HashMapToJSON(JSON));*/
    }
}
