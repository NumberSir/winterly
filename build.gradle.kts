import groovy.lang.Closure
import io.github.pacifistmc.forgix.plugin.ForgixMergeExtension
import net.fabricmc.loom.api.LoomGradleExtensionAPI

plugins {
    java
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("dev.architectury.loom") version "1.7-SNAPSHOT" apply false
    id("io.github.pacifistmc.forgix") version "1.2.9"
}

architectury {
    minecraft = rootProject.property("minecraft_version").toString()
}

@Suppress("UNCHECKED_CAST")
forgix {
    group = "ru.pinkgoosik.winterly"
    mergedJarName = "winterly-${rootProject.property("mod_version").toString()}.jar"
    outputDir = "build/libs/merged"

    fabric(
            closureOf<ForgixMergeExtension.FabricContainer> {
                projectName = "winterly-fabric"
            } as Closure<ForgixMergeExtension.FabricContainer>
    )
    neoforge(
            closureOf<ForgixMergeExtension.NeoForgeContainer> {
                projectName = "winterly-neoforge"
            } as Closure<ForgixMergeExtension.NeoForgeContainer>
    )
}

subprojects {
    apply(plugin = "dev.architectury.loom")
    apply(plugin = "architectury-plugin")

    val loom = project.extensions.getByName<LoomGradleExtensionAPI>("loom")
    loom.silentMojangMappingsLicense()

    dependencies {
        "minecraft"("com.mojang:minecraft:${rootProject.property("minecraft_version")}")

        // The template comes with Mojang mappings, but you may use other mappings such as Yarn and Quilt if you want.
        @Suppress("UnstableApiUsage")
        "mappings"(loom.layered {
            officialMojangMappings()
            parchment("org.parchmentmc.data:parchment-${rootProject.property("minecraft_version")}:${rootProject.property("parchment_version")}@zip")
        })
    }

    tasks.processResources {
        val expandProps = mapOf(
                "name" to rootProject.property("mod_name"),
                "version" to rootProject.property("mod_version"),
                "description" to rootProject.property("mod_description"),
                "mod_id" to rootProject.property("mod_id"),
				"homepage_url" to rootProject.property("homepage_url"),
				"sources_url" to rootProject.property("sources_url"),
                "issue_tracker_url" to rootProject.property("issue_tracker_url"),
                "license" to rootProject.property("license"),
                "minecraft_version" to rootProject.property("minecraft_version"),
                "minecraft_version_range" to rootProject.property("minecraft_version_range"),
                "minecraft_version_range_fabric" to rootProject.property("minecraft_version_range_fabric"),
                "fabric_loader_version" to rootProject.property("fabric_loader_version"),
                "fabric_version" to rootProject.property("fabric_api_version"),
                "neoforge_version" to rootProject.property("neoforge_version"),
                "neoforge_version_range" to rootProject.property("neoforge_version_range")
        )

        inputs.properties(expandProps)

        filesMatching(listOf("pack.mcmeta", "fabric.mod.json", "META-INF/neoforge.mods.toml", "*.mixins.json")) {
            expand(expandProps)
        }
    }
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "architectury-plugin")
    apply(plugin = "maven-publish")

    base.archivesName.set(rootProject.property("archives_base_name").toString())
    version = rootProject.property("mod_version").toString()
    group = rootProject.property("maven_group").toString()

    repositories {
        // Add repositories to retrieve artifacts from in here.
        // You should only use this when depending on other mods because
        // Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
        // See https://docs.gradle.org/current/userguide/declaring_repositories.html
        // for more information about repositories.

        maven("https://maven.neoforged.net/releases/") {
            name = "NeoForged"
        }

        maven("https://maven.parchmentmc.org") {
            name = "ParchmentMC"
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release = 21
    }

    java {
        withSourcesJar()

        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}
