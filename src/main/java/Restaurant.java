import org.sql2o.*;
import java.util.List;

public class Restaurant {
  private int id;
  private int cuisineId;
  private String name;

  public Restaurant (String newName, int cuisineId) {
    this.name = newName;
    this.cuisineId = cuisineId;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getCuisineId() {
    return cuisineId;
  }

  @Override
  public boolean equals(Object otherRestaurant){
    if (!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
        this.getId() == newRestaurant.getId();
    }
  }

  //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurants (name, cuisineId) VALUES (:name, :cuisineId);";
      this.id = (int) con.createQuery(sql,true)
        .addParameter("name", this.name)
        .addParameter("cuisineId", this.cuisineId)
        .executeUpdate()
        .getKey();
      }
  }

  // READ
  public static List<Restaurant> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants;";
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  public static Restaurant find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants WHERE id=:id;";
      Restaurant restaurant = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Restaurant.class);
      return restaurant;
    }
  }

  //UPDATE
  public void updateName(String newName) {
    this.name = newName;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE restaurants SET name =:newName WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("newName", newName)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateCuisineId(int newCuisineId) {
    this.cuisineId = newCuisineId;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE restaurants SET cuisineid =:newCuisineId WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("newCuisineId", newCuisineId)
        .addParameter("id", id)
        .executeUpdate();
    }
  }


  //DELETE
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM restaurants WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
