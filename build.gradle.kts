allprojects {
    repositories {
        mavenLocal()
        google()
        jcenter()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

tasks.register("clean", Delete::class) {
    group = "build"
    description = "cleans every project buildDir"
    delete(buildDir)
}

tasks.register("cleanRootOnly", Delete::class) {
    group = "build"
    description = "cleans only the rootProject buildDir"
    delete(rootProject.buildDir)
}
