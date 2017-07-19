FROM openjdk:8-jdk
RUN mkdir /usr/local/hello-play
ADD . /usr/local/hello-play/
RUN chmod a+x /usr/local/hello-play/bin/hello-play
EXPOSE 8080
ENTRYPOINT /usr/local/hello-play/bin/hello-play -Dplay.http.secret.key="HKVoNtKacvYuWbs_Wg>sTU157csQWzFtddCp[vslJSm=MXy/]Q58Z<giXBTOi?EA" -Dhttp.port=8080 && tail -f /dev/null