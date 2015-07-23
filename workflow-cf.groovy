stage name: 'Build'
node() {
    // COMPILE AND JUNIT
    echo "INFO - Starting build phase"
    def src = 'https://github.com/spring-projects/spring-petclinic'

    git url: src

    //ensureMaven()
    sh 'mvn -o clean package'
    sh 'tar -c -f src.tar src/ pom.xml'
    archive 'src.tar, target/petclinic.war'
    step $class: 'hudson.tasks.junit.JUnitResultArchiver', testResults: 'target/surefire-reports/*.xml'
    echo "INFO - Ending build phase"
}

stage name: 'DevTest', concurrency: 1
node() {
    deploy 'target/petclinic.war', 'development'
    echo "Deployed to Development Env"
}

stage name: 'QA', concurrency: 1
node() {
    // DEPLOY ON THE QA SERVER
    echo "INFO - Starting QA Deploy"
    echo "INFO - QA Deploy complete"
}

stage name: 'Staging', concurrency: 1
checkpoint 'CHOOSE TO ENTER STAGING'

input message: "Does QA look good? If yes, we deploy on staging.", ok: "DEPLOY TO STAGING!"

node() {
    // DEPLOY ON STAGING
    echo "INFO - Starting Staging Deploy"
    echo "INFO - Staging Deploy complete"
}
def deploy(war, id) {
   // def src = 'https://github.com/kishorebhatia/jenkins-demo/'
    //git url: src
    //sh "chmod +x cfdeploy.sh"
    //sh "./cfdeploy.sh"
    sh "cf push petclinic -p ${war}"
}
// FUNCTIONS


/**
 * Deploy Maven on the slave if needed and add it to the path
 */
def ensureMaven() {
    env.PATH = "${tool 'Maven 3.x'}/bin:${env.PATH}"
}
