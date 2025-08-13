FROM ubuntu

RUN mkdir -p database
RUN mkdir -p database/db
WORKDIR ./database

RUN apt update
RUN apt install -y postgresql-client


COPY ./db/* ./database/db/
COPY --chmod=755 ./migration.sh ./database/migration.sh

ENTRYPOINT ["./database/migration.sh"]