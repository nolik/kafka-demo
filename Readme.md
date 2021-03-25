docker-compose up -d

# Generate purchase inserts into mysql
docker exec mysql /data/generate_purcases.sh


# Run App