package cn.peng.studygodpath.algorithm.astar;

/**
 * Created by remote on 2017/7/15.
 * 坐标类
 */
public class Coord {
    private int x;
    private int y;

    public Coord(int x,int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if(null == obj) return false;
        if(obj instanceof  Coord){
            Coord o = (Coord) obj;
            if(x == o.getX() && y == o.getY()){
                return true;
            }
        }
        return false;
    }
}
