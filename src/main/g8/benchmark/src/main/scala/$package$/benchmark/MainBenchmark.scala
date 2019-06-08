package $package$
package benchmark

import java.util.concurrent._
import org.openjdk.jmh.annotations._

@State(Scope.Thread)
class MainBenchmark {

  val N = 10

  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @Benchmark
  def sum(): Int = {
    N + 1
  }
}
