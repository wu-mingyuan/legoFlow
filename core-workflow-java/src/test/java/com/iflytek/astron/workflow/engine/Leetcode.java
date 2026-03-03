package com.iflytek.astron.workflow.engine;

import net.sf.jsqlparser.statement.merge.Merge;
import org.junit.jupiter.api.condition.EnabledOnOs;

import java.util.*;

/**
 * @author Wu Mingyuan
 * @since 2026-02-26-17:10
 */
public class Leetcode {


}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * 题目：相交链表
 *
 * 思路：两个链表各自向前遍历，当为空时就去遍历对方，这样当两者相等（为相交节点或者都为null）时输出
 */
class Solution160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode q = headA;
        ListNode p = headB;

        if(q == null || p == null){
            return null;
        }

        while(q != p){
            if(q != null){       //这里如果是q.next != null 那么q和p都会停在对方的最后一个节点上，这样永远无法相等
                q = q.next;
            }else{
                q = headB;
            }

            if(p != null){
                p = p.next;
            }else{
                p = headA;
            }
        }

        return q;
    }
}

/**
 * 题目：反转链表
 *
 * 思路：如下
 */
class Solution206 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
}

/**
 * 题目：反转链表的一部分
 *
 * 思路：设置一个p0，记录反转的开始位置，后面正常进行局部链表反转，最后利用p0完成整个的反转
 */
class Solution92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode p0 = dummy;
        for(int i = 0; i < left - 1; i++){
            p0 = p0.next;
        }

        ListNode cur = p0.next;
        ListNode pre = null;
        for(int i = 0; i < right - left + 1; i++){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;;
            cur = next;
        }

        p0.next.next = cur;
        p0.next = pre;

        return dummy.next;      //不能return head，因为当head节点也反转时，头节点就换了
    }
}

/**
 * 题目：判断链表是否是回文链表，要求在原链表上操作
 *
 * 思路：将链表从中间分开，右半部分反转一下，然后两个链表分别从头遍历，看看是不是每个值都相等
 */
class Solution234 {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode leftStart = head;
        ListNode rightStart = reverse(slow.next);

        return result(leftStart, rightStart);
    }

    public ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public boolean result(ListNode left, ListNode right){
        while(right != null){
            if(left.val != right.val){
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }
}

/**
 * 题目：判断链表是否有环
 *
 * 思路：快慢指针法，慢指针一次走一步，快指针一次走两步，如果链表有环，那么两指针一定会相遇
 */
class Solution141 {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }
}

/**
 * 题目：判断题目是否有环，如果有，则输出环开始的那个节点
 *
 * 思路：第一步还是慢指针法，慢指针一次走一步，快指针一次走两步，如果链表有环，那么两指针一定会相遇；
 *      根据数学推理，当两指针相遇时，头节点到环开始节点的距离 == 相遇节点到环开始距离；
 *      因此，把一个指针重新指向头节点，然后两个指针同时同速度移动，直到相遇，此时相遇的节点就是环开始的节点
 */
class Solution142 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow){
                break;
            }
        }

        if(fast == null || fast.next == null){
            return null;
        }

        slow = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}

/**
 * 题目：合并两个有序链表
 *
 * 思路；如下
 */
class Solution21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        ListNode node1 = list1;
        ListNode node2 = list2;

        while(node1 != null && node2 != null){
            if(node1.val <= node2.val){
                pre.next = node1;
                node1 = node1.next;
            }else{
                pre.next = node2;
                node2 = node2.next;
            }
            pre = pre.next;
        }

        if(node1 == null){
            pre.next = node2;
        }else{
            pre.next = node1;
        }

        return dummy.next;
    }
}

/**
 * 题目：两数相加
 *
 * 思路：如下
 */
class Solution2 {
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        ListNode node1 = l1;
        ListNode node2 = l2;
        int carry = 0;

        while(node1 != null || node2 != null){
            int x = (node1 == null)? 0 : node1.val;
            int y = (node2 == null)? 0 : node2.val;
            int sum = carry + x + y;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if(node1 != null) node1 = node1.next;
            if(node2 != null) node2 = node2.next;
        }
        if(carry > 0){
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }
}

