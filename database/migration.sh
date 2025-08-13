#!/bin/bash

path="./database/db"

for file in "$path"/*; do
  echo $file
  PGPASSWORD=genLife psql -U user -p 5432 -d noteapp -h database -f "${file}"
done