import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Best Restaurants");
  }

  @Test
  public void newRestaurantDisplaysOnPage() {
    Cuisine newCuisine = new Cuisine("American");
    newCuisine.save();
    Restaurant newResty = new Restaurant("Jimmy John's", newCuisine.getId());
    newResty.save();
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Jimmy John's");
  }

  @Test
  public void restaurantWithoutCuisineAsksForNewCuisine() {
    goTo("http://localhost:4567/");
    fill("#name").with("Dar Salam");
    fillSelect("#cuisineId").withValue("0");
    submit(".btn");
    assertThat(pageSource()).contains("is served at Dar Salam");
  }

  @Test
  public void newCuisineIsShownOnHomepage() {
    Cuisine newCuisine = new Cuisine("Chinese");
    newCuisine.save();
    Restaurant newResty = new Restaurant("Yao Ming", newCuisine.getId());
    newResty.save();
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Chinese");
  }

  @Test
  public void eachRestaurantHasIndividualPage() {
    Cuisine newCuisine = new Cuisine("Chinese");
    newCuisine.save();
    Restaurant newResty = new Restaurant("Yao Ming", newCuisine.getId());
    newResty.save();
    String restaurantPath = String.format("http://localhost:4567/restaurant/%d", newResty.getId());
    goTo(restaurantPath);
    assertThat(pageSource()).contains("Update");
    assertThat(pageSource()).contains("Yao Ming");
  }

  @Test
  public void updatePage() {
    Cuisine newCuisine = new Cuisine("American");
    newCuisine.save();
    Restaurant newResty = new Restaurant("Jimmy John's", newCuisine.getId());
    newResty.save();
    String restaurantPath = String.format("http://localhost:4567/update/%d", newResty.getId());
    goTo(restaurantPath);
    assertThat(pageSource()).contains("Update Restaurant Information:");
  }

  @Test
  public void updateButtonChangesRestaurantInformationDisplay() {
    Cuisine newCuisine = new Cuisine("American");
    newCuisine.save();
    Restaurant newResty = new Restaurant("Jimmy John's", newCuisine.getId());
    newResty.save();
    String restaurantPath = String.format("http://localhost:4567/update/%d", newResty.getId());
    goTo(restaurantPath);
    fill("#location").with("Portland");
    submit(".btn-warning");
    assertThat(pageSource()).contains("Portland");
  }
}
