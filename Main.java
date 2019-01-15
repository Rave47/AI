public class Main {

    public static void main(String[] args) {

//        SearchAbstract search = new Search();
//        SearchAbstract search = new SearchIterative();

        SearchAbstract search = new SearchManhattan();
//        SearchAbstract search = new SearchA();


        search.process();
    }
}
