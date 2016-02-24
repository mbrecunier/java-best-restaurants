import org.sql2o.*;
import java.util.List;

public class Restaurant {
  private int id;
  private int cuisineId;
  private String name;
  private String phoneNumber;
  private String location;
  private String cost;

  public Restaurant (String newName, int cuisineId) {
    this.name = newName;
    this.cuisineId = cuisineId;
  }
  public String getLocation() {
    return location;
  }

  public String getCost() {
    return cost;
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

  public String getPhoneNumber() {
    return phoneNumber;
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
  public void update(String newName, int newCuisineId, String newPhoneNumber, String newLocation, String newCost) {
    this.name = newName;
    this.cuisineId = newCuisineId;
    this.phoneNumber = newPhoneNumber;
    this.location = newLocation;
    this.cost = newCost;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE restaurants SET name =:newName, cuisineid=:newCuisineId, phonenumber=:newPhoneNumber, location=:newLocation, cost=:newCost WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("newName", newName)
        .addParameter("newCuisineId", newCuisineId)
        .addParameter("newPhoneNumber", newPhoneNumber)
        .addParameter("newLocation", newLocation)
        .addParameter("newCost", newCost)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void addPhoneNumber(String newPhoneNumber) {
    this.phoneNumber = newPhoneNumber;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE restaurants SET phonenumber =:newPhoneNumber WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("newPhoneNumber", newPhoneNumber)
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
