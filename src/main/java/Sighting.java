import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Sighting {
  private int animal_id;
  private String location;
  private String ranger_name;
  private int id;

  public Sighting(int animal_id, String location, String ranger_name) {
    this.animal_id = animal_id;
    this.location = location;
    this.ranger_name = ranger_name;
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public int getAnimalId() {
    return animal_id;
  }

  public String getLocation() {
    return location;
  }

  public String getRangerName() {
    return ranger_name;
  }

  @Override
  public boolean equals(Object otherSighting) {
    if(!(otherSighting instanceof Sighting)) {
      return false;
    } else {
      Sighting newSighting = (Sighting) otherSighting;
      return this.getAnimalId() == (newSighting.getAnimalId()) && this.getLocation().equals(newSighting.getLocation()) && this.getRangerName().equals(newSighting.getRangerName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animal_id, location, ranger_name) VALUES (:animal_id, :location, :ranger_name);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("animal_id", this.animal_id)
        .addParameter("location", this.location)
        .addParameter("ranger_name", this.ranger_name)
        //.throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Sighting> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings;";
      return con.createQuery(sql)
        //.throwOnMappingFailure(false)
        .executeAndFetch(Sighting.class);
    }
  }

  public static Sighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE id = :id;";
      Sighting sighting = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Sighting.class);
      return sighting;
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }
//need to parse integer
  // public void updateAnimalId(int animal_id) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE sightings SET animal_id = :animal_id WHERE id=:id;";
  //     con.createQuery(sql)
  //       .addParameter("id", id)
  //       .addParameter(animal_id, animal_id)
  //       .executeUpdate();
  //   }
  // }

  public void updateLocation(String location) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE sightings SET location = :location WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("location", location)
        .executeUpdate();
    }
  }

  public void updateRangerName(String ranger_name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE sightings SET ranger_name = :ranger_name WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("ranger_name", ranger_name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM sightings WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }


}
