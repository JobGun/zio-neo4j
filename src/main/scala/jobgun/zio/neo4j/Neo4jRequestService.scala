package jobgun.zio.neo4j

// ZIO Imports:
import zio.{RIO, ZIO}

// NEO4J Imports:
import org.neo4j.driver.summary.ResultSummary
import org.neo4j.driver.Record

// CYPHER Imports:
import org.neo4j.cypherdsl.core.executables.{
  ExecutableStatement,
  ExecutableResultStatement
}

trait Neo4jRequestService:

  // SCALA Imports:
  import scala.jdk.CollectionConverters.*

  /** Executes a write query given a Neo4J connection in the environment
    *
    * @param query
    *   The executable query.
    */
  inline def write(
      query: ExecutableStatement
  ): RIO[Neo4jConnection, ResultSummary]

  /** Executes a read query given a Neo4J connection in the environment
    *
    * @param query
    *   The executable query.
    */
  inline def read(
      query: ExecutableResultStatement
  ): RIO[Neo4jConnection, ResultSummary]
end Neo4jRequestService

object Neo4jRequestService:
  /** Default Neo4jRequestService instance for making managed transactions.
    */
  lazy val defaultManaged: Neo4jRequestService =
    new:
      override inline def write(
          query: ExecutableStatement
      ): RIO[Neo4jConnection, ResultSummary] =
        for
          conn <- ZIO.service[Neo4jConnection]
          result <- ZIO.acquireReleaseWith(conn.acquire)(conn.release(_))(
            session => conn.use(_.executeWrite(query.executeWith(_)))(session)
          )
        yield result

      override inline def read(
          query: ExecutableResultStatement
      ): RIO[Neo4jConnection, ResultSummary] =
        for
          conn <- ZIO.service[Neo4jConnection]
          result <- ZIO.acquireReleaseWith(conn.acquire)(conn.release(_))(
            session => conn.use(_.executeRead(query.executeWith(_)))(session)
          )
        yield result
end Neo4jRequestService
