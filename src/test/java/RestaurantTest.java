import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void restaurant_instantiatesCorrectly_true() {
    Restaurant newResty = new Restaurant("Cheryl's");
    assertTrue(newResty instanceof Restaurant);
  }

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Restaurant.all().size(), 0);
  }


}
