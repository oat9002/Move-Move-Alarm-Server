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
    }
}
