package jobgun.zio.neo4j

import zio.test.*
import zio.*
import org.neo4j.driver.Config
import zio.test.Assertion._

// object Neo4jConnectionTest extends ZIOSpecDefault:
//   // val connectionLayer = ZLayer.fromZIO(
//   //   Neo4jConnection.fromConnectionInfo("0.0.0.0:7687", "system", "", Config.defaultConfig)
//   // )

//   def spec = suite("ConnectionTest")(
//     test("foo") {
//       assertZIO(
//         Neo4jConnection.fromConnectionInfo("bolt://0.0.0.0:7687", "system", "", Config.defaultConfig)
//       )(anything)
//     },
//     test("foo bar") {
//       assertTrue(true)
//     },
//     test("foo bar baz") {
//       assertTrue(true)
//     }
//   )
// end Neo4jConnectionTest