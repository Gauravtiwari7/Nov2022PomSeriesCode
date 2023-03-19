pipeline 
{
    agent any
    stages 
    {
        stage('Build') 
        {
            steps
            {
                 echo("build the project")
            }
        }
        stage('Deploy to DEV') 
        {
            steps
            {
                 echo("deploy to DEV")
            }
        }
        stage('Deploy to QA') 
        {
            steps
            {
                 echo("deploy to QA")
            }
        }
        stage("Run regression automation test cases")
        {
            steps
            {
                echo("Run regression automation test cases")
            }
        }
        stage('Deploy to Stage') 
        {
            steps
            {
                echo('Deploy to stage')
            }
        }
        stage('Run sanity automation test case') 
        {
           steps 
           {
                echo('Run sanity automation test case')
                    
                
            }
        }
        stage('Deploy to prod')
        {
            steps
            {
                    echo('Deploy to prod')
            }
        }
        
    }    
}