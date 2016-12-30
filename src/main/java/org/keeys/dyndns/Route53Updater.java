package org.keeys.dyndns;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.TimerTask;

import org.keeys.dyndns.config.Config;
import org.keeys.dyndns.helper.Route53Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class Route53Updater extends TimerTask {

    private static final Logger log = LoggerFactory.getLogger(Route53Updater.class);


    private Config config;
    private Route53Helper route53Helper;

    public Route53Updater(
            Config config,
            Route53Helper route53Helper
    ){
        this.config = checkNotNull(config);
        this.route53Helper = checkNotNull(route53Helper);
    }

    public void run(){
        try {
            Thread.sleep(config.getCheckInterval());

            String currentIP =
                    new BufferedReader(
                            new InputStreamReader(
                                    new URL(config.getIpResolutionHost()).openStream()
                            )
                    ).readLine();

            String recordedIP = route53Helper
                    .getRecordSet()
                    .getResourceRecords()
                    .get(0)
                    .getValue();

            if (!currentIP.equals(recordedIP)) {
                route53Helper.updateRecordSet(currentIP);
                log.info("Route53 updated with " + currentIP);
            }
            else log.info("Route53 is up to date");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
