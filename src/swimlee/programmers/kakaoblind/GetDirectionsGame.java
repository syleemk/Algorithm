package swimlee.programmers.kakaoblind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 트리 구현?
 */

public class GetDirectionsGame {

    public static void main(String[] args) {
        int[][] nodeinfo = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};

        GetDirectionsGame getDirectionsGame = new GetDirectionsGame();
        int[][] solution = getDirectionsGame.solution(nodeinfo);
        for (int[] ints : solution) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];

        ArrayList<TreeNode> list = new ArrayList<>();

        for (int i = 0; i < nodeinfo.length; i++) {
            list.add(new TreeNode(i+1, nodeinfo[i][0], nodeinfo[i][1]));
        }

        Collections.sort(list);

        Tree tree = new Tree();

        for (int i = 0; i < list.size(); i++) {
            TreeNode now = list.get(i);
            if (i == 0) {
                tree = new Tree(now.index, now.x, now.y);
            } else {
                addTreeNode(tree, new Tree(now.index, now.x, now.y));
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        inorder(tree, result);

        for (int i = 0; i < nodeinfo.length; i++) {
            answer[0][i] = result.get(i);
        }

        result.clear();
        postorder(tree, result);

        for (int i = 0; i < nodeinfo.length; i++) {
            answer[1][i] = result.get(i);
        }

        return answer;
    }

    private void addTreeNode(Tree root, Tree node) {
        if (root.left == null && root.right == null) {
            if (root.x > node.x) { // 더해지는 노드가 루트의 왼쪽에 있을 경우
                root.left = node;
            } else { // 오른쪽에 있을 경우
                root.right = node;
            }
        } else {
            if (root.left == null) {
                if (root.x > node.x) { // 더해지는 노드가 루트의 왼쪽인 경우, 루트의 왼쪽이 null
                    root.left = node;
                } else { // 오른쪽에 있는 경우, 오른쪽 노드가 not null
                    addTreeNode(root.right, node);
                }
            } else if (root.right == null) {
                if (root.x > node.x) {
                    addTreeNode(root.left, node);
                } else {
                    root.right = node;
                }
            } else {
                if (root.x > node.x) {
                    addTreeNode(root.left, node);
                } else {
                    addTreeNode(root.right, node);
                }
            }
        }
    }

    private void inorder(Tree root, ArrayList<Integer> result) {

        result.add(root.index);
        if(root.left != null) inorder(root.left, result);
        if(root.right != null) inorder(root.right, result);
    }

    private void postorder(Tree root, ArrayList<Integer> result) {

        if(root.left != null) postorder(root.left, result);
        if(root.right != null) postorder(root.right, result);
        result.add(root.index);
    }
}

class Tree {
    int index;
    int x;
    int y;
    Tree left;
    Tree right;

    Tree() {}

    Tree(int index, int x, int y) {
        this.index = index;
        this.x = x;
        this.y = y;
    }
}

class TreeNode implements Comparable<TreeNode> {
    int x;
    int y;
    int index;

    TreeNode(int index, int x, int y) {
        this.index = index;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(TreeNode o) {
        if (this.y == o.y) {
            return this.x - o.x;
        }
        return -(this.y - o.y);
    }
}
