pipeline{
    agent any
    stages{
        stage('scm'){
            steps{
                git url: "https://github.com/matcha-sofi/webserverpipeline.git"
            }
        }
        stage('ArchiveArtifacts'){
            steps{
                archiveArtifacts '**/*.html'
            }
        }
        stage('Build'){
            steps{
                sshPublisher(publishers:[sshPublisherDesc(configName: 'webserver', [sshTransfer(excludes: '', exeCommand: '', exeTimeout:120000, flatten: true, makeEmptyDirs: false, noDefaultExcludes: false, patternSepatator: '[,]', remoteDirectory: '', remoteDirectorySdf: false, removePrefix: '', sourseFiles: '**/*.html')], usePromotionTimestamp: false, useWorkspaceInpromotion: false, verbose: true)])
            }
        }

    }
}