/**
 * 题目：删除链表的倒数第n个节点
 *
 * 思路：快慢指针法，快指针先往前走n步，然后快慢一起走直到快指针到头，此时慢指针的位置在目标输出节点的前一个
 */
class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}

/**
 * 题目：两个一翻转链表
 *
 *思路：如下
 */
class Solution24 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while(pre.next != null && pre.next.next != null){
            ListNode cur = pre.next;
            ListNode next = cur.next;

            cur.next = next.next;
            next.next = cur;
            pre.next = next;

            pre = cur;
        }
        return dummy.next;
    }
}

/**
 * 题目：k个一组翻转链表
 *
 * 思路：k个一组，先断开要翻转的链表，每组内部链表反转，然后再合并
 */
class Solution25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        while(end != null){
            for(int i = 0; i < k && end != null; i++){
                end = end.next;
            }
            if(end == null) break;

            //断开每组链表
            ListNode start = pre.next;
            ListNode nextGroupStart = end.next;
            end.next = null;

            //连接每组链表
            pre.next = reverse(start);
            start.next = nextGroupStart;
            pre = start;
            end = start;
        }
        return dummy.next;
    }

    public ListNode reverse(ListNode cur){
        ListNode dummy = new ListNode(-1);
        dummy.next = cur;
        ListNode pre = dummy;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}

/**
 * 题目：对一个随机链表进行深度拷贝
 *
 * 思路：新建一个Map<Node, Node>，key里填老链表，value里填新链表
 */
class Solution138 {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node p = head;
        while(p != null){
            map.put(p, new Node(p.val));
            p = p.next;
        }
        p = head;
        while(p != null){
            map.get(p).next = map.get(p.next);
            map.get(p).random = map.get(p.random);
            p = p.next;
        }
        return map.get(head);
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}

/**
 * 题目：链表排序
 *
 * 思路：用归并排序，一个大链表，拆成小链表，小链表再两两合并，合并的时候就是用普通的两个升序链表排序的方法
 */
class Solution148 {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = slow.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode left = dummy.next;
        ListNode right = slow.next;
        slow.next = null;
        return merge(sortList(left), sortList(right));
    }

    public  ListNode merge(ListNode head1, ListNode head2){
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        ListNode node1 = head1;
        ListNode node2 = head2;
        while(node1 != null && node2 != null){
            if(node1.val <= node2.val){
                cur.next = node1;
                node1 = node1.next;
            }else{
                cur.next = node2;
                node2 = node2.next;
            }
            cur = cur.next;
        }
        if(node1 == null){
            cur.next = node2;
        }else{
            cur.next = node1;
        }
        return dummy.next;
    }
}

/**
 * 题目：合并n个升序链表
 *
 * 思路：使用一个优先级队列维持每个链表头节点的大小排序
 */
class Solution23 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);
        for(ListNode list: lists){
            if(list != null){
                queue.add(list);
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(!queue.isEmpty()){
            ListNode miniNode = queue.poll();
            cur.next = miniNode;
            cur = cur.next;
            if(miniNode.next != null){
                queue.add(miniNode.next);
            }
        }
        cur.next = null;
        return dummy.next;
    }
}

/**
 * 题目：二叉树的中序遍历
 *
 * 思路：前、中、右
 */
class Solution94 {
    List<Integer> res = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            res.add(root.val);
            inorderTraversal(root.right);
        }
        return res;
    }
}

/**
 * 题目：二叉树的最大深度
 *
 * 思路：使用递归，类似前序遍历，先中、再左右
 */
class Solution104 {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

/**
 * 题目：翻转二叉树
 *
 * 思路：使用递归，如下
 */
class Solution226 {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        TreeNode l = invertTree(root.left);
        TreeNode r = invertTree(root.right);
        root.right = l;
        root.left = r;
        return root;
    }
}

/**
 * 题目：检查所给的二叉树是否是对称二叉树
 *
 * 思路：使用递归，如下
 */
class Solution101 {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        boolean curIsSymmetric = (left.val == right.val);
        boolean innerIsSymmetric = isSymmetric(left.right, right.left);
        boolean outerIsSymmetric = isSymmetric(left.left, right.right);
        return curIsSymmetric && innerIsSymmetric && outerIsSymmetric;
    }
}

