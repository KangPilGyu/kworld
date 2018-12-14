#!/bin/bash
sudo mv -f /home/ubuntu/app/travis/build/target/ROOT.war /var/lib/tomcat8/webapps
sudo service tomcat8 restart