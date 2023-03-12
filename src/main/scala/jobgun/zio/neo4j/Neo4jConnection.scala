package jobgun.zio.neo4j

// NEO4J Imports:
import org.neo4j.driver.{
  AuthTokens,
  Driver,
  GraphDatabase,
  Config,
  Session
}

// ZIO Imports:
import zio.*

/** Handles the Neo4J driver and methods to access it.
  *
  * @param uri
  *   A uri to access the database instance
  * @param user
  *   A database username
  * @param pwd
  *   A password to a database
  * @param config
  *   // A connection configuration
  */
final case class Neo4jConnection private (
    uri: String,
    user: String,
    pwd: String,
    config: Config
) extends AutoCloseable:

  /** This signature prevents access to the compiler generated copy method.
    */
  private def copy: Unit = ()

  /** Driver instance given the configuration.
    */
  private val driver: Driver =
    GraphDatabase.driver(uri, AuthTokens.basic(user, pwd), config)

  /** Maps a driver session. Note: this is curried in this order for
    * ZIO.acquireReleaseWith.
    *
    * @param f
    *   The map function.
    * @param session
    *   The session to be mapped.
    */
  inline def use[T](f: Session => T)(session: Session): Task[T] =
    ZIO succeed f(session)

  /** Closes a session resource.
    *
    * @param session
    *   The session the be released.
    */
  inline def release(session: Session): UIO[Unit] = ZIO succeed session.close

  /** Provides a new session from the driver.
    */
  inline def acquire: Task[Session] = ZIO attempt driver.session

  /** Closes the driver instance. TODO: Error handling on close. Close may throw
    * an exception.
    */
  override def close: Unit = driver.close
end Neo4jConnection

object Neo4jConnection:

  private def apply(
      uri: String,
      user: String,
      pwd: String,
      config: Config
  ): Task[Neo4jConnection] =
    ZIO.succeed(new Neo4jConnection(uri, user, pwd, config))

  /** Allows us the create a Neo4jConnection instance.
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
  def fromConnectionInfo(
      uri: String,
      user: String,
      pwd: String,
      config: Config
  ): Task[Neo4jConnection] =
    apply(uri, user, pwd, config)
end Neo4jConnection