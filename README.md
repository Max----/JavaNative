# Java Native

To run these applications you need to have both databases running locally.

Proper setup is:

Start a postgres db in the official docker image with:

```docker run -d --name myPostgres -e POSTGRES_PASSWORD=pass -p 5432:5432 postgres```

Same for the MongoDb docker image except we need a replica set mongo image due to using transactions.
* First create a dockerfile with

```
FROM mongo:latest
RUN echo "rs.initiate();" > /docker-entrypoint-initdb.d/replica-init.js
CMD [ "--replSet", "rs" ]
```

Build this dockerfile with `docker build ./ -t mongo:latest-replset` then run it:

```docker run -d --name myMongo -p 27017:27017 mongo:latest-replset```
