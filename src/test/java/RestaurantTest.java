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

  @Test
  public void save_restaurantToDatabase_true() {
    Restaurant newResty = new Restaurant("Cheryl's");
    newResty.save();
    assertTrue(Restaurant.all().contains(newResty));
  }

  @Test
  public void find_returnsRestaurantFromDatabase_true() {
    Restaurant newResty = new Restaurant("Cheryl's");
    newResty.save();
    assertEquals(newResty, Restaurant.find(newResty.getId()));
  }

  @Test
  public void getId_returnsRestaurantId() {
    Restaurant newResty = new Restaurant("Cheryl's");
    newResty.save();
    Restaurant savedRestaurant = Restaurant.find(newResty.getId());
    assertTrue(newResty.getId() == savedRestaurant.getId());
  }

  @Test
  public void update_changesRestaurantName() {
    Restaurant newBaby = new Restaurant("Pizza Caboose");
    newBaby.save();
    newBaby.update("Chipotle");
    assertEquals(newBaby.getName(), "Chipotle");
  }

  @Test
  public void delete_removesRestaurant() {
    Restaurant newResty = new Restaurant("Cheesy's");
    newResty.save();
    assertTrue(Restaurant.all().contains(newResty));
    newResty.delete();
    assertFalse(Restaurant.all().contains(newResty));
  }

  @Test
  public void setCuisineId_setsCuisineId() {
    Restaurant newBoobear = new Restaurant("Beebop Landcam");
    newBoobear.setCuisineId(3);
    assertEquals(3, newBoobear.getCuisineId());
  }


}
