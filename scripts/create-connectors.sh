#!/bin/bash

echo "Installing Connector"
confluent-hub install --no-prompt confluentinc/kafka-connect-datagen:0.4.0
confluent-hub install --no-prompt debezium/debezium-connector-mysql:1.3.1
confluent-hub install --no-prompt confluentinc/kafka-connect-elasticsearch:10.0.1
/etc/confluent/docker/run &
echo "Waiting for Kafka Connect to start listening on $CONNECT_REST_ADVERTISED_HOST_NAME ⏳"
while [ $(curl -s -o /dev/null -w %{http_code} http://$CONNECT_REST_ADVERTISED_HOST_NAME:$CONNECT_REST_PORT/connectors) -ne 200 ] ; do
  echo -e $(date) " Kafka Connect listener HTTP state: " $(curl -s -o /dev/null -w %{http_code} http://$CONNECT_REST_ADVERTISED_HOST_NAME:$CONNECT_REST_PORT/connectors) " (waiting for 200)"
  sleep 5
done
echo "Creating new joiner sample connector"
curl -s -X PUT -H  "Content-Type:application/json" http://localhost:8083/connectors/source-new-joiners-valid/config \
    -d '{
    "max.interval":250,
    "iterations": 100,
    "connector.class": "io.confluent.kafka.connect.datagen.DatagenConnector",
    "kafka.topic": "new.joiners",
    "schema.string": "{\"type\": \"record\", \"namespace\": \"com.comparethemarket.kstreams.sandbox.model\", \"name\": \"Employee\", \"fields\": [{\"type\": {\"type\": \"int\", \"arg.properties\": {\"iteration\": {\"start\": 1, \"step\": 1}}}, \"name\": \"employeeId\"}, {\"type\": {\"type\": \"string\", \"arg.properties\": {\"regex\": \"(Bruce|Carol|Clint|Natasha|Thor|Wanda|Steve) [A-Z][.] (Banner|Danvers|Barton|Romanoff|Odinson|Maximoff|Rogers)\"}}, \"name\": \"name\"}, {\"type\": {\"type\": \"string\", \"arg.properties\": {\"regex\": \"2020-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])\"}}, \"name\": \"joiningDate\"}]}",
    "schema.keyfield": "employeeId",
    "key.converter": "org.apache.kafka.connect.converters.IntegerConverter",
    "value.converter": "org.apache.kafka.connect.json.JsonConverter",
    "value.converter.schemas.enable": "false",
    "tasks.max": "1"
  }'
# Don't let the container die
sleep infinity