plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

architectury {
    platformSetupLoomIde()
    fabric()
}

base.archivesName.set("${rootProject.property("archives_base_name").toString()}-fabric")

loom {
    accessWidenerPath.set(project(":winterly-common").loom.accessWidenerPath)
}

val common: Configuration by configurations.creating
val shadowCommon: Configuration by configurations.creating // Don't use shadow from the shadow plugin because we don't want IDEA to index this.
val developmentFabric: Configuration = configurations.getByName("developmentFabric")

configurations {
    compileClasspath.get().extendsFrom(configurations["common"])
    runtimeClasspath.get().extendsFrom(configurations["common"])
    developmentFabric.extendsFrom(configurations["common"])
}

repositories {
	maven {
        url = uri("https://api.modrinth.com/maven")
    }
	maven { url = uri("https://maven.ladysnake.org/releases") } //CCA
	maven { url = uri("https://maven.terraformersmc.com") } //trinkets
	maven { url = uri("https://maven.wispforest.io") } //owo-lib
	maven { url = uri("https://maven.shedaniel.me/") } //cloth-config
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${rootProject.property("fabric_loader_version")}")
    modApi("net.fabricmc.fabric-api:fabric-api:${rootProject.property("fabric_api_version")}")

	modApi("dev.emi:trinkets:${rootProject.property("trinkets_version")}")
    modApi("io.wispforest:owo-lib:${rootProject.property("owo_lib")}")
	modApi("me.shedaniel.cloth:cloth-config-fabric:${rootProject.property("cloth_config")}") { exclude("net.fabricmc.fabric-api") }
	modApi("com.terraformersmc:modmenu:${rootProject.property("modmenu_version")}")

	modLocalRuntime("dev.emi:emi-fabric:${rootProject.property("emi_version")}")

    common(project(":winterly-common", configuration = "namedElements")) { isTransitive = false }
    shadowCommon(project(":winterly-common", configuration = "transformProductionFabric")) { isTransitive = false }
}

val javaComponent = components.getByName<AdhocComponentWithVariants>("java")
javaComponent.withVariantsFromConfiguration(configurations["sourcesElements"]) {
    skip()
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand("version" to project.version)
        }
    }

    shadowJar {
        exclude("architectury.common.json")
        configurations = listOf(project.configurations["shadowCommon"])
        archiveClassifier.set("dev-shadow")
    }

    remapJar {
        injectAccessWidener.set(true)
        inputFile.set(shadowJar.flatMap { it.archiveFile })
        dependsOn(shadowJar)
//        archiveClassifier.set("fabric")
    }

    jar {
        archiveClassifier.set("dev")
    }

    sourcesJar {
        val commonSources = project(":winterly-common").tasks.getByName<Jar>("sourcesJar")
        dependsOn(commonSources)
        from(commonSources.archiveFile.map { zipTree(it) })
    }
}
