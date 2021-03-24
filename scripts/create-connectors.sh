#!/bin/bash

echo "Installing Connector"
#confluent-hub install --no-prompt confluentinc/kafka-connect-datagen:0.4.0
#confluent-hub install --no-prompt debezium/debezium-connector-mysql:1.3.1
confluent-hub install --no-prompt confluentinc/kafka-connect-elasticsearch:10.0.1
/etc/confluent/docker/run &
echo "Waiting for Kafka Connect to start listening on $CONNECT_REST_ADVERTISED_HOST_NAME ⏳"
while [ $(curl -s -o /dev/null -w %{http_code} http://$CONNECT_REST_ADVERTISED_HOST_NAME:$CONNECT_REST_PORT/connectors) -ne 200 ] ; do
  echo -e $(date) " Kafka Connect listener HTTP state: " $(curl -s -o /dev/null -w %{http_code} http://$CONNECT_REST_ADVERTISED_HOST_NAME:$CONNECT_REST_PORT/connectors) " (waiting for 200)"
  sleep 5
done
echo "Creating elasticsearch connector"
curl -i -X PUT -H  "Content-Type:application/json" \
    http://localhost:8083/connectors/sink-elastic-orders-00/config \
    -d '{
        "connector.class": "io.confluent.connect.elasticsearch.ElasticsearchSinkConnector",
        "topics": "rated-movies",
        "connection.url": "http://elasticsearch:9200",
        "type.name": "type.name=kafkaconnect",
        "key.ignore": "true",
        "schema.ignore": "true"
    }'
# Don't let the container die
sleep infinity