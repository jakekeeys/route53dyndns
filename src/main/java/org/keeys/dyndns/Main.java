package org.keeys.dyndns;

import java.util.Timer;
import java.util.TimerTask;
import org.keeys.dyndns.config.Config;
import org.keeys.dyndns.helper.Route53Helper;

public class Main extends TimerTask {

    private Config config;
    private Route53Helper route53Helper;

    public static void main(String args[]){
        new Main().run();
    }

    public Main(){
        this.config = Config.create();
        this.route53Helper = Route53Helper.create(config);
    }

    public void run() {
        TimerTask timerTask = new Route53Updater(config, route53Helper);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, config.getCheckInterval() * 1000);
    }
}

