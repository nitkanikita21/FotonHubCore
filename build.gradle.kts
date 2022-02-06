plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.papermc.paperweight.userdev") version "1.3.3"
    id("xyz.jpenilla.run-paper") version "1.0.6"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
}

group = "dev.foton"
version = "0.1"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://jitpack.io")
    maven("https://repo.dmulloy2.net/repository/public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    paperDevBundle("1.18.1-R0.1-SNAPSHOT")
    compileOnly(files("libs/AdvancedColorApi.jar"))
    compileOnly("com.comphenix.protocol:ProtocolLib:4.7.0")
    compileOnly("net.luckperms:api:5.3")
    compileOnly("me.clip:placeholderapi:2.11.1")
    // compileOnly("org.spigotmc:spigot:1.18.1-R0.1-SNAPSHOT")
}

// Здесь генерируется plugin.yml, настраивайте его тут.
bukkit {
    name = "Hubcore"
    main = "dev.foton.hubcore.Main"
    apiVersion = "1.18"
    authors = listOf("FotonDevTeam")
    depend = listOf("ProtocolLib","LuckPerms","PlaceholderAPI")
    commands {
        register("menu") {
            usage = "/menu"
        }
        register("games") {
            usage = "/games"
        }
        register("hats") {
            usage = "/hats"
        }
        register("spawnnpc") {
            usage = "/spawnnpc"
        }
    }
}

tasks {
    // Здесь можно запустить новейший билд пейпера для теста плагинов.
    runServer {
        runDirectory(file("$rootDir/run"))
        minecraftVersion("1.18")
    }

    assemble {
        dependsOn(":reobfJar")
    }
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
    withType(JavaCompile::class.java) {
        options.encoding = "UTF-8"
    }
    shadowJar {
        destinationDirectory.set(file("$rootDir/out"))
    }
}