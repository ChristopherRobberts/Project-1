/**
 * Created by Chris on 2017-09-29.
 */
import se.kth.id1020.Driver;
import se.kth.id1020.TinySearchEngineBase;

public class Main {
    public static void main(String[] args) throws Exception{
        TinySearchEngineBase searchEngine = new TinySearchEngine();
        Driver.run(searchEngine);
    }
}
