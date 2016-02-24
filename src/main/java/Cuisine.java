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
  //
  // //UPDATE
  // public void update(String newType) {
  //   this.mType = newType;
  //   try(Connection con = DB.sql2o.open()) {
  //     /******************************************************
  //       Students: TODO: Create sql query and execute update
  //     *******************************************************/
  //   }
  // }
  //
  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //     /******************************************************
  //       Students: TODO: Create sql query and execute update
  //     *******************************************************/
  //   }
  // }
  //
  // /******************************************************
  //   Students:
  //   TODO: Create find method
  //   TODO: Create method to get restaurants
  // *******************************************************/

}
