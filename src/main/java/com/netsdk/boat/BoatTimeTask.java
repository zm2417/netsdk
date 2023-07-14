package com.netsdk.boat;

import org.apache.log4j.Logger;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.TimerTask;

public class BoatTimeTask extends TimerTask {

    private static final Logger log = Logger.getLogger(BoatTimeTask.class);

    private static final Integer millis = 1000 * 60 * 2;

    @Override
    public void run() {
        log.info("timer task");
        if (BoatFile.lastBoatWarn == null) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, BoatFile.lastBoatWarn);
        if (duration.toMillis() > millis) {
            // 超过指定时间没有出现有船只报警，即为无船只
            BoatFile.noBoat();
        }
    }

}
