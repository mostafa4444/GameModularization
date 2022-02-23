apply{
    from("$rootDir/library-build.gradle")
}

plugins{
    kotlin(KotlinPlugins.serialization) version Kotlin.version
    id(SqlDelight.plugin)
}

dependencies{

    "implementation"(project(path = Modules.heroDomain))

    "implementation"(Ktor.core)

    "implementation"(Ktor.clientSerialization)

    "implementation"(Ktor.android)

    "implementation"(SqlDelight.runtime)

}

sqldelight{
    database("HeroDatabase"){
        packageName = "com.game.hero_datasource.cache"
        sourceFolders = listOf("sqlDelight")
    }
}
