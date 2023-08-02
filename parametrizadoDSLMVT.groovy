job('primer-mvt-job-dsl'){
  description('Job DSL de ejemplo para el curso jenkins')
  scm {
    git('https://github.com/macloujulian/jenkins.job.parametrizado.git','main'){ node ->
      node / gitConfigName('michelvatre')
      node / gitConfigEmail('michelvatre@gmail.com')
    }
  }
  parameters {
  	stringParam('nombre', defaultValue = 'Default', description='Parametro cadena para el Job Boleano')
    choiceParam('planeta', ['Tierra','Marte','Jupiter','Pluton','Neptuno','Urano'])
    booleanParam('agente',false)
  }
  triggers {
  	cron('H/7 * * * *')
  }
  steps {
    shell("bash jobscript.sh")
  }
  publishers {
    mailer('michelvatre94@gmail.com', true, true)
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
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
