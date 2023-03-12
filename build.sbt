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
    pgpPublicRing := file("/tmp/public.asc"),
    pgpSecretRing := file("/tmp/secret.asc"),
    pgpPassphrase := sys.env.get("PGP_PASSWORD").map(_.toArray),
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

name := "zio-neo4j"
scalafmtOnCompile := false

enablePlugins(BuildInfoPlugin, WebsitePlugin)
buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion, isSnapshot)
buildInfoPackage := "zio.neo4j"

val zioVersion = "2.0.0"

libraryDependencies ++= Seq(
  "dev.zio"                %% "zio-test"                % zioVersion % "test",
  "dev.zio"                %% "zio-test-sbt"            % zioVersion % "test",
  "dev.zio"                %% "zio"                     % zioVersion,
  "dev.zio"                %% "zio-json"                % "0.4.2",
  "com.google.code.gson"   % "gson"                     % "2.10.1",
  "org.neo4j.driver"       % "neo4j-java-driver"        % "5.6.0"
)

scalacOptions --= Seq("-Xlint:nullary-override")

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("check", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")
