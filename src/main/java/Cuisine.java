import org.sql2o.*;
import java.util.List;

public class Cuisine {
  private int id;
  private String type;

  public Cuisine (String type) {
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  @Override
  public boolean equals(Object otherCuisine){
    if (!(otherCuisine instanceof Cuisine)) {
      return false;
    } else {
      Cuisine newCuisine = (Cuisine) otherCuisine;
      return this.getType().equals(newCuisine.getType()); //&&
        // this.getId() == newCuisine.getId();
    }
  }

  //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO cuisines(type) VALUES (:type);";
      id = (int) con.createQuery(sql,true)
        .addParameter("type", type)
        .executeUpdate()
        .getKey();
    }
  }

  //READ
  public static List<Cuisine> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM cuisines;";
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }

  public static Cuisine find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM cuisines WHERE id=:id;";
      Cuisine cuisine = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Cuisine.class);
      return cuisine;
    }
  }

  //UPDATE
  public void update(String newType) {
    this.type = newType;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE cuisines SET type = :newType WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("newType", newType)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM cuisines WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public List<Restaurant> getRestaurants() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants WHERE cuisineid =:id;";
      List<Restaurant> restaurants = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Restaurant.class);
      return restaurants;
    }
  }

}
