import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class EndangeredAnimalTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void endangeredAnimal_instantiatesCorrectly_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
  }

  @Test
  public void getName_endangeredAnimalInstantiatesWithName_String() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    assertEquals("Fox", testEndangeredAnimal.getName());
  }

  @Test
  public void getHealth_endangeredAnimalInstantiatesWithHealth_String() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    assertEquals("Healthy", testEndangeredAnimal.getHealth());
  }

  @Test
  public void getAge_endangeredAnimalInstantiatesWithAge_String() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    assertEquals("Young", testEndangeredAnimal.getAge());
  }

  @Test
  public void equals_returnsTrueIfNameHealthAgeIsTheSame_true() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    assertTrue(firstEndangeredAnimal.equals(secondEndangeredAnimal));
  }

  @Test
  public void save_assignsIdAndSavesObjectToDatabase() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    testEndangeredAnimal.save();
    EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.all().get(0);
    assertEquals(testEndangeredAnimal.getId(), savedEndangeredAnimal.getId());
  }

  @Test
  public void all_returnsAllInstancesOfEndangeredAnimal_true() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Badger", "Okay", "Adult");
    secondEndangeredAnimal.save();
    assertEquals(true, EndangeredAnimal.all().get(0).equals(firstEndangeredAnimal));
    assertEquals(true, EndangeredAnimal.all().get(1).equals(secondEndangeredAnimal));
  }

  @Test
  public void find_returnsEndangeredAnimalWithSameId_secondEndangeredAnimal() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Badger", "Okay", "Adult");
    secondEndangeredAnimal.save();
    assertEquals(EndangeredAnimal.find(secondEndangeredAnimal.getId()), secondEndangeredAnimal);
  }

  @Test
  public void save_savesEndangeredAnimalIdIntoDB_true() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    firstEndangeredAnimal.save();
    assertEquals(true, EndangeredAnimal.all().get(0).equals(firstEndangeredAnimal));
  }

  @Test
  public void update_updatesNameAttribute_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    testEndangeredAnimal.save();
    testEndangeredAnimal.updateName("Coyote");
    assertEquals("Coyote", EndangeredAnimal.find(testEndangeredAnimal.getId()).getName());
  }

  @Test
  public void update_updatesHealthAttribute_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    testEndangeredAnimal.save();
    testEndangeredAnimal.updateHealth("ill");
    assertEquals("ill", EndangeredAnimal.find(testEndangeredAnimal.getId()).getHealth());
  }

  @Test
  public void update_updatesAgeAttribute_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    testEndangeredAnimal.save();
    testEndangeredAnimal.updateAge("Adult");
    assertEquals("Adult", EndangeredAnimal.find(testEndangeredAnimal.getId()).getAge());
  }

  @Test
  public void delete_deletesEndangeredAnimalFromDatabase_0() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    testEndangeredAnimal.save();
    testEndangeredAnimal.delete();
    assertEquals(0, EndangeredAnimal.all().size());
  }

  @Test
  public void find_returnsNullWhenNoEndangeredAnimalFound_null() {
    assertTrue(EndangeredAnimal.find(999) == null);
  }
}
