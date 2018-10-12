package cn.peng.studygodpath.algorithm.astar;

/**
 * Created by remote on 2017/7/15.
 * 路径节点类
 */
public class Node implements Comparable<Node>{
    private Coord position;
    private Coord parent;
    private int G;
    private int F;

    public Node(int x, int y){
        position = new Coord(x,y);
    }
    public Node(int x,int y,Coord parent,int G,int F){
        this(x,y);
        this.parent = parent;
        this.G = G;
        this.F = F;
    }

    public int getValue(){
        return G + F;
    }

    @Override
    public int compareTo(Node node) {
        if(null == node)return -1;
        if(getValue() > node.getValue())
            return 1;
        if(getValue() < node.getValue())
            return -1;
        return 0;
    }
}
