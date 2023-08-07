#!/bin/bash

echo "Apply database migrations"
python manage.py migrate

echo "Create superuser"
python manage.py createsuperuser --noinput

echo "Collect static files"
python manage.py collectstatic --noinput

echo "Start gunicorn server"
gunicorn -c gunicorn.conf.py admin.wsgi

exec "$@"
