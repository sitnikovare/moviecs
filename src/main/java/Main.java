public class Main {

    public static void main( String... args ) throws Exception
    {
       User tUser = new User("testInit");
       tUser.createNodeInDB();

       Actor tAct = new Actor("testActor");
       tAct.createNodeInDB();

       Director tDir = new Director("testDir");
       tDir.createNodeInDB();
    }
}
