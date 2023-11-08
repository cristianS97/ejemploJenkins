job('Ejemplo-Job-DSL-2') {
    description('Job DSL de ejemplo para el curso de Jenkins')
    
    scm {
        git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node -> 
        node / gitConfigName('Cristian')
        node / gitConfigEmail('correo@correo.com')
        }
    }
    
    parameters {
        stringParam('Nombre', defaultValue='Pepe', description='Parametro de cadena para el job booleano')
        choiceParam('Planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Urano', 'Neptuno'])
        booleanParam('Agente', false, description='¿Es un agrente?')
    }
    
    triggers {
        cron('H/7 * * * *')
    }
    
    steps {
        shell("bash jobscript.sh")
    }
    
    publishers {
        //mailer('correo@correo.com', true, true)
        slackNotifier {
        notifyAborted(true)
        notifyEveryFailure(true)
        notifyNotBuilt(false)
        notifyUnstable(false)
        notifyBackToNormal(true)
        notifySuccess(false)
        notifyRepeatedFailure(false)
        startNotification(false)
        includeTestSummary(false)
        includeCustomMessage(false)
        customMessage('Mensaje creado con un job dsl')
        sendAs(null)
        commitInfoChoice('NONE')
        teamDomain(null)
        authToken(null)
        }
    }
}
