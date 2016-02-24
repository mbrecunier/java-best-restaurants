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
}
