package jobgun.zio.neo4j

// ZIO Imports:
import zio.ZLayer

// NEO4J Imports:
import org.neo4j.driver.Config

object Neo4j:
  /** Allows us the create a Neo4jConnection layer.
    *
    * @param uri
    *   A uri to access the database instance
    * @param user
    *   A database username
    * @param pwd
    *   A password to a database
    * @param config
    *   A connection configuration
    */
  lazy val live = (uri: String, user: String, pwd: String, conf: Config) =>
    ZLayer fromZIO Neo4jConnection.fromConnectionInfo(uri, user, pwd, conf)
end Neo4j
