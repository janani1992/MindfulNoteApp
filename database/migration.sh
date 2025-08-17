#!/bin/bash
set -e

# Wait for Postgres to be ready
until PGPASSWORD="${POSTGRES_PASSWORD:-genLife}" psql -h database -U "${POSTGRES_USER:-user}" -d "${POSTGRES_DB:-noteapp}" -c '\q' 2>/dev/null; do
  echo "Waiting for database to be ready..."
  sleep 2
done

# Run all SQL files in ./db
for file in ./db/*.sql; do
  echo "Running migration: $file"
  PGPASSWORD="${POSTGRES_PASSWORD:-genLife}" psql -U "${POSTGRES_USER:-user}" -h database -d "${POSTGRES_DB:-noteapp}" -f "$file"
done