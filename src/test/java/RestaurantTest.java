import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void restaurant_instantiatesCorrectly_true() {
    Restaurant newResty = new Restaurant("Cheryl's", 1);
    assertTrue(newResty instanceof Restaurant);
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfRestaurantNamesAreTheSame() {
    Restaurant newResty = new Restaurant("Bobby", 1);
    Restaurant newRestyToo = new Restaurant("Bobby", 1);
    assertTrue(newResty.equals(newRestyToo));
  }

  @Test
  public void getName_returnsName_string() {
    Restaurant newResty = new Restaurant("Bobby", 1);
    assertEquals("Bobby", newResty.getName());
  }

  @Test
  public void save_restaurantToDatabase_true() {
    Restaurant newResty = new Restaurant("Cheryl's", 1);
    newResty.save();
    assertTrue(Restaurant.all().contains(newResty));
  }

  @Test
  public void find_returnsRestaurantFromDatabase_true() {
    Restaurant newResty = new Restaurant("Cheryl's", 1);
    newResty.save();
    assertEquals(newResty, Restaurant.find(newResty.getId()));
  }

  @Test
  public void getId_returnsRestaurantId() {
    Restaurant newResty = new Restaurant("Cheryl's", 1);
    newResty.save();
    Restaurant savedRestaurant = Restaurant.find(newResty.getId());
    assertTrue(newResty.getId() == savedRestaurant.getId());
  }

  // public void addPhoneNumber_declareRestaurantPhoneNumber() {
  //   Restaurant newResty = new Restaurant("Paula's", 1);
  //   newResty.save();
  //   newResty.addPhoneNumber("111-222-3333");
  //   assertEquals("111-222-3333", newResty.getPhoneNumber());
  // }

  // @Test
  // public void update_updatesAllRestaurantProperties() {
  //   Restaurant newBaby = new Restaurant("Pizza Caboose", 1);
  //   newBaby.save();
  //   newBaby.updateName("Chipotle");
  //   assertEquals(newBaby.getName(), "Chipotle");
  // }

  @Test
  public void delete_removesRestaurant() {
    Restaurant newResty = new Restaurant("Cheesy's", 1);
    newResty.save();
    assertTrue(Restaurant.all().contains(newResty));
    newResty.delete();
    assertFalse(Restaurant.all().contains(newResty));
  }

}
