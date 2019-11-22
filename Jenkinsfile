node{
  
  stage('SCM Checkout'){
  git 'https://github.com/shahvic/ProjectManagement'
  }
  stage('install'){
  def mvnHome = tool name: 'Maven', type: 'maven'
  bat "{mvnHome}/bin/mvn package"
  }

}
