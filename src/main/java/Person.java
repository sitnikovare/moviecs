public class Person {
    protected String name;
    protected String role;

    public Person() {}

    public Person(String n, String r) {
        name = n;
        role = r;
    }

    public String getName() {
        return name;
    }
    public String getRole() { return role; }

    //Создание узла Person в базе
    public void initInDB() {
        try ( Connector connector = new Connector( "bolt://localhost:11008", "neo4j", "root" ) )
        {
            connector.CreateNode( this);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void deleteFromDB() {
        try ( Connector connector = new Connector( "bolt://localhost:11008", "neo4j", "root" ) )
        {
            connector.DeleteNode(this);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
