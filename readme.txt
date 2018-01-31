# PostGIS docker
docker run --name some-postgis -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres@123 -e POSTGRES_DB=springbootdb -d mdillon/postgis

# Create Geo index
CREATE INDEX event_gix ON event USING GIST(location);