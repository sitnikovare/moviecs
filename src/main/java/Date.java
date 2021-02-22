public class Date {
    private int year;

    public Date(int y) {
        if (y >= 0)
            year = y;
        else return;
    }

    int getYear() { return year;}

    //Создание узла Date в базе
    public void initInDB() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.CreateNode(this);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
