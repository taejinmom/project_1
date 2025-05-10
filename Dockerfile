# Step 1: Use an official Ubuntu base image
FROM ubuntu:20.04

# Step 2: Set working directory
WORKDIR /app
ENV DEBIAN_FRONTEND=noninteractive

RUN mkdir -p /app/project_1

# Step 3: Update and install required packages
RUN apt-get update && apt-get install -y --fix-missing \
    curl \
    vim \
    unzip \
    openjdk-17-jdk \
    openssh-server \
    git \
    sudo \
    ca-certificates \
    gnupg \
    wget \
    lsb-release \
    && curl -fsSL https://deb.nodesource.com/setup_20.x | bash - \
    && apt-get install -y nodejs \
    && rm -rf /var/lib/apt/lists/*

# Step 4: Install PostgreSQL 17
RUN apt-get update && apt-get install -y wget gnupg lsb-release && \
    wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | gpg --dearmor -o /usr/share/keyrings/postgresql.gpg && \
    echo "deb [signed-by=/usr/share/keyrings/postgresql.gpg] http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list.d/pgdg.list && \
    apt-get update && \
    apt-get install -y postgresql-17 postgresql-client-17 && \
    rm -rf /var/lib/apt/lists/*

# Step 4-1: Initialize PostgreSQL database cluster
# RUN mkdir -p /var/lib/postgresql/17/main && \
#    chown -R postgres:postgres /var/lib/postgresql && \
#    su - postgres -c "/usr/lib/postgresql/17/bin/initdb -D /var/lib/postgresql/17/main"

# Step 4-2: PostgreSQL config (listen on all IPs)
RUN echo "listen_addresses='*'" >> /etc/postgresql/17/main/postgresql.conf && \
    echo "host all all all trust" >> /etc/postgresql/17/main/pg_hba.conf

# Step 4-3: Export PostgreSQL binary path
RUN echo 'export PATH="/usr/lib/postgresql/17/bin:$PATH"' >> /etc/profile && \
    echo 'export PGDATA="/var/lib/postgresql/17/main"' >> /etc/profile

COPY init-db.sh /app/init-db.sh
RUN chmod +x /app/init-db.sh

RUN echo "postgres install success!!"

# Step 5: Install ngrok
RUN curl -s -o ngrok.zip https://bin.equinox.io/c/bNyj1mQVY4c/ngrok-v3-stable-linux-amd64.zip && \
    unzip ngrok.zip && \
    mv ngrok /usr/local/bin/ngrok && \
    chmod +x /usr/local/bin/ngrok && \
    rm ngrok.zip

RUN ngrok config add-authtoken 2FFrA4apgILp5fKzHwGSgxCqg2q_5GMPTAbNgXQpTB6cMfQSo
RUN echo "ngrok install success!!"

# Step 6: Install Jenkins
RUN curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key | tee \
        /usr/share/keyrings/jenkins-keyring.asc > /dev/null && \
    echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
        https://pkg.jenkins.io/debian-stable binary/ | tee \
        /etc/apt/sources.list.d/jenkins.list > /dev/null && \
    apt-get update && \
    apt-get install -y jenkins && \
    apt-get clean

RUN echo "jenkins install success!!"

# Step 7: Configure SSH and SFTP
RUN mkdir /var/run/sshd
RUN echo 'root:password' | chpasswd
RUN sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config
RUN sed -i 's/#PasswordAuthentication yes/PasswordAuthentication yes/' /etc/ssh/sshd_config

# Step 8: Set environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:/usr/lib/postgresql/17/bin:$PATH
ENV PGDATA=/var/lib/postgresql/17/main

# Step 9: Expose necessary ports
EXPOSE 22     
EXPOSE 3000   
EXPOSE 5432   
EXPOSE 8000   
EXPOSE 8080   

# Step 10: Set timezone
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Step 11: Start services
CMD ["/bin/bash", "-c", "\
    service ssh start && \
    service jenkins start && \
    su - postgres -c '/usr/lib/postgresql/17/bin/postgres -D /var/lib/postgresql/17/main' & \
    tail -f /dev/null"]
