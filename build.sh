# encoding: utf-8  
#!/bin/bash
sudo mv -f /home/ubuntu/app/travis/build/target/ROOT.war /var/lib/tomcat8/webapps
echo "Tomcat Restart"
sudo service tomcat8 restart
echo "Tomcat Starting"