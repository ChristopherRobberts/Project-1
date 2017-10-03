package Indexing;

import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.ArrayList;


public class Node {
    private String key;

    //Every node in the WordList array has an array list of node data (documents and occurrences in that document).
    private ArrayList<NodeData> nodeDataList = new ArrayList<NodeData>();

    //Node constructor, takes a word. The word objects variable word is equal to the nodes variable key.
    public Node(Word w){
        this.key = w.word;
    }

    //Insert method that gets called upon when inserting a new node in the word list array list.
    //Creates a new NodeData instance that takes the given attributes document. Then we add this to the class
    //array list.
    //By calling this method we also call the NodeData method insert, where we insert all the occurrences of the given
    //word in an array list.
    public void insert(Attributes att){
        Document doc = att.document;
        NodeData data = new NodeData(doc);
        nodeDataList.add(data);
        data.insert(att.occurrence);
    }

    //Returns an array list of documents that were gathered from the NodeDatas document and does it for every NodeData
    //object in the array list given by the argument.
    private static ArrayList<Document> nodeDataToDocument(ArrayList<NodeData> nodeDocs) {
        ArrayList<Document> temp = new ArrayList<Document>();

            for (int i = 0; i < nodeDocs.size(); i++) {
                NodeData data = nodeDocs.get(i);
                temp.add(data.getDocument());
            }
        return temp;
    }

    //returns the array list given from the previous method
    public static ArrayList<Document> nodeDatasToDocument(ArrayList<NodeData> appearances) {
        return nodeDataToDocument(appearances);
    }

    public String getWord(){ return this.key; }

    public ArrayList<NodeData> getNodeDataList(){
        return this.nodeDataList;
    }

    public ArrayList<Document> getDocuments()
    {
        return Node.nodeDatasToDocument(this.getNodeDataList());
    }
}
