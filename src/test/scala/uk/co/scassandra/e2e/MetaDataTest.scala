package uk.co.scassandra.e2e

import uk.co.scassandra.{ConnectionToServerStub, PrimingHelper}
import com.datastax.driver.core.Cluster
import uk.co.scassandra.priming.query.When

class MetaDataTest extends PrimingHelper(false) {


  test("Cluster name") {
    val when = When("SELECT * FROM system.local WHERE key='local'")
    val clusterName = "ACCluster"
    val columnTypes = Map("tokens"->"set")
    val rows = List(Map("cluster_name" -> clusterName,
      "partitioner" -> "org.apache.cassandra.dht.Murmur3Partitioner",
      "data_center" -> "dc1",
      "rack" -> "rc1",
      "release_version" -> "2.0.1",
      "tokens" -> Set("1743244960790844724")))
    PrimingHelper.primeQuery(when, rows, columnTypes = columnTypes)

    cluster = Cluster.builder()
      .addContactPoint(ConnectionToServerStub.ServerHost)
      .withPort(ConnectionToServerStub.ServerPort)
      .build()
    session = cluster.connect()

    cluster.getMetadata.getClusterName should equal(clusterName)
  }

}
