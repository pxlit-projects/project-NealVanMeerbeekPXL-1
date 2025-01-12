#! /bin/bash
export APIURL="http://localhost:8086"
echo "changing api url to $APIURL"
sed -i "s|APIURL|$APIURL|g" /usr/share/nginx/html/main*.js
exec "$@"
