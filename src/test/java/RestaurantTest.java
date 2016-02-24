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

  @Test
  public void equals_returnsTrueIfRestaurantNamesAreTheSame() {
    Restaurant newResty = new Restaurant("Bobby");
    Restaurant newRestyToo = new Restaurant("Bobby");
    assertTrue(newResty.equals(newRestyToo));
  }
  
  @Test
  public void getName_returnsName_string() {
    Restaurant newResty = new Restaurant("Bobby");
    assertEquals("Bobby", newResty.getName());
  }


}
