import Model.Entity;
import Util.ConnectionUtil;
import Util.FileUtil;
import org.junit.*;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class InnerJoinTest {
    private static Connection conn;

    public Set<Entity> sql(){
            String sql = FileUtil.parseSQLFile("src/main/lab1.sql");
            Set<Entity> results = new HashSet<>();
            try {
                Connection connection = ConnectionUtil.getConnection();
                Statement s = connection.createStatement();
                ResultSet rs =s.executeQuery(sql);

                while(rs.next()) {
                    Entity e = new Entity();
                    e.setStudent(rs.getString("student_name"));
                    e.setId(rs.getInt("id"));
                    results.add(e);
                }
            } catch (SQLException e) {
                Assert.fail(e.getMessage());
            }

            return results;
    }
    @Test
    public void testActivityInnerJoin1() {
        Entity e1 = new Entity(2, "Stephen Colbert");
        Entity e2 = new Entity(3, "Samantha Bee");
        Entity e3 = new Entity(5, "Robert Riggle");
        Set<Entity> results = sql();
        Assert.assertTrue("There should only be 3 items in the result set. ",
                results.size() == 3);
        Assert.assertTrue("This student should be in the result set: "+e1, results.contains(e1));
        Assert.assertTrue("This student should be in the result set: "+e2, results.contains(e2));
        Assert.assertTrue("This student should be in the result set: "+e3, results.contains(e3));


    }

    @BeforeClass
    public static void beforeAll() {
        conn = ConnectionUtil.getConnection();
    }


    @Before
    public void beforeEach() {
        try {
            String facultyTable = "CREATE TABLE class (" +
                    "id SERIAL PRIMARY KEY," +
                    "teacher_name VARCHAR(255)," +
                    "class_title VARCHAR(255)" +
                    ");";
            PreparedStatement facultyTableStatement = conn.prepareStatement(facultyTable);
            facultyTableStatement.executeUpdate();

            String insertFaculty = "INSERT INTO class (teacher_name, class_title) VALUES" +
                    "('Ms. Lovelace', 'Physics')," +
                    "('Ms. Lovelace', 'Math')," +
                    "('Mr. McCarthy', 'Writing')," +
                    "('Ms. Goodall', 'Biology');";
            PreparedStatement insertFacultyData = conn.prepareStatement(insertFaculty);
            insertFacultyData.executeUpdate();


            String studentsTable = "CREATE TABLE student (" +
                    "id SERIAL PRIMARY KEY," +
                    "student_name VARCHAR(255)," +
                    "class_title VARCHAR(255)" +
                    ");";
            PreparedStatement studentsTableStatement = conn.prepareStatement(studentsTable);
            studentsTableStatement.executeUpdate();

            String insertStudents = "INSERT INTO student (student_name, class_title) VALUES" +
                    "('John Stewart', 'Writing')," +
                    "('Stephen Colbert', 'Physics')," +
                    "('Samantha Bee', 'Math')," +
                    "('Aasif Mandvi', 'Writing')," +
                    "('Robert Riggle', 'Physics')," +
                    "('Jessica Williams', 'Art');";
            PreparedStatement insertStudentsData = conn.prepareStatement(insertStudents);
            insertStudentsData.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void afterEach() {
        try {
            conn = ConnectionUtil.getConnection();

            String dropTable = "DROP TABLE IF EXISTS class, student";
            PreparedStatement createTableStatement = conn.prepareStatement(dropTable);
            createTableStatement.executeUpdate();

        } catch(SQLException e) {
        }
    }

    @AfterClass
    public static void afterAll() {
        try {
            conn.close();
        } catch(SQLException e) {
        }

    }
}