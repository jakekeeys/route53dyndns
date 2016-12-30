# route53dyndns

A simple Java project to dynamically update and maintain a AWS route53 record, the value of which is based on the hosts current IP.

## Building

To build the project run `mvn clean package` followed by `docker build -t route53dyndns .`

## Running

To run the project run the following ```docker run -e IP_RESOLUTION_HOST='http://checkip.amazonaws.com' -e CHECK_INTERVAL='60'  -e ROUTE53_KEY='' -e ROUTE53_SECRET='' -e ROUTE_53_HOSTED_ZONE_ID='' -e ROUTE_53_RECORD_NAME='' -e ROUTE_53_RECORD_TYPE='A' -e ROUTE_53_RECORD_TTL='300' route53dyndns``` filling in gaps.
