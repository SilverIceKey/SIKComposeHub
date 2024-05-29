plugins {
    `maven-publish`
}

/**
 * Reference doc:
 * https://docs.gradle.org/current/dsl/org.gradle.api.publish.maven.MavenPublication.html#org.gradle.api.publish.maven.MavenPublication:artifact(java.lang.Object)
 * https://docs.gradle.org/current/userguide/publishing_setup.html
 */

afterEvaluate {
    if (plugins.hasPlugin("com.android.application") || plugins.hasPlugin("com.android.library")) {
        /** Android doc */
        val androidJavadocs = tasks.create("androidJavadocs", Javadoc::class.java) {
            isFailOnError = false
            source = project.fileTree("src/main/java").apply { include("**/*.java") }
            options.encoding = "UTF-8"
            doFirst {
                classpath += project.files(android.bootClasspath.joinToString(File.pathSeparator))
            }
        }

        /** Android doc jar */
        val androidJavadocsJar = tasks.create("androidJavadocsJar", Jar::class.java) {
            dependsOn(androidJavadocs)
            archiveClassifier.set("javadoc")
            from(androidJavadocs.destinationDir)
        }

        /** Android source jar */
        val androidSourcesJar = tasks.create("androidSourcesJar", Jar::class.java) {
            archiveClassifier.set("sources")
            from(project.fileTree("src/main/java").apply { include("**/*.java") })
        }
    } else if (plugins.hasPlugin("java")) {
        /** Java source jar */
        val sourcesJar = tasks.create("sourcesJar", Jar::class.java) {
            dependsOn("classes")
            archiveClassifier.set("sources")
            from(sourceSets["main"].allSource)
        }

        /** Java doc jar */
        val javadocJar = tasks.create("javadocJar", Jar::class.java) {
            dependsOn("javadoc")
            archiveClassifier.set("javadoc")
            from(tasks.named<Javadoc>("javadoc").get().destinationDir)
        }
    }

    if (JavaVersion.current().isJava8Compatible) {
        allprojects {
            tasks.withType<Javadoc>().configureEach {
                options.addStringOption("Xdoclint:none", "-quiet")
            }
        }
    }

    /** Publish library with doc and source code */
    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "com.example"
                artifactId = "your-artifact-id"
                version = "1.0.0"

                afterEvaluate {
                    if (plugins.hasPlugin("com.android.application") || plugins.hasPlugin("com.android.library")) {
                        from(components["release"])
                    } else if (plugins.hasPlugin("java")) {
                        from(components["java"])
                    }
                }

                if (plugins.hasPlugin("com.android.application") || plugins.hasPlugin("com.android.library")) {
                    artifact(tasks.named("androidSourcesJar"))
                    artifact(tasks.named("androidJavadocsJar"))
                } else if (plugins.hasPlugin("java")) {
                    artifact(tasks.named("sourcesJar"))
                    artifact(tasks.named("javadocJar"))
                }
            }
        }
    }
}
