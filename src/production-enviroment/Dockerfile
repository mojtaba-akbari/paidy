FROM java:18
WORKDIR /
ADD service-app.jar service-app.jar
# I do not know what is your production enviroment , API base or message service base... to expose web-api or any other procotol #
# EXPOSE 8080
# So pass Test main class to the entrypoint :)
CMD java - jar service-app.jar org.paidy.AppTest