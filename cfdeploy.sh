cf api api.hackney.cf-app.com --skip-ssl-validation
cf login -u kbhatia@cloudbees.com -p wrubuve8UT -o cloudbees -s development
cf push petclinic -p target/petclinic.war
