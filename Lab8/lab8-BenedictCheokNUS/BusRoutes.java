import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Encapsulates the result of a query: for a bus stop and a search string,
 * it stores a map of bus services that servce stops with matching name.
 * e.g., stop 12345, name "MRT", with map contains:
 *    96: 11223 "Clementi MRT"
 *    95:  22334 "Beuno Vista MRT"
 *
 * @author: Benedict Cheok Wei En (B03), A0199433U
 * @version: CS2030 AY20/21 ST1, Lab 8
 */
class BusRoutes {
  final BusStop stop;
  final String name;
  final CompletableFuture<Map<BusService, CompletableFuture<Set<BusStop>>>> services;

  /**
   * Constructor for creating a bus route.
   * @param stop The first bus stop.
   * @param name The second bus stop.
   * @param services The set of bus services between the two stops.
   */
  BusRoutes(BusStop stop, String name, 
      CompletableFuture<Map<BusService, CompletableFuture<Set<BusStop>>>> services) {
    this.stop = stop;
    this.name = name;
    this.services = services;
  }

  /**
   * Return a string describing the bus route.
   * @return The first line contains the query information:
   *     bus stop id and search string.  The remaining line contains 
   *     the bus services and matching stops served.
   */
  public CompletableFuture<String> description() {
    String outStart = "Search for: " + this.stop + " <-> " + name + ":\n"
        + "From " + this.stop + "\n";

    CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> outStart);

    CompletableFuture<String> halfResult = this.services
        .<String>thenCompose(m -> {
          CompletableFuture<String> stops = CompletableFuture.supplyAsync(() -> "");
          for (BusService service : m.keySet()) {
            stops = m.get(service) //returns CF<Set<BusStop>>
                .<String>thenApply(setStops -> describeService(service, setStops))
                .thenCombine(stops, (a, b) -> a + b);
          }
          return stops;
        });
    return result.thenCombine(halfResult, (x, y) -> x + y);   
  }

  /**
   * Return a string representation a bus service and its matching stops.
   * @return The first line contains the query information:
   *     bus stop id and search string.  The remaining line contains 
   *     the bus services and matching stops served.
   */
  public String describeService(BusService service, Set<BusStop> stops) {
    
    if (stops.isEmpty()) {
      return "";
    } 
    return stops
      .stream()
      .filter(stop -> stop != this.stop) 
      .reduce("- Can take " + service + " to:\n",
          (str, stop) -> str += "  - " + stop + "\n",
          (str1, str2) -> str1 + str2);
  }
}
