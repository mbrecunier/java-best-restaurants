import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Cuisine.all().size(), 0);
  }

  @Test
  public void save_addsCuisineToDatabase_True() {
    Cuisine western = new Cuisine("beans");
    western.save();
    assertTrue(Cuisine.all().contains(western));
  }

  @Test
  public void find_returnsCuisineObject_True() {
    Cuisine newCuisine = new Cuisine("cuisine");
    newCuisine.save();
    assertTrue(newCuisine.equals(Cuisine.find(newCuisine.getId())));
  }

  @Test
  public void update_changesCuisineType() {
    Cuisine newCuisine = new Cuisine("hot pockets");
    newCuisine.save();
    newCuisine.update("lean");
    assertEquals(newCuisine.getType(), "lean");
  }

  @Test
  public void delete_removesCuisineFromCuisines_true() {
    Cuisine newCuisine = new Cuisine("beans");
    newCuisine.save();
    assertTrue(Cuisine.all().contains(newCuisine));
    newCuisine.delete();
    assertFalse(Cuisine.all().contains(newCuisine));
  }
}
