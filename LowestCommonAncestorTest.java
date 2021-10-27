import org.junit.Test;

public class LowestCommonAncestorTest {
    // tree functions
    //      0
    //    /   \
    //   1     2
    //  / \   / \
    // 3   4 5   6
    @Test
    public void testTree() {
        LowestCommonAncestor lca = new LowestCommonAncestor();
        LowestCommonAncestor.binaryTree tree = lca.new binaryTree();

        // empty tree
        assert (tree.getKey(tree.head) == -1);

        // head
        tree.add(0, null, true);
        assert (tree.getKey(tree.head) == 0);

        // children of the head
        tree.add(1, tree.head, true);
        tree.add(2, tree.head, false);
        assert (tree.getKey(tree.head.lChild) == 1);
        assert (tree.getKey(tree.head.rChild) == 2);

        // children of the children of the head
        tree.add(3, tree.head.lChild, true);
        tree.add(4, tree.head.lChild, false);
        tree.add(5, tree.head.rChild, true);
        tree.add(6, tree.head.rChild, false);
        assert (tree.getKey(tree.head.lChild.lChild) == 3);
        assert (tree.getKey(tree.head.lChild.rChild) == 4);
        assert (tree.getKey(tree.head.rChild.lChild) == 5);
        assert (tree.getKey(tree.head.rChild.rChild) == 6);
    }

    // valid LCA
    //      0
    //    /   \
    //   1     2
    //  / \   / \
    // 3   4 5   6
    @Test
    public void testLCA() {
        LowestCommonAncestor lca = new LowestCommonAncestor();
        LowestCommonAncestor.binaryTree tree = lca.new binaryTree();
        tree.add(0, null, true);
        tree.add(1, tree.head, true);
        tree.add(2, tree.head, false);
        tree.add(3, tree.head.lChild, true);
        tree.add(4, tree.head.lChild, false);
        tree.add(5, tree.head.rChild, true);
        tree.add(6, tree.head.rChild, false);

        // LCA of 0 and 0
        assert (LowestCommonAncestor.getLCA(tree, tree.head, tree.head) == 0);

        // LCA of 1 and 2
        assert (LowestCommonAncestor.getLCA(tree, tree.head.lChild, tree.head.rChild) == 0);

        // LCA of 1 and 6
        assert (LowestCommonAncestor.getLCA(tree, tree.head.lChild, tree.head.rChild.rChild) == 0);

        // LCA of 3 and 6
        assert (LowestCommonAncestor.getLCA(tree, tree.head.lChild.lChild, tree.head.rChild.rChild) == 0);

        // LCA of 1 and 4
        assert (LowestCommonAncestor.getLCA(tree, tree.head.lChild, tree.head.lChild.rChild) == 1);

        // LCA of 3 and 4
        assert (LowestCommonAncestor.getLCA(tree, tree.head.lChild.lChild, tree.head.lChild.rChild) == 1);
    }

    // invalid LCA
    //      0
    //    /   \
    //   1     2
    //  / \   / \
    // 3   4 5   6
    @Test
    public void testInvalid() {
        LowestCommonAncestor lca = new LowestCommonAncestor();
        LowestCommonAncestor.binaryTree tree = lca.new binaryTree();
        tree.add(0, null, true);
        tree.add(1, tree.head, true);
        tree.add(2, tree.head, false);
        tree.add(3, tree.head.lChild, true);
        tree.add(4, tree.head.lChild, false);
        tree.add(5, tree.head.rChild, true);
        tree.add(6, tree.head.rChild, false);

        // LCA of 0 and an empty node
        assert (LowestCommonAncestor.getLCA(tree, tree.head, null) == -1);

        // LCA of two empty nodes
        assert (LowestCommonAncestor.getLCA(tree, null, null) == -1);
    }
}