import java.util.ArrayList;
import java.util.Arrays;

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

    // graph functions for trees
    //       0
    //     ↙ ↘
    //    1	    2
    //  ↙ ↘  ↙ ↘
    // 3    4 5    6
	@Test
	public void testGraph() {
		LowestCommonAncestor lca = new LowestCommonAncestor();
		LowestCommonAncestor.DAG dag = lca.new DAG();
		dag.addNode(0, null);
		dag.addNode(1, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.head)));
		dag.addNode(2, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.head)));
		dag.addNode(3, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(1))));
		dag.addNode(4, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(1))));
		dag.addNode(5, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(2))));
		dag.addNode(6, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(2))));
	
        // children of 0
		assert (dag.printChildren(dag.head).equals("1 2 "));

        // children of 1
		assert (dag.printChildren(dag.getNode(1)).equals("3 4 "));

        // children of 2
		assert (dag.printChildren(dag.getNode(2)).equals("5 6 "));

        // children of 3
		assert (dag.printChildren(dag.getNode(3)).equals(""));

        // children of 4
		assert (dag.printChildren(dag.getNode(4)).equals(""));

        // children of 5
		assert (dag.printChildren(dag.getNode(5)).equals(""));

        // children of 6
		assert (dag.printChildren(dag.getNode(6)).equals(""));
	}

    // graph functions for dags
    //       0
    //     ↙ ↘
    //    1	    2
    //  ↙ ↘  ↙ ↘
    // 3     4     5
	@Test
	public void testDAG() {
		LowestCommonAncestor lca = new LowestCommonAncestor();
		LowestCommonAncestor.DAG dag = lca.new DAG();
		dag.addNode(0, null);
		dag.addNode(1, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.head)));
		dag.addNode(2, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.head)));
		dag.addNode(3, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(1))));
		dag.addNode(4, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(1), dag.getNode(2))));
		dag.addNode(5, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(2))));
	
        // children of 0
		assert (dag.printChildren(dag.head).equals("1 2 "));

        // children of 1
		assert (dag.printChildren(dag.getNode(1)).equals("3 4 "));

        // children of 2
		assert (dag.printChildren(dag.getNode(2)).equals("4 5 "));

        // children of 3
		assert (dag.printChildren(dag.getNode(3)).equals(""));

        // children of 4
		assert (dag.printChildren(dag.getNode(4)).equals(""));

        // children of 5
		assert (dag.printChildren(dag.getNode(5)).equals(""));
	}

    // LCA Tree DAG
    //       0
    //     ↙ ↘
    //    1	    2
    //  ↙ ↘  ↙ ↘
    // 3    4 5    6
	@Test
	public void testLCATreeDAG() {
		LowestCommonAncestor lca = new LowestCommonAncestor();
		LowestCommonAncestor.DAG dag = lca.new DAG();
		dag.addNode(0, null);
		dag.addNode(1, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.head)));
		dag.addNode(2, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.head)));
		dag.addNode(3, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(1))));
		dag.addNode(4, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(1))));
		dag.addNode(5, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(2))));
		dag.addNode(6, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(2))));
	
        // LCA of 1 and 2
		assert (LowestCommonAncestor.getDAG_LCA(dag, 1, 2) == 0);

        // LCA of 1 and 6
		assert (LowestCommonAncestor.getDAG_LCA(dag, 1, 6) == 0);

        // LCA of 5 and 6
		assert (LowestCommonAncestor.getDAG_LCA(dag, 5, 6) == 2);

        // LCA of 0 and 3
		assert (LowestCommonAncestor.getDAG_LCA(dag, 0, 3) == 0);

        // LCA of 3 and 6
		assert (LowestCommonAncestor.getDAG_LCA(dag, 3, 6) == 0);
	}

    // LCA Graph DAG
    //       0
    //     ↙ ↘
    //    1	    2
    //  ↙ ↘  ↙ ↘
    // 3     4     5
	@Test
	public void testLCAGraphDAG() {
		LowestCommonAncestor lca = new LowestCommonAncestor();
		LowestCommonAncestor.DAG dag = lca.new DAG();
		dag.addNode(0, null);
		dag.addNode(1, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.head)));
		dag.addNode(2, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.head)));
		dag.addNode(3, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(1))));
		dag.addNode(4, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(1), dag.getNode(2))));
		dag.addNode(5, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(2))));
	
        // LCA of 1 and 2
		assert (LowestCommonAncestor.getDAG_LCA(dag, 1, 2) == 0);

        // LCA of 1 and 5
		assert (LowestCommonAncestor.getDAG_LCA(dag, 1, 5) == 0);

        // LCA of 4 and 5
		assert (LowestCommonAncestor.getDAG_LCA(dag, 4, 5) == 2);

        // LCA of 3 and 4
		assert (LowestCommonAncestor.getDAG_LCA(dag, 3, 4) == 1);

        // LCA of 0 and 3
		assert (LowestCommonAncestor.getDAG_LCA(dag, 0, 3) == 0);

        // LCA of 3 and 5
		assert (LowestCommonAncestor.getDAG_LCA(dag, 3, 5) == 0);
	}

    // invalid LCA DAG
    //       0
    //     ↙ ↘
    //    1	    2
    //  ↙ ↘  ↙ ↘
    // 3     4     5
	@Test
	public void testInvalidDAG() {
		LowestCommonAncestor lca = new LowestCommonAncestor();
		LowestCommonAncestor.DAG dag = lca.new DAG();
		dag.addNode(0, null);
		dag.addNode(1, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.head)));
		dag.addNode(2, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.head)));
		dag.addNode(3, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(1))));
		dag.addNode(4, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(1), dag.getNode(2))));
		dag.addNode(5, new ArrayList<LowestCommonAncestor.DAG.treeNode>(Arrays.asList(dag.getNode(2))));
	
        // LCA of 0 and an empty node
        assert (LowestCommonAncestor.getDAG_LCA(dag, 0, -1) == -1);

        // LCA of two empty nodes
        assert (LowestCommonAncestor.getDAG_LCA(dag, -1, -2) == -1);
	}
}