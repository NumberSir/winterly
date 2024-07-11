plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

architectury {
    platformSetupLoomIde()
    neoForge()
}

base.archivesName.set("${rootProject.property("archives_base_name").toString()}-neoforge")

loom {
    accessWidenerPath.set(project(":winterly-common").loom.accessWidenerPath)
}

val common: Configuration by configurations.creating
val shadowCommon: Configuration by configurations.creating // Don't use shadow from the shadow plugin because we don't want IDEA to index this.
val developmentNeoForge: Configuration = configurations.getByName("developmentNeoForge")

configurations {
    compileClasspath.get().extendsFrom(configurations["common"])
    runtimeClasspath.get().extendsFrom(configurations["common"])
    developmentNeoForge.extendsFrom(configurations["common"])
}

repositories {
  maven { url = uri("https://maven.theillusivec4.top/") }
  maven { url = uri("https://maven.shedaniel.me/") }
  maven { url = uri("https://maven.terraformersmc.com") }
}

dependencies {
    neoForge("net.neoforged:neoforge:${rootProject.property("neoforge_version")}")

	modCompileOnly("top.theillusivec4.curios:curios-neoforge:${rootProject.property("curios_version")}:api")
    // Use the full Curios API jar at runtime
    modCompileOnly("top.theillusivec4.curios:curios-neoforge:${rootProject.property("curios_version")}")

	modApi("me.shedaniel.cloth:cloth-config-neoforge:${rootProject.property("cloth_config")}")

    modLocalRuntime("dev.emi:emi-neoforge:1.1.10+1.21")

    common(project(":winterly-common", configuration = "namedElements")) { isTransitive = false }
    shadowCommon(project(":winterly-common", configuration = "transformProductionNeoForge")) { isTransitive = false }
}

val javaComponent = components.getByName<AdhocComponentWithVariants>("java")
javaComponent.withVariantsFromConfiguration(configurations["sourcesElements"]) {
    skip()
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("META-INF/neoforge.mods.toml") {
            expand("version" to project.version)
        }
    }

    shadowJar {
        exclude("fabric.mod.json")
        exclude("architectury.common.json")

        configurations = listOf(project.configurations["shadowCommon"])
        archiveClassifier.set("dev-shadow")
    }

    remapJar {
        inputFile.set(shadowJar.flatMap { it.archiveFile })
        dependsOn(shadowJar)
//        archiveClassifier.set("neoforge")
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
