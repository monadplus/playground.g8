resolvers in Global ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.bintrayRepo("io-monadplus", "maven")
)

lazy val contributors = Seq(
  "monadplus" -> "Arnau Abella"
)

val catsV = "$catsV$"
val kittensV = "$kittensV$"
val catsEffectV = "$catsEffectV$"
val mouseV = "$mouseV$"
val catsMtlV = "$catsMtlV$"
val fs2V = "$fs2V$"
val http4sV = "$http4sV$"
val circeV = "$circeV$"
val doobieV = "$doobieV$"
val pureConfigV = "$pureConfigV$"
val equalityV = "$equalityV$"
val shapelessV = "$shapelessV$"
val refinedV = "$refinedV$"
val simulacrumV = "$simulacrumV$"
val catsParV = "$catsParV$"
val catsTimeV = "$catsTimeV$"
val fuuidV = "$fuuidV$"
val lineBackerV = "$lineBackerV$"
val meowMtlV = "$meowMtlV$"

// Test
val scalaTestV = "$scalaTestV$"
val scalaCheckV = "$scalaCheckV$"

// Pluguins
val kindProjectorV = "$kindProjectorVersion$"
val betterMonadicForV = "$betterMonadicForVersion$"

lazy val dependencies =
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-macros" % catsV,
    "org.typelevel" %% "cats-kernel" % catsV,
    "org.typelevel" %% "cats-core" % catsV,
    "org.typelevel" %% "cats-laws" % catsV,
    "org.typelevel" %% "cats-free" % catsV,
    "org.typelevel" %% "cats-testkit" % catsV % Test,

    "org.typelevel" %% "alleycats-core" % catsV,

    "org.typelevel" %% "kittens" % kittensV,

    "org.typelevel" %% "mouse" % mouseV,
    "com.olegpy" %% "meow-mtl" % meowMtlV,

    "org.typelevel" %% "cats-mtl-core" % catsMtlV,

    "com.chuusai" %% "shapeless" % shapelessV,

    "org.typelevel" %% "cats-effect" % catsEffectV,
    "org.typelevel" %% "cats-effect-laws" % catsEffectV % Test,

    "co.fs2" %% "fs2-core" % fs2V,
    "co.fs2" %% "fs2-io" % fs2V,

    "io.chrisdavenport" %% "cats-par" % catsParV,
    "io.chrisdavenport" %% "cats-time" % catsTimeV,
    "io.chrisdavenport" %% "linebacker" % lineBackerV,
    "io.chrisdavenport" %% "fuuid" % fuuidV,

    "org.http4s" %% "http4s-dsl" % http4sV,
    "org.http4s" %% "http4s-blaze-server" % http4sV,
    "org.http4s" %% "http4s-blaze-client" % http4sV,
    "org.http4s" %% "http4s-circe" % http4sV,

    "io.circe" %% "circe-core" % circeV,
    "io.circe" %% "circe-generic" % circeV,
    "io.circe" %% "circe-parser" % circeV,
  //  "io.circe" %% "circe-fs2" % circeV,

    "org.tpolecat" %% "doobie-core" % doobieV,
    "org.tpolecat" %% "doobie-h2" % doobieV,
    "org.tpolecat" %% "doobie-hikari" % doobieV,
    "org.tpolecat" %% "doobie-postgres" % doobieV,
    "org.tpolecat" %% "doobie-scalatest" % doobieV % Test,

    "com.github.pureconfig" %% "pureconfig" % pureConfigV,

    "io.monadplus" %% "equality-core" % equalityV,

    "com.github.mpilquist" %% "simulacrum" % simulacrumV,

    "eu.timepit" %% "refined" % refinedV,
    "eu.timepit" %% "refined-scalacheck" % refinedV % Test,

    "com.github.mpilquist" %% "simulacrum" % simulacrumV,

    "org.scalatest" %% "scalatest" % scalaTestV % Test,

    "org.scalacheck" %% "scalacheck" % scalaCheckV % Test
  )

lazy val compilerOptions = Seq(
  scalacOptions ++= Seq(
    "-feature",
    "-deprecation",
    "-language:implicitConversions",
    "-language:higherKinds"
  ) ++ (if (scalaBinaryVersion.value.startsWith("2.12"))
          List(
            "-Xlint",
            "-Xfatal-warnings",
            "-Yno-adapted-args",
            "-Ywarn-value-discard",
            "-Ywarn-unused-import",
            "-Ypartial-unification"
          )
        else Nil),
  scalacOptions in (Test, compile) --= Seq(
    "-Ywarn-unused-import",
    "-Xlint",
    "-Xfatal-warnings"
  )
)

lazy val typeSystemEnhancements = Seq(
  addCompilerPlugin("org.typelevel" %% "kind-projector" % kindProjectorV),
  addCompilerPlugin("com.olegpy" %% "better-monadic-for" % betterMonadicForV)
)

lazy val commonSettings = Seq(
  organization := "$organization$",
  scalaVersion := "$scala_version$"
)

lazy val root = project
  .in(file("."))
  .settings(commonSettings)
  .aggregate(core, benchmark)

lazy val core = project
  .in(file("core"))
  .settings(
    name := "$name$"
  )
  .settings(
    commonSettings,
    compilerOptions,
    typeSystemEnhancements,
    dependencies
  )

lazy val benchmark = project
  .in(file("benchmark"))
  .settings(
    name := "$name$-benchmark"
  )
  .settings(
    commonSettings,
    compilerOptions,
    typeSystemEnhancements,
    dependencies
  )
  .enablePlugins(JmhPlugin)
  .dependsOn(core)
