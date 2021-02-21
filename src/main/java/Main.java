public class Main {

    public static void main( String... args ) throws Exception
    {
        try ( Connector greeter = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            greeter.printGreeting( "hello, world" );
        }
    }

}
