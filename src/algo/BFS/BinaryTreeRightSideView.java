package algo.BFS;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Queue<TreeNode> currentLevelQueue = new LinkedList<>();
        Queue<TreeNode> nextLevelQueue = new LinkedList<>();

        nextLevelQueue.add(root);
        List<Integer> result = new ArrayList<>();

        TreeNode node  = null;

        while(!nextLevelQueue.isEmpty()) {
            currentLevelQueue = nextLevelQueue;

            nextLevelQueue = new LinkedList<>();

            while(!currentLevelQueue.isEmpty()) {
                node = currentLevelQueue.poll();

                if (node.left != null) nextLevelQueue.offer(node.left);
                if (node.right != null) nextLevelQueue.offer(node.right);
            }
            result.add(node.val);
        }
        return result;
    }

    public static void main(String args[]) {
        BinaryTreeRightSideView obj  = new BinaryTreeRightSideView();
        List<Integer> res = obj.rightSideView(new TreeNode(1, new TreeNode(2, new TreeNode(6, null, new TreeNode(7)), null), new TreeNode(3, new TreeNode(4), new TreeNode(5))));
        System.out.println(res.toString());
    }
}
