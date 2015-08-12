#!/bin/bash
cf api api.hackney.cf-app.com --skip-ssl-validation
cf login -u kbhatia@cloudbees.com -p Jenkins!23 -o cloudbees -s development
cf push petclinic -p target/petclinic.war
