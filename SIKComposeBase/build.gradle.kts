plugins {
    `maven-publish`
}

/**
 * Reference doc:
 * https://docs.gradle.org/current/dsl/org.gradle.api.publish.maven.MavenPublication.html#org.gradle.api.publish.maven.MavenPublication:artifact(java.lang.Object)
 * https://docs.gradle.org/current/userguide/publishing_setup.html
 */
val androidLibrary = the<com.android.build.gradle.LibraryExtension>()
val androidJavadocs = tasks.create("androidJavadocs", Javadoc::class.java) {
    isFailOnError = false
    source(androidLibrary.sourceSets["main"].java.srcDirs)
    options.encoding = "UTF-8"
    doFirst {
        classpath += project.files(androidLibrary.bootClasspath.joinToString(File.pathSeparator))
    }
}

val androidJavadocsJar = tasks.create("androidJavadocsJar", Jar::class.java) {
    dependsOn(androidJavadocs)
    archiveClassifier = "javadoc"
    from(androidJavadocs.destinationDir)
}

val androidSourcesJar = tasks.create("androidSourcesJar", Jar::class.java) {
    archiveClassifier = "sources"
    from(androidLibrary.sourceSets["main"].java.srcDirs)
}

if (JavaVersion.current().isJava8Compatible) {
    allprojects {
        tasks.withType<Javadoc>().configureEach {
            options.encoding += "Xdoclint:none"
            options.encoding += "-quiet"
        }
    }
}

afterEvaluate {


    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "com.example"
                artifactId = "your-artifact-id"
                version = "1.0.0"

                afterEvaluate {
                    if (plugins.hasPlugin("com.android.application") || plugins.hasPlugin("com.android.library")) {
                        from(components["release"])
                    }
                }

                if (plugins.hasPlugin("com.android.application") || plugins.hasPlugin("com.android.library")) {
                    artifact(androidSourcesJar)
                    artifact(androidJavadocsJar)
                }
            }
        }
    }
}
