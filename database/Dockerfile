FROM ubuntu

# Install Postgres client
RUN apt-get update && apt-get install -y postgresql-client

# Create directories
RUN mkdir -p /database/db

# Set working directory
WORKDIR /database

# Copy migration scripts and SQL files
COPY db ./db
COPY --chmod=755 ./migration.sh ./migration.sh

# Set entrypoint
ENTRYPOINT ["./migration.sh"]