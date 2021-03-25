#!/bin/bash
        echo "Waiting for Kibana to be ready ⏳"
        while [ $$(curl -H 'kbn-xsrf: true' -s -o /dev/null -w %{http_code} http://localhost:5601/api/saved_objects/_find?type=index-pattern&search_fields=title&search=*) -ne 200 ] ; do
          echo -e "\t" $$(date) " Kibana saved objects request response: " $$(curl -H 'kbn-xsrf: true' -o /dev/null -w %{http_code} -s http://localhost:5601/api/saved_objects/_find?type=index-pattern&search_fields=title&search=*) $$(curl -H 'kbn-xsrf: true' -s http://localhost:5601/api/saved_objects/_find?type=index-pattern&search_fields=title&search=*) " (waiting for 200)"
          sleep 5
        done

        echo -e "\t" $$(date) " Kibana saved objects request response: " $$(curl -H 'kbn-xsrf: true' -o /dev/null -w %{http_code} -s http://localhost:5601/api/saved_objects/_find?type=index-pattern&search_fields=title&search=*) $$(curl -H 'kbn-xsrf: true' -s http://localhost:5601/api/saved_objects/_find?type=index-pattern&search_fields=title&search=*)

        echo -e "\n--\n+> Pre-creating index pattern"
        curl -s -XPOST 'http://localhost:5601/api/saved_objects/index-pattern/rated-movies' \
          -H 'kbn-xsrf: nevergonnagiveyouup' \
          -H 'Content-Type: application/json' \
          -d '{"attributes":{"title":"purchase-detail","timeFieldName":"occured_at"}}'

        echo -e "\n--\n+> Setting the index pattern as default"
        curl -s -XPOST 'http://localhost:5601/api/kibana/settings' \
          -H 'kbn-xsrf: nevergonnagiveyouup' \
          -H 'content-type: application/json' \
          -d '{"changes":{"defaultIndex":"purchase-detail"}}'

        echo -e "\n--\n+> Opt out of Kibana telemetry"
        curl 'http://localhost:5601/api/telemetry/v2/optIn' \
            -H 'kbn-xsrf: nevergonnagiveyouup' \
            -H 'content-type: application/json' \
            -H 'accept: application/json' \
            --data-binary '{"enabled":false}' \
            --compressed

        sleep infinity