/**
 * 题目：计算二叉树的直径
 *
 * 思路：使用递归，要考虑到最大直径有可能不经过根结点，所以要在求最大深度的过程中记录最大值。
 */
class Solution543 {
    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return res;
        maxDepth(root);
        return res;
    }

    public int maxDepth(TreeNode node){
        if(node == null) return 0;
        int leftMax = maxDepth(node.left);
        int rightMax = maxDepth(node.right);
        res = Math.max(res, leftMax + rightMax);
        return Math.max(leftMax, rightMax) + 1;
    }
}

/**
 * 题目：二叉树的层序遍历
 *
 * 思路：使用迭代法，使用一个queue记录下一层的所有节点，然后依次输出
 */
class Solution102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }
}

/**
 * 题目：将一个升序的有序数组转化成二叉平衡搜索树
 *
 * 思路：搜索二叉树的中序遍历就是正常的升序，因此找中间节点，从中间再分成左子树和右子树，后序递归
 */
class Solution108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end){
        if(start > end) return null;
        int mid = (start + end) / 2;
        TreeNode cur = new TreeNode(nums[mid]);
        TreeNode left = sortedArrayToBST(nums, start, mid - 1);
        TreeNode right = sortedArrayToBST(nums, mid + 1, end);
        cur.left = left;
        cur.right = right;
        return cur;
    }
}

/**
 * 题目：验证一棵树是二叉搜索树
 *
 * 思路：递归，如下
 */
class Solution98 {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);    //用Integer的话，力扣会报错
    }

    public boolean isValidBST(TreeNode cur, long upBound, long downBound){
        if(cur == null) return true;
        boolean curFlag = (cur.val < upBound && cur.val > downBound);
        boolean leftFlag = isValidBST(cur.left, cur.val, downBound);
        boolean rightFlag = isValidBST(cur.right, upBound, cur.val);
        return curFlag && leftFlag && rightFlag;
    }
}

/**
 * 题目：找到二叉搜索树的第k小的元素
 *
 * 思路：搜索二叉树的中序遍历就是正常的升序，因此采用中序遍历，遍历一个count++，然后和k比对
 */
class Solution230 {
    int count = 0;
    int res = 0;
    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return res;
    }

    public void inOrder(TreeNode cur, int k){
        if(cur == null) return;
        inOrder(cur.left, k);
        count++;
        if(count == k) res = cur.val;
        inOrder(cur.right, k);
    }
}

/**
 * 题目：输出二叉树的右视图
 *
 * 思路：本质上就是二叉树的层序遍历，输出每一层的随后一个元素
 */
class Solution199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if(cur.left != null) queue.add(cur.left);
                if(cur.right != null) queue.add(cur.right);
                if(i == size - 1) res.add(cur.val);
            }

        }
        return res;
    }
}

/**
 * 题目：写一个LRU缓存
 *
 * 思路：双向链表加头尾哨兵
 */
class LRUCache {

    class Node {
        int key;
        int value;
        Node pre;
        Node next;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    Map<Integer, Node> cache;
    int size;
    int capacity;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.size = 0;
        this.capacity = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if(cache.containsKey(key)){
            Node target = cache.get(key);
            removeNode(target);
            putToHead(target);
            return target.value;
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)){
            Node cur = cache.get(key);
            cur.value = value;
            removeNode(cur);
            putToHead(cur);
            return;
        }

        Node cur = new Node(key, value);
        cache.put(key, cur);
        putToHead(cur);
        size++;
        if(size > capacity){
            cache.remove(tail.pre.key);
            removeNode(tail.pre);
            size--;
        }
    }

    public void removeNode(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void putToHead(Node node){
        node.next = head.next;
        node.pre = head;
        head.next = node;
        node.next.pre = node;
    }


}

/**
 * 题目：岛屿数量
 *
 * 思路：深度优先遍历算法
 */
class Solution200 {
    public int numIslands(char[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                if(grid[i][j] == '1'){
                    count++;
                    fill(grid, i, j);
                }
            }
        }
        return count;
    }

    public void fill(char[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0'){
            return;
        }

        grid[i][j] = '0';
        fill(grid, i, j + 1);
        fill(grid, i, j - 1);
        fill(grid, i + 1, j);
        fill(grid, i - 1, j);
    }
}




























