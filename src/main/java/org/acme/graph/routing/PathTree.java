package org.acme.graph.routing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acme.graph.model.Edge;
import org.acme.graph.model.Graph;
import org.acme.graph.model.Vertex;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PathTree {

    private static final Logger log = LogManager.getLogger(DijkstraPathFinder.class);

    private Map<Vertex,PathNode> nodes;

    PathTree(){
        this.nodes = new HashMap<Vertex,PathNode>();
    }

   
    PathTree(Graph graph, Vertex origin){
        this.nodes = new HashMap<Vertex,PathNode>();
        log.trace("initGraph({})", origin);
		for (Vertex vertex : graph.getVertices()) {
			PathNode pathNode = new PathNode();
			pathNode.setCost(origin == vertex ? 0.0 : Double.POSITIVE_INFINITY);
			pathNode.setReachingEdge(null);
			pathNode.setVisited(false);
			this.nodes.put(vertex, pathNode);
		}
    }

    /**
	 * Construit le chemin en remontant les relations incoming edge
	 * 
	 * @param destination
	 * @return
	 */
    public List<Edge> getPath(Vertex destination){
        List<Edge> result = new ArrayList<>();

		Edge current = getNode(destination).getReachingEdge();
		do {
			result.add(current);
			current = getNode(current.getSource()).getReachingEdge();
		} while (current != null);

		Collections.reverse(result);
		return result;
    }

    /**
     * Récupère le PathNode associé à un vertex
     * 
     * @param vertex
     * @return
     */
    public PathNode getNode(Vertex vertex){
		return nodes.get(vertex);
	}

    
}
