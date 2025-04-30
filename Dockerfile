# Step 1: Use an official Ubuntu base image
FROM ubuntu:20.04

# Step 2: Set working directory
WORKDIR /app
ENV DEBIAN_FRONTEND=noninteractive

# Step 3: Update and install required packages
RUN apt-get update && apt-get install -y --fix-missing \
    curl \
    vim \
    unzip \
    openjdk-17-jdk \
    openssh-server \
    git \
    ca-certificates \
    gnupg \
    && curl -fsSL https://deb.nodesource.com/setup_20.x | bash - \
    && apt-get install -y nodejs \
    && rm -rf /var/lib/apt/lists/*

# Step 4: Copy current directory contents into the container
# COPY . /app

# Step 5: Install SFTP and configure SSH
RUN mkdir /var/run/sshd
RUN echo 'root:password' | chpasswd
RUN sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config
RUN sed -i 's/#PasswordAuthentication yes/PasswordAuthentication yes/' /etc/ssh/sshd_config

# Step 6: Set environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# Step 7: Expose necessary ports
EXPOSE 22
EXPOSE 3000
EXPOSE 8000

# Step 8 : time zone check
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Last : Start SSH service
CMD ["/bin/bash", "-c", "service ssh start && tail -f /dev/null"]
