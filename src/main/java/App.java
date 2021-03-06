import java.util.Map;
import java.util.HashMap;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    // As a user I want to be able to see all restaurants on a main page.

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("restaurant", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      int cuisineId = Integer.parseInt(request.queryParams("cuisineId"));
      Restaurant newResty = new Restaurant(name, cuisineId);
      newResty.save();
      request.session().attribute("newResty", newResty);
      newResty.update(newResty.getName(), newResty.getCuisineId(), "", "", "");
      model.put("cuisines", Cuisine.all());
      model.put("newResty", newResty);
      model.put("index", "templates/index.vtl");
      model.put("template", "templates/cuisinecheck.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/cuisine", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String type = request.queryParams("cuisine");
      Cuisine newCuisine = new Cuisine(type.toUpperCase());
      newCuisine.save();
      Restaurant newResty = request.session().attribute("newResty");
      newResty.update(newResty.getName(), newCuisine.getId(), "", "", "");
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/restaurant/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String restaurantId = request.params(":id");
      Restaurant restaurant = Restaurant.find(Integer.parseInt(restaurantId));
      model.put("restaurant", restaurant);
      model.put("cuisine", Cuisine.class);
      model.put("template", "templates/restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String restaurantId = request.params(":id");
      Restaurant oldResty = Restaurant.find(Integer.parseInt(restaurantId));
      int questionableCuisineId = oldResty.getCuisineId();
      Cuisine questionableCuisine = Cuisine.find(questionableCuisineId);
      oldResty.delete();

      if (questionableCuisine.getRestaurants().size() == 0) {
        questionableCuisine.delete();
      }

      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/update/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String restaurantId = request.params(":id");
      Restaurant restaurant = Restaurant.find(Integer.parseInt(restaurantId));
      model.put("restaurant", restaurant);
      model.put("cuisines", Cuisine.all());
      model.put("cuisineClass", Cuisine.class);
      model.put("template", "templates/update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/restaurant/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String restaurantId = request.params(":id");
      Restaurant restaurant = Restaurant.find(Integer.parseInt(restaurantId));
      String name = request.queryParams("name");
      int cuisineId = Integer.parseInt(request.queryParams("cuisineId"));
      String phoneNumber = request.queryParams("phoneNumber");
      String location = request.queryParams("location");
      String cost = request.queryParams("cost");
      restaurant.update(name, cuisineId, phoneNumber, location, cost);
      model.put("restaurant", restaurant);
      model.put("cuisine", Cuisine.class);
      model.put("template", "templates/restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    /******************************************************
    Students: TODO: Create page to add a new restaurant
    *******************************************************/
    // get("/new-restaurant", (request, reponse) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   model.put("template", "templates/newrestaurant.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    /******************************************************
    STUDENTS:
    TODO: Create page to display information about the selected restaurant
    TODO: Create page to display restaurants by cuisine type
    *******************************************************/

  }
}
