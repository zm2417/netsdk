package com.netsdk.boat;

import org.apache.log4j.Logger;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.TimerTask;

public class BoatTimeTask extends TimerTask {

    private static final Logger log = Logger.getLogger(BoatTimeTask.class);

    /**
     * 3分15秒
     */
    private static final Integer millis = 1000 * (3 * 60 + 15);

    @Override
    public void run() {
        BoatFile.timer();
//        log.info("timer task");
//        if (BoatFile.lastBoatWarn == null) {
//            System.out.println(1);
//            BoatFile.noBoat();
//            return;
//        }
//        LocalDateTime now = LocalDateTime.now();
//        Duration duration = Duration.between(BoatFile.lastBoatWarn, now);
//        if (Math.abs(duration.toMillis()) > millis) {
//            System.out.println(2);
//            // 超过指定时间没有出现有船只报警，即为无船只
//            BoatFile.noBoat();
//        }
    }

}
