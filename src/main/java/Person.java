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

    //Создание узла Person в базе
    public void createNodeInDB() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.CreatePersonNode( this.name, this.role );
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
