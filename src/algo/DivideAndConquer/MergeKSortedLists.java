package algo.DivideAndConquer;

import common.ListNode;

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
 * The idea is to merge every 2 lists together and repeat until there is one left
 * [list 0, list 1, list 2, list 3, list 4]
 * merge(list 0, list 1); -> list 0
 * merge(list 2, list 3); -> list 2
 *
 * 2nd Iteration:
 * merge(list 0, list 2) -> list 0
 *
 * 3rd Iteration
 * merge(list 0, list 4) -> list 0
 *
 * We need to have an interval, which will help to guide which lists to merge
 *
 */
public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        int numberOfNodes = lists.length;
        int interval = 1;

        while(interval < numberOfNodes) {
            for(int i=0; i< numberOfNodes - interval; i = i+interval*2) {
                lists[i]= merge(lists[i], lists[i+interval]);
            }
            interval = interval * 2; // Pairwise merging
        }

        return numberOfNodes > 0 ? lists[0]:null;
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode tail = head;

        while(l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail= tail.next;
        }

        tail.next = (l1!= null) ? l1: l2;
        return head.next;
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
        MergeKSortedLists obj = new MergeKSortedLists();
        // [[1,4,5],[1,3,4],[2,6]]
        ListNode list1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode list3 = new ListNode(2, new ListNode(6));

        ListNode res = obj.mergeKLists(new ListNode[]{list1, list2, list3});
        obj.printLinkedList(res);
    }
}
