import java.util.*;
/**
 * Minimum spanning tree
 */
public class MINIMUM_SPANNING_TREE {
    public static Set<WeightedEdge> minimum_spanning_tree(List<WeightedEdge> weightedEdges) {
        Map<Node,Set<Node>> groupByNode = new HashMap<>();
        Set<WeightedEdge> minSpanningTree = new HashSet<>();

        Collections.sort(weightedEdges);

        for (WeightedEdge edge : weightedEdges) {
            Node vertex_u = edge.node1;
            Node vertex_v = edge.node2;
            //System.out.printf("u: %s, v: %s weight: %d\n", vertex_u.getValue(), vertex_v.getValue(), edge.weight);
            if (!groupByNode.containsKey(vertex_u)){
                groupByNode.put(vertex_u, new HashSet<>(Arrays.asList(vertex_u)));
            }
            if (!groupByNode.containsKey(vertex_v)){
                groupByNode.put(vertex_v, new HashSet<>(Arrays.asList(vertex_v)));
            }

            if (groupByNode.get(vertex_u) != groupByNode.get(vertex_v)) {
                minSpanningTree.add(edge);
                groupByNode = update(groupByNode, vertex_u, vertex_v);
                // no further looping here: update merges groups and updates mapping for all nodes
            }
        }
        return minSpanningTree;
    }

    public static Map<Node,Set<Node>> update(Map<Node,Set<Node>> groupByNode, Node vertex_u, Node vertex_v) {
        Set<Node> setU = groupByNode.get(vertex_u);
        Set<Node> setV = groupByNode.get(vertex_v);

        // create merged set (new object) containing all nodes from both groups
        Set<Node> merged = new HashSet<>(setU);
        merged.addAll(setV);

        // update the map to point every node in merged to the merged set
        for (Node n : merged) {
            groupByNode.put(n, merged);
        }

        return groupByNode;
    }
}
