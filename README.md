Application with demo of Apache Kafka infrastructure with Apache-Kafka + Schema Registry (Avro) + with Source Connector (MySQL) and Sink Connector (Elasticsearch) + KStream App that join change-stream from MySql and Produced stream and demo of KSQL querying. 


### Run the App:
docker-compose up -d

### Generate purchase inserts into mysql
docker exec mysql /data/generate_purcases.sh

### Run App
spring-boot-maven-plugin:run

### Elasticsearch + Kibana:
[Kibana log board](http://localhost:5601/app/visualize#/create?type=area&indexPattern=purchase-detail&_g=(filters:!(),refreshInterval:(pause:!t,value:0),time:(from:now-15m,to:now))&_a=(filters:!(),linked:!f,query:(language:kuery,query:''),uiState:(),vis:(aggs:!((enabled:!t,id:'1',params:(field:price),schema:metric,type:avg),(enabled:!t,id:'2',params:(drop_partials:!f,extended_bounds:(),field:occurred_at,interval:auto,min_doc_count:1,scaleMetricValues:!f,timeRange:(from:now-15m,to:now),useNormalizedEsInterval:!t),schema:segment,type:date_histogram)),params:(addLegend:!t,addTimeMarker:!f,addTooltip:!t,categoryAxes:!((id:CategoryAxis-1,labels:(filter:!t,show:!t,truncate:100),position:bottom,scale:(type:linear),show:!t,style:(),title:(),type:category)),grid:(categoryLines:!f),labels:(),legendPosition:right,seriesParams:!((data:(id:'1',label:'Average%20price'),drawLinesBetweenPoints:!t,interpolate:linear,lineWidth:2,mode:stacked,show:!t,showCircles:!t,type:area,valueAxis:ValueAxis-1)),thresholdLine:(color:%23E7664C,show:!f,style:full,value:10,width:1),times:!(),type:area,valueAxes:!((id:ValueAxis-1,labels:(filter:!f,rotate:0,show:!t,truncate:100),name:LeftAxis-1,position:left,scale:(mode:normal,type:linear),show:!t,style:(),title:(text:'Average%20price'),type:value))),title:'',type:area)))

[Kibana dushboard](http://localhost:5601/app/discover#/?_g=(filters:!(),refreshInterval:(pause:!t,value:0),time:(from:now-15m,to:now))&_a=(columns:!(_source),filters:!(),index:purchase-detail,interval:auto,query:(language:kuery,query:''),sort:!()))


### Run KSQL queries

`SET 'auto.offset.reset' = 'earliest';
SHOW TOPICS;`

`CREATE STREAM PURCHASE WITH (KAFKA_TOPIC='purchase-detail', VALUE_FORMAT='AVRO');
DESCRIBE PURCHASE;`

`SELECT ID, FIRST_NAME, SECOND_NAME, PRODUCT, PRICE, OCCURRED_AT FROM PURCHASE
WHERE PRODUCT LIKE 'Beer%' or 'Wine%'
EMIT CHANGES;`

`SELECT PRODUCT, COUNT(*) AS PURCHASE_COUNT, SUM(CAST(PRICE AS DECIMAL(13,2))) AS TOTAL_VALUE
FROM PURCHASE
GROUP BY PRODUCT EMIT CHANGES;`