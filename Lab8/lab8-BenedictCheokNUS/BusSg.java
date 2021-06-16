import java.util.Map;
import java.util.Set;
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
  public static BusRoutes findBusServicesBetween(BusStop stop, String searchString) {
    try {
      Map<BusService, Set<BusStop>> validServices = stop.getBusServices().stream()
          .collect(Collectors.toMap(
              service -> service, 
              service -> service.findStopsWith(searchString)));
      return new BusRoutes(stop, searchString, validServices);
    } catch (CompletionException e) {
      System.err.println("Unable to complete query: " + e);
      return new BusRoutes(stop, searchString, Map.of());
    }
  }
}
