import Indexing.Node;
import Indexing.NodeData;
import Indexing.WordList;
import Util.BubbleSort;
import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.ArrayList;
import java.util.List;


public class TinySearchEngine implements TinySearchEngineBase{

    WordList wordList = new WordList();

    public void insert(Word w, Attributes att){
        wordList.insert(w, att);
    }

    public List<Document> search(String query){
        return parse(query);
        //return wordList.search(query);
    }

    private List<Document> parse(String query){
        String[] tokens = query.split(" ");
        ArrayList<String> words = new ArrayList<String>();
        String property = "popularity";
        String direction = "desc";

        for(int i = 0; i < tokens.length; i++){
            if(tokens[i].equals("orderby")){
                property = tokens[i + 1];
                direction = tokens[i + 2];
                break;
            }
            else{
                words.add(tokens[i]);
            }
        }

        ArrayList<NodeData> nodeDatas = new ArrayList<NodeData>();
        //iterates the words in the query
        for(String word : words){
            //iterates the node data for each word in the query
           for(NodeData nodeData : wordList.getNodeDatas(word)){
               boolean found = false;
               //iterates our new array list created before the for loops.
               for(NodeData n : nodeDatas){
                   //if we find the same document in the data of the two words we make a union. Adding their occurrences
                   //together
                   if (nodeData.getDocument().name.equals(n.getDocument().name)){
                       n.union(nodeData);
                       found = true;
                   }
               }
               //if there aren't two words found in the same document, we add the node data to the new array.
               if (!found) { nodeDatas.add(nodeData); }
           }
        }
        //here we sort according to the property and direction
        BubbleSort.bubbleSort(property, direction, nodeDatas);
        //we return all the documents from the node data.
        return Node.nodeDatasToDocument(nodeDatas);
    }
}
