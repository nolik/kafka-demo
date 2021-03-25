#!/bin/bash

echo Waiting for Kafka to be ready...
cub kafka-ready -b broker:29092 1 20
sleep 0

kafka-topics --create --topic customer --if-not-exists --bootstrap-server broker:29092 --partitions 1 --replication-factor 1
kafka-topics --create --topic purchase-detail --if-not-exists --bootstrap-server broker:29092 --partitions 1 --replication-factor 1
kafka-topics --create --topic rekeyed-customers --if-not-exists --bootstrap-server broker:29092 --partitions 1 --replication-factor 1

kafka-topics --create --topic _kafka-connect-01-configs --if-not-exists --bootstrap-server broker:29092 --partitions 1 --replication-factor 1 --config cleanup.policy=compact
kafka-topics --create --topic _kafka-connect-01-offsets --if-not-exists --bootstrap-server broker:29092 --partitions 1 --replication-factor 1 --config cleanup.policy=compact
kafka-topics --create --topic _kafka-connect-01-status --if-not-exists --bootstrap-server broker:29092 --partitions 1 --replication-factor 1 --config cleanup.policy=compact
