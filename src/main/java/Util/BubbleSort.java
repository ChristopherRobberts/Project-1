package Util;

import Indexing.NodeData;
import java.util.ArrayList;

public class BubbleSort {

    //bubble sort implementation
    public static void bubbleSort(String prop, String dir, ArrayList<NodeData> nodeData){
        int n = nodeData.size();
        int R = n - 2;
        boolean swapped = true;
        while(R >= 0 && swapped){
            for(int i = 0; i <= R; i++){
                if(compareTo(prop, dir, nodeData.get(i), nodeData.get(i + 1)) > 0){
                    swapped = true;
                    swap(nodeData, i, i+1);
                }
            }
            R = R - 1;
        }
    }

    private static void swap(ArrayList<NodeData> nodeDatas, int a, int b){
        NodeData temp = nodeDatas.get(a);
        nodeDatas.set(a, nodeDatas.get(b));
        nodeDatas.set(b, temp);
    }

    public static int compareTo(String prop, String dir, NodeData m, NodeData n){
        int comp = 0;

        //compareTo method checks what the written string is, and then it determines what NodeData is largest in this
        //property. if comp is a negative integer it means that n > m and if comp is positive it means that n < m.
        if (prop.equals("count")){
            comp = m.getCount() - n.getCount();
        }
        else if (prop.equals("popularity")){
            comp = m.getPopularity() - n.getPopularity();
        }
        else if (prop.equals("occurrence")){
            comp = m.getOccurrence() - n.getOccurrence();
        }

        //if our direction string equals asc, it will perform a swap if the previous index is larger than the next,
        //else if the direction string equals desc, it will perform the swap if the previous index is smaller than the
        //next index.
        if (dir.equals("asc")) {
            return comp;
        }else
            return -comp;
    }
}
