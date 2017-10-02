package Indexing;

import se.kth.id1020.util.Document;

import java.util.ArrayList;
import java.util.Collections;

public class NodeData {
    private Document document;
    private ArrayList<Integer> occurrences = new ArrayList<Integer>();

    public NodeData(Document doc){
        this.document = doc;
    }

    //Insert method inserts the different occurrences of a word in the document the word has appeared in, and inserts
    //them in to an array list that belongs to that specific node data.
    public void insert(int occ){
        occurrences.add(occ);
    }

    //if two or more words that are searched for appear in the same document, we add their occurrences to the same
    //occurrence list.
    public void union(NodeData nodeData){
        occurrences.addAll(nodeData.occurrences);
    }

    //returned the amount of times a certain word has occurred in the document.
    public int getCount(){
        return occurrences.size();
    }

    public int getPopularity(){
        return document.popularity;
    }

    //returns the position in which the searched word appeared for the first time.
    public int getOccurrence(){ return Collections.min(occurrences);}

    public Document getDocument() { return this.document; }
}
