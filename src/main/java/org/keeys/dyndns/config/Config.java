package org.keeys.dyndns.config;

import static com.google.common.base.Preconditions.checkNotNull;

public class Config {

    private String ipResolutionHost;
    private Long checkInterval;

    private String route53Key;
    private String route53Secret;
    private String route53HostedZoneId;
    private String route53RecordName;
    private String route53RecordType;
    private Long route53RecordTTL;

    private Config(
            String ipResolutionHost,
            Long checkInterval,
            String route53Key,
            String route53Secret,
            String route53HostedZoneId,
            String route53RecordName,
            String route53RecordType,
            Long route53RecordTTL
    ) {
        this.ipResolutionHost = checkNotNull(ipResolutionHost);
        this.checkInterval = checkNotNull(checkInterval);
        this.route53Key = checkNotNull(route53Key);
        this.route53Secret = checkNotNull(route53Secret);
        this.route53HostedZoneId = checkNotNull(route53HostedZoneId);
        this.route53RecordName = checkNotNull(route53RecordName);
        this.route53RecordType = checkNotNull(route53RecordType);
        this.route53RecordTTL = checkNotNull(route53RecordTTL);
    }

    public static Config create() {
        return new Config(
            System.getenv("IP_RESOLUTION_HOST"),
            Long.parseLong(System.getenv("CHECK_INTERVAL")),
            System.getenv("ROUTE53_KEY"),
            System.getenv("ROUTE53_SECRET"),
            System.getenv("ROUTE_53_HOSTED_ZONE_ID"),
            System.getenv("ROUTE_53_RECORD_NAME"),
            System.getenv("ROUTE_53_RECORD_TYPE"),
            Long.parseLong(System.getenv("ROUTE_53_RECORD_TTL"))
        );
    }

    public String getIpResolutionHost() {
        return ipResolutionHost;
    }

    public Long getCheckInterval() {
        return checkInterval;
    }

    public String getRoute53Key() {
        return route53Key;
    }

    public String getRoute53Secret() {
        return route53Secret;
    }

    public String getRoute53HostedZoneId() {
        return route53HostedZoneId;
    }

    public String getRoute53RecordName() {
        return route53RecordName;
    }

    public String getRoute53RecordType() {
        return route53RecordType;
    }

    public Long getRoute53RecordTTL() {
        return route53RecordTTL;
    }
}
