public class Person {
    protected String name;
    protected String role;
    protected String rate;

    public Person() {}

    public Person(String n, String r,String g) {
        name = n;
        role = r;
        rate = g;
    }

    public String getName() {
        return name;
    }
    public String getRole() { return role; }

    public void setRate(String r) {rate = r;}

    //Создание узла Person в базе
    public void initInDB() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.CreateNode( this);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void bornIn(Date date) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.CreateRelation( this, date, "bornIn");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void deleteFromDB() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.DeleteNode(this);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
