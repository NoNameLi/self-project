package cn.peng.studygodpath.algorithm.leetcode;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-07 09:57
 * QDescription:
 */
public class LeetCode298 {

    /**
     * 生命游戏。根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机
     * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。
     * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
     * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
     * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
     * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
     * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
     * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态
     */

    public void testLife() {
        int[][] borad = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        lifeGame(borad);
    }


    public void lifeGame(int[][] board) {
        if (null == board || board.length == 0 || board[0].length == 0) {
            return;
        }
        int x = board.length;
        int y = board[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int num = neighbors(board, i, j);
                if (num == 3 || (board[i][j] == 1 && num == 2)) {
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j] = board[i][j] >>> 1;
            }
        }
    }

    /**
     * 计算 x y 点周围又几个1
     *
     * @param board
     * @param i
     * @param j
     * @return
     */
    private int neighbors(int[][] board, int i, int j) {

        return f(board, i - 1, j - 1) +
                f(board, i, j - 1) +
                f(board, i + 1, j - 1) +
                f(board, i - 1, j) +
                f(board, i + 1, j) +
                f(board, i - 1, j + 1) +
                f(board, i, j + 1) +
                f(board, i + 1, j + 1);
    }

    private int f(int[][] borad, int x, int y) {
        if (x <= 0 || y < 0 || x >= borad.length || y >= borad[0].length) {
            return 0;
        } else {
            return borad[x][y];
        }
    }

}
