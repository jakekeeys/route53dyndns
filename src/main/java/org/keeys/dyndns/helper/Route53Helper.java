package org.keeys.dyndns.helper;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.route53.AmazonRoute53Client;
import com.amazonaws.services.route53.model.*;
import org.keeys.dyndns.config.Config;

import static com.google.common.base.Preconditions.checkNotNull;

public class Route53Helper {

    private Config config;
    private AmazonRoute53Client amazonRoute53Client;

    private Route53Helper(Config config){
        this.config = checkNotNull(config);
        this.amazonRoute53Client = new AmazonRoute53Client(
                new BasicAWSCredentials(config.getRoute53Key(),config.getRoute53Secret())
        );
    }

    public static Route53Helper create(Config config){
        return new Route53Helper(config);
    }

    public ResourceRecordSet getRecordSet(){
        ListResourceRecordSetsResult listResourceRecordSetsResult =
                amazonRoute53Client.listResourceRecordSets(new ListResourceRecordSetsRequest()
                        .withHostedZoneId(config.getRoute53HostedZoneId())
                        .withStartRecordName(config.getRoute53RecordName())
                        .withStartRecordType(config.getRoute53RecordType())
                        .withMaxItems("1")
                );

        return listResourceRecordSetsResult.getResourceRecordSets().get(0);
    }

    public ChangeResourceRecordSetsResult updateRecordSet(String currentIP){
        ChangeResourceRecordSetsRequest changeResourceRecordSetsRequest = new ChangeResourceRecordSetsRequest()
                .withHostedZoneId(config.getRoute53HostedZoneId())
                .withChangeBatch(
                        new ChangeBatch().withChanges(
                                new Change()
                                        .withAction("DELETE").withResourceRecordSet(getRecordSet()),
                                new Change()
                                        .withAction("CREATE").withResourceRecordSet(new ResourceRecordSet()
                                        .withName(config.getRoute53RecordName())
                                        .withType(config.getRoute53RecordType())
                                        .withTTL(config.getRoute53RecordTTL())
                                        .withResourceRecords(
                                                new ResourceRecord().withValue(currentIP)
                                        )
                                )
                        )
                );

        return amazonRoute53Client.changeResourceRecordSets(changeResourceRecordSetsRequest);
    }
}

