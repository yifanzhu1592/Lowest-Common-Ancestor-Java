import java.util.ArrayList;

public class LowestCommonAncestor {

    // return the key of the LCA of the two nodes
    public static int getLCA(binaryTree theTree, binaryTree.treeNode val1, binaryTree.treeNode val2) {
        if (val1 == null || val2 == null)
            return -1;

        ArrayList<Integer> route1 = new ArrayList<>();
        ArrayList<Integer> route2 = new ArrayList<>();
        binaryTree.treeNode node1 = val1;
        binaryTree.treeNode node2 = val2;

        do {
            route1.add(node1.key);
            node1 = node1.parent;
        } while (node1 != null);

        do {
            route2.add(node2.key);
            node2 = node2.parent;
        } while (node2 != null);

        for (Integer theNode : route1) {
            for (Integer searchNode : route2)
                if (theNode.equals(searchNode))
                    return theNode.intValue();
        }

        return -1;
    }

    // the binary tree that we will be traversing
    public class binaryTree {
        treeNode head;

        binaryTree() {
            head = null;
        }

        public int getKey(treeNode theNode) {
            if (theNode != null)
                return theNode.key;
            return -1;
        }

        public void add(int key, treeNode parent, boolean isLeft) {
            treeNode newNode = new treeNode(key, parent, null, null);
            if (head == null) { // check for first node
                head = newNode;
                return;
            }
            if (isLeft)
                parent.lChild = newNode;
            else
                parent.rChild = newNode;
        }

        class treeNode {
            treeNode parent, lChild, rChild;
            int key;

            treeNode(int key, treeNode parent, treeNode lChild, treeNode rChild) {
                this.key = key;
                this.parent = parent;
                this.lChild = lChild;
                this.rChild = rChild;
            }
        }
    }

    // return the key of the LCA
    public static int getDAG_LCA(DAG theDAG, int keyA, int keyB) {
        DAG.treeNode curNode = theDAG.head;

        int theNodeKey = bothInSubtreeOf(curNode, keyA, keyB);
        if (theNodeKey < 0)
            return -1;
        return theNodeKey;
    }

    public static final int FOUND_A = -2;
    public static final int FOUND_B = -3;

    // recursive function that traverses the tree and find the LCA
    private static int bothInSubtreeOf(DAG.treeNode curNode, int keyA, int keyB) {
        int curNodeVal = curNode.key;
        boolean foundA = false;
        boolean foundB = false;
        for (int i = 0; i < curNode.children.size(); i++) {
            int subTreeOf = bothInSubtreeOf(curNode.children.get(i), keyA, keyB);
            if (subTreeOf >= 0) {
                return subTreeOf;
            } else {
                if (subTreeOf == FOUND_B)
                    foundB = true;
                if (subTreeOf == FOUND_A)
                    foundA = true;
            }
        }
        if ((foundA && (foundB || curNodeVal == keyB)) || (foundB && curNodeVal == keyA))
            return curNodeVal;

        if (curNodeVal == keyA && curNodeVal == keyB)
            return curNodeVal;

        if (foundA || curNodeVal == keyA)
            return FOUND_A;
        if (foundB || curNodeVal == keyB)
            return FOUND_B;

        return -1;
    }

    // the DAG version
    public class DAG {
        treeNode head = null;

        public void addNode(int key, ArrayList<treeNode> parents) {
            treeNode theNode = new treeNode(key);
            if (head == null)
                head = theNode;
            if (parents != null)
                for (treeNode parentNode : parents)
                    if (parentNode != null)
                        parentNode.children.add(theNode);
        }

        public treeNode getNode(int key) {
            if (head != null) {
                return getNodeDFS(key, head);
            }
            return null;
        }

        private treeNode getNodeDFS(int key, treeNode node) {
            for (treeNode child : node.children) {
                treeNode theRetNode = getNodeDFS(key, child);
                if (theRetNode != null)
                    return theRetNode;
            }
            if (node.key == key)
                return node;
            return null;
        }

        public String printChildren(treeNode node) {
            String children = "";
            for (treeNode child : node.children)
                children += child.key + " ";
            return children;
        }

        class treeNode {
            public ArrayList<treeNode> children;
            public int key;

            treeNode(int key) {
                children = new ArrayList<treeNode>();
                this.key = key;
            }
        }
    }
}