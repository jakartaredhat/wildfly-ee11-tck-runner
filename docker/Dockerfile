# Reference: https://docs.docker.com/engine/reference/builder/

FROM fedora:40

RUN ["dnf", "upgrade", "--refresh", "-y"]
RUN ["dnf", "install", "-y", "bash-completion", "ant", "java-17-openjdk", "java-17-openjdk-devel", "java-17-openjdk-jmods", "java-21-openjdk", "java-21-openjdk-devel", "java-21-openjdk-jmods","java-latest-openjdk", "java-latest-openjdk-devel", "java-latest-openjdk-jmods", "maven", "maven-openjdk11", "git", "git-extras", "wget", "unzip", "procps-ng", "file"]

RUN ["useradd", "-u", "185", "-m", "jboss"]
COPY --chown=jboss:jboss --chmod=0755 bin/* /home/jboss/bin/

ENV PATH="/home/jboss/bin/:${PATH}"

USER jboss
WORKDIR /home/jboss

ENV HOME="/home/jboss"
ENV TS_FOLDER="${HOME}"
ENV TCK_LOG_DIR="${TS_FOLDER}/tmp/logs"
ENV TS_HOME="${TS_FOLDER}/jakartaeetck"
ENV TCK_RUNNER="${TS_FOLDER}/wildfly-ee11-tck-runner"
ENV JEETCK_MODS="$TS_FOLDER/cts-10-mods"
ENV JAVAEE_HOME_RI="$TS_FOLDER/glassfish7/glassfish"
ENV DERBY_HOME="$JAVAEE_HOME_RI/../javadb"
ENV DERBY_OPTS="-Djava.net.preferIPv4Stack=true"
ENV JBOSS_HOME="${TS_FOLDER}/wildfly"
ENV WFLY_HOME="${TS_FOLDER}/wildfly"
ENV JAVAEE_HOME="${JBOSS_HOME}"
ENV ANT_OPTS="-Djavax.xml.accessExternalStylesheet=all -Djavax.xml.accessExternalSchema=all -Djavax.xml.accessExternalDTD=file,http -Duser.home=${HOME}"

# Add aliases
COPY --chown=jboss:jboss --chmod=0700 jboss-bashrc "${HOME}/.bashrc"

RUN ["/bin/bash", "/home/jboss/bin/tck-env.sh"]

CMD ["/bin/bash", "/home/jboss/bin/tck-init.sh", "-r", "/bin/bash"]
#CMD ["/bin/bash"]
