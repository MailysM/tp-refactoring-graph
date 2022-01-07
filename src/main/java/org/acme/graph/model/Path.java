package org.acme.graph.model;

import java.util.ArrayList;
import java.util.List;

public class Path {
    
    private List<Edge> edges;


    public Path(){
        this.edges = new ArrayList<Edge>();
    }

    public Path(List<Edge> edges){
        this.edges = edges;
    }

    public double getLength(){
        double longueur = 0;
        for (Edge edge : edges) {
            longueur += edge.getCost();
        }
        return longueur;
    }

    public Edge getEdge(int index){
        return this.edges.get(index);
    }

    public List<Edge> getEdges(){
        return edges;
    }

}
