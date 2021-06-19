import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

/**
 * A BusSg class encapsulate the data related to the bus services and
 * bus stops in Singapore, and supports queries to the data.
 *
 * @author: Benedict Cheok Wei En (B03), A0199433U
 * @version: CS2030 AY20/21 ST1, Lab 8
 */
class BusSg {

  /**
   * Given a bus stop and a name, find the bus services that serve between
   * the given stop and any bus stop with matching mame.
   * @param  stop The bus stop.  Assume to be not null.
   * @param  searchString The (partial) name of other bus stops, assume not null.
   * @return The (optional) bus routes between the stops.
   */
  public static CompletableFuture<BusRoutes> findBusServicesBetween(
      BusStop stop, String searchString) {
    
    CompletableFuture<BusRoutes> busRoutes = CompletableFuture.supplyAsync(() -> 
        new BusRoutes(stop, searchString, stop.getBusServices() 
          //.getBusServices() return CompletableFuture<Set<BusService>>
          .thenApply(x -> x.stream())
          //becomes CompletableFuture<Stream<BusService>>
          .thenApply(x -> x.collect(Collectors.toMap(
                service -> service, 
                service -> service.findStopsWith(searchString))))
          //becomes CompletableFuture<Map<Service, CompletableFuture<Set<BusStop>>>>
          .exceptionally(e -> {
            System.err.println("Unable to complete query: " + e);
            return Map.of();
          })));
    return busRoutes;
  }
}
