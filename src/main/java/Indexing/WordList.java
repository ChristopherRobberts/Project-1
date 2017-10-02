package Indexing;

import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Word;

import java.util.ArrayList;


public class WordList {
    //we create an array list of the words that are to be inserted from the brown corpus documents.
    //every array entry is of the object node, which takes the word itself and creates an array list of it's attributes.
    private ArrayList<Node> words = new ArrayList<Node>();

    //Our object instance of WordList calls this insert method when it is to insert the words.
    //we create an instance of a node which here is our inserted and all it's attributes word.
    //This instance points to the word generated from the "get node" method which returns the node with the given
    //word taken from the object instance of Words variable word.
    //We then check if the same word already has been inserted into the array list earlier, if it hasn't we add a new
    //node to the array list with that word, and index it using binary searching for logarithmic complexity.
    //We do this by calling our WordList class method "insert".
    //The last thing we do is insert all the attributes for this word in to our node by calling the insert method for
    //our Node.
    public void insert(Word w, Attributes att){
        Node wordNode = getNode(w.word);
        if (wordNode == null){
            wordNode = new Node(w);
            insert(w.word, wordNode, words);
        }
        wordNode.insert(att);
    }

    /*public ArrayList<Document> search(String key) {
        Node current = getNode(key);

        if (current == null)
            return null;

        return current.getDocuments();
    } */

    //This method accesses the given node by searching for it's word key and returns all that nodes data
    //(documents & occurrences). If the node doesn't exist in the array we return an empty array.
    public ArrayList<NodeData> getNodeDatas(String key){
        Node n = getNode(key);

        if (n == null)
            return new ArrayList<NodeData>();

        return n.getNodeDataList();
    }

    //This method creates a new array list that points to our class's node array list, then it does a binary search
    //of the array list and returns the searched node with the given key string.
    private Node getNode(String key)
    {
        ArrayList<Node> listOfWords = this.getWords();
        int index = search(key, listOfWords, 0, listOfWords.size());
        Node currentNode;
        try{
        currentNode = listOfWords.get(index);
        }
        catch (ArrayIndexOutOfBoundsException e){
            return null;
         }
        return currentNode;
    }

    //returns our class array list with all the nodes.
    private ArrayList<Node> getWords(){
        return this.words;
    }


    private static Node insert(String key, Node n, ArrayList<Node> arr){
        if(arr.size() == 0){
            arr.add(n);
        }
        int pos = search(key, arr, 0, arr.size() - 1);
        if(pos < 0){
            pos = -pos -1;
            if(pos >= arr.size())
                arr.add(n);
            else if (arr.get(pos).getWord().compareTo(key) > 0)
                arr.add(pos, n);
            else
                arr.add(pos + 1, n);
            return n;
        }
        else
            return arr.get(pos);
    }

    //Binary search method for this class
    private static int search(String key, ArrayList<Node> a, int lo, int hi) {
        int mid = 0;
        int cmp;
        if(a.size() == 0){
            return -1;
        }
        while(lo < hi){
            mid = lo + (hi - lo) / 2;
            cmp = a.get(mid).getWord().compareTo(key);

            if(cmp > 0)
                hi = mid;
            else if(cmp < 0)
                lo = mid + 1;
            else
                return mid;
        }
        return -mid - 1;
    }
}
