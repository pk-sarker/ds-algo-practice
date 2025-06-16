package ds.PriorityQueue;

import common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 *
 */
public class MergeKSortedListsPQ {

    public ListNode mergeKLists(ListNode[] lists) {
        // Creating head node of the result/merged linked list
        ListNode head = new ListNode(0);
        ListNode currentNode = head; // Pointing the current node to the head

        // Create a priority queue to store the ListNodes and keep then in ascending order
        // For this we need to create a custom comparator to compare two ListNode
        // Min Heap
        PriorityQueue<ListNode> queue = new PriorityQueue<>(
                new Comparator<ListNode>() {
                    @Override
                    public int compare(ListNode l1, ListNode l2) {
                        if (l1.val > l2.val) {
                            return 1;
                        } else if (l1.val==l2.val) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                }
        );
        // Add the list node(heads) in the queue
        for (ListNode node: lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        // Now remove nodes for queue in ascending order and add to the result Linked list.

        while(!queue.isEmpty()) {
            currentNode.next = queue.poll();; // Add polled node from the queue to currentNode's next
            currentNode = currentNode.next; // move current node to its next node
            if (currentNode.next != null) {
                queue.add(currentNode.next);
            }
        }
        return head.next;// Why head.next, because we have a dummy node at the begining.
    }

    public void printLinkedList(ListNode head) {
        ListNode current = head;

        while(current.next != null) {
            System.out.print(current.val);
            if (current.next!= null) {
                System.out.print("->");
            }
            current = current.next;
        }
    }

    public static void main(String args[]) {
        MergeKSortedListsPQ obj = new MergeKSortedListsPQ();
        // [[1,4,5],[1,3,4],[2,6]]
        ListNode list1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode list3 = new ListNode(2, new ListNode(6));

        ListNode res = obj.mergeKLists(new ListNode[]{list1, list2, list3});
        obj.printLinkedList(res);
    }
}
