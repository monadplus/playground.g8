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

val scalaTestV = "$scalaTestV$"
val scalaCheckV = "$scalaCheckV$"

val kindProjectorV = "$kindProjectorVersion$"
val betterMonadicForV = "$betterMonadicForVersion$"

lazy val commonDependencies = Seq(
  "org.typelevel" %% "cats-core" % catsV,
  "org.typelevel" %% "cats-kernel" % catsV,
  "org.typelevel" %% "cats-macros" % catsV,
  "org.typelevel" %% "cats-free" % catsV,
  "org.typelevel" %% "cats-laws" % catsV,
  "org.typelevel" %% "kittens" % kittensV,
  "org.typelevel" %% "alleycats-core" % catsV,
  "org.typelevel" %% "mouse" % mouseV,
  "org.typelevel" %% "cats-mtl-core" % catsMtlV,
  "org.typelevel" %% "cats-effect" % catsEffectV,
  "co.fs2" %% "fs2-core" % fs2V,
  "co.fs2" %% "fs2-io" % fs2V,
  "org.http4s" %% "http4s-dsl" % http4sV,
  "org.http4s" %% "http4s-blaze-server" % http4sV,
  "org.http4s" %% "http4s-blaze-client" % http4sV,
  "org.http4s" %% "http4s-circe" % http4sV,
  "io.circe" %% "circe-core" % circeV,
  "io.circe" %% "circe-generic" % circeV,
  "io.circe" %% "circe-parser" % circeV,
  "org.tpolecat" %% "doobie-core" % doobieV,
  "org.tpolecat" %% "doobie-h2" % doobieV,
  "org.tpolecat" %% "doobie-hikari" % doobieV,
  "org.tpolecat" %% "doobie-postgres" % doobieV,
  "com.github.pureconfig" %% "pureconfig" % pureConfigV,
  "io.monadplus" %% "equality-core" % equalityV,

  "org.scalatest" %% "scalatest" % scalaTestV % Test,
  "org.scalacheck" %% "scalacheck" % scalaCheckV % Test,
  "org.tpolecat" %% "doobie-scalatest" % doobieV % Test,
  "org.typelevel" %% "cats-testkit" % catsV % Test
)

lazy val compilerFlags = Seq(
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
lazy val commonSettings = Seq(
  organization := "$organization$",
  scalaVersion := "$scala_version$",
  licenses := Seq("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0.html")),
  description := "$project_description$",
  parallelExecution in Test := true,
  fork in Test := true,
  addCompilerPlugin("org.spire-math" % "kind-projector" % kindProjectorV cross CrossVersion.binary),
  addCompilerPlugin("com.olegpy" %% "better-monadic-for" % betterMonadicForV),
  libraryDependencies ++= commonDependencies
)

lazy val `$name$` = project
  .in(file("."))
  .settings(commonSettings)
  .aggregate(core)

lazy val core = project
  .in(file("core"))
  .settings(commonSettings)
  .settings(
    name := "$name$"
  )
