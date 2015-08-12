#!/bin/bash
cf api api.hackney.cf-app.com --skip-ssl-validation
cf push petclinic -p target/petclinic.war
