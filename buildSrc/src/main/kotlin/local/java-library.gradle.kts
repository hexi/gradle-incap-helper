package local

plugins {
    `java-library`
}
apply(plugin = "net.ltgt.errorprone")
apply(plugin = "net.ltgt.nullaway")

java.sourceCompatibility = JavaVersion.VERSION_1_8
if (JavaVersion.current().isJava9Compatible) {
    tasks.withType<JavaCompile>().configureEach {
        options.compilerArgs.addAll(listOf("--release", java.targetCompatibility.majorVersion))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    "errorprone"("com.google.errorprone:error_prone_core:2.5.1")
    "errorprone"("com.uber.nullaway:nullaway:0.8.0")
    "errorproneJavac"("com.google.errorprone:javac:9+181-r4173-1")
}

tasks {
    withType<JavaCompile>().configureEach {
        options.compilerArgs.addAll(listOf("-Werror", "-Xlint:all"))
    }
    javadoc {
        (options as CoreJavadocOptions).addBooleanOption("Xdoclint:all,-missing", true)
        if (JavaVersion.current().isJava9Compatible) {
            (options as CoreJavadocOptions).addBooleanOption("html5", true)
        }
    }
}
