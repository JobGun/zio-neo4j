package jobgun.zio.neo4j

trait Neo4jRequestService {
  // ZIO Imports:
  import zio.RIO

  // NEO4J Imports:
  import org.neo4j.driver.summary.ResultSummary
  import org.neo4j.driver.Record

  // SCALA Imports:
  import scala.jdk.CollectionConverters.*

  /** Executes a query given a Neo4J connection in the environment
    *
    * @param query
    *   The executable query.
    */
  inline def query(
      query: String
  ): RIO[Neo4jConnection, ResultSummary]
}
