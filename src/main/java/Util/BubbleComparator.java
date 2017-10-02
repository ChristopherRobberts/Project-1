package Util;

import Indexing.NodeData;

import java.util.Comparator;

/**
 * Created by Chris on 2017-10-02.
 */
public class BubbleComparator implements Comparator<NodeData> {
    private String property;
    private int direction;

    public BubbleComparator(String property, String direction) {
        this.property = property;
        if (direction.equals("descending")) this.direction = -1;
        else this.direction = 1;
    }

    public int compare(NodeData v, NodeData w)
    {
        if (property.equals("count"))
        {
            if (v.getCount() > w.getCount()) return direction;
            else if (v.getCount() < w.getCount()) return -direction;
            else return 0;
        }
        else if (property.equals("occurrence"))
        {
            if (v.getOccurrence() > w.getOccurrence()) return direction;
            else if (v.getOccurrence() < w.getOccurrence()) return -direction;
            else return 0;
        }
        else
        {
            if (v.getPopularity() > w.getPopularity()) return direction;
            else if (v.getPopularity() < w.getPopularity()) return -direction;
            else return 0;
        }
    }
}

