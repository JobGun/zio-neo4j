package jobgun.zio.neo4j

// NEO4J Imports:
import org.neo4j.driver.summary.ResultSummary

// GSON Imports:
import com.google.gson.GsonBuilder

// ZIO Imports:
import zio.json.*

/** Extensions of the ResultSummary type to make it more Scala friendly.
  */
extension (result: ResultSummary)
  /** Converts a result summary into a json string
    */
  inline def asJson: String =
    GsonBuilder().disableHtmlEscaping().create().toJson(result)

  /** Converts a ResultSummary to a custom type.
    *
    * @param T
    *   The type we wish to decode our JSON to. Requires a given JsonDecoder
    *   instance.
    */
  inline def as[T: JsonDecoder]: Either[String, T] =
    result.asJson.fromJson[T]
