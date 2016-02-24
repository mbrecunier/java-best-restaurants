import org.sql2o.*;
import java.util.List;

public class Restaurant {
  private int id;
  private int cuisineId;
  private String name;
  //
  public Restaurant (String newName) {
    this.name = newName;
  }
  //
  // public int getId() {
  //   return mId;
  // }
  //
  public String getName() {
    return name;
  }
  //
  // @Override
  // public boolean equals(Object otherRestaurant){
  //   if (!(otherRestaurant instanceof Restaurant)) {
  //     return false;
  //   } else {
  //     Restaurant newRestaurant = (Restaurant) otherRestaurant;
  //     return this.getName().equals(newRestaurant.getName()) &&
  //       this.getId() == newRestaurant.getId();
  //   }
  // }
  //
  // //CREATE
  // public void save() {
  //   try (Connection con = DB.sql2o.open()) {
  //     /******************************************************
  //       Students: TODO: Display all restaurants on main page
  //     *******************************************************/
  //   }
  // }
  //
  // READ
  public static List<Restaurant> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants;";
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }
  //
  // //UPDATE
  // public void update(String newName) {
  //   this.mName = newName;
  //   try(Connection con = DB.sql2o.open()) {
  //     /******************************************************
  //       Students: TODO: Display all restaurants on main page
  //     *******************************************************/
  //     }
  // }
  //
  // //DELETE
  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //     /******************************************************
  //       Students: TODO: Display all restaurants on main page
  //     *******************************************************/
  //   }
  // }
  //
  // /******************************************************
  //   Students:
  //   TODO: Create find method
  //   TODO: Create method to get cuisine type
  // *******************************************************/

}
