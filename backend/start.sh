sudo docker rm -f bodybuddy_backend
sudo docker run -d -p 8080:8080 --name bodybuddy_backend --restart=always -e springprofiles.active=prod -v /home/ubuntu/data/bodybuddy_backend:/home/ubuntu/data/bodybuddy_backend bodybuddy_backend
