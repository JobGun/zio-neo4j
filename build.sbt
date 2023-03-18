lazy val mainScala = "3.1.2"
lazy val allScala  = Seq(mainScala, "2.13.8")

inThisBuild(
  List(
    organization := "com.jobgun",
    projectName := "ZIO-Neo4J",
    mainModuleName := "jobgun.zio.neo4j",
    projectStage := ProjectStage.Development,
    homepage := Some(url("https://github.com/jobgun/zio-neo4j")),
    licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    useCoursier := false,
    version := "0.1.0",
    scalaVersion := mainScala,
    crossScalaVersions := allScala,
    Test / parallelExecution := false,
    Test / fork := true,
    run / fork := true,
    scmInfo := Some(
      ScmInfo(url("https://github.com/jobgun/zio-neo4j/"), "scm:git:git@github.com:jobgun/zio-neo4j.git")
    ),
    developers := List(
      Developer(
        "baovitt",
        "Bradly Ovitt",
        "oobrad76@gmail.com",
        url("https://github.com/baovitt")
      )
    )
  )
)

ThisBuild / publish / skip := true
ThisBuild / publishMavenStyle := true
ThisBuild / versionScheme := Some("early-semver")
ThisBuild / publishTo := Some(
  "GitHub Package Registry " at "https://maven.pkg.github.com/alterationx10/ursula"
)

ThisBuild / credentials += Credentials(
  "GitHub Package Registry", // realm
  "maven.pkg.github.com", // host
  "baovitt", // user
  sys.env.getOrElse("GITHUB_TOKEN", "") // password
)

name := "zio-neo4j"
scalafmtOnCompile := false

enablePlugins(BuildInfoPlugin, WebsitePlugin)
buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion, isSnapshot)
buildInfoPackage := "zio.neo4j"

val zioVersion = "2.0.0"

libraryDependencies ++= Seq(
  "dev.zio"                %% "zio-test"                % zioVersion % Test,
  "dev.zio"                %% "zio-test-sbt"            % zioVersion % Test,
  "dev.zio"                %% "zio-test-magnolia"       % zioVersion % Test,
  "dev.zio"                %% "zio"                     % zioVersion,
  "dev.zio"                %% "zio-json"                % "0.4.2",
  "com.google.code.gson"   % "gson"                     % "2.10.1",
  "org.neo4j.driver"       % "neo4j-java-driver"        % "5.6.0",
  "org.neo4j"              % "neo4j-cypher-dsl"         % "2023.1.0"
)

scalacOptions --= Seq("-Xlint:nullary-override")

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("check", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")
