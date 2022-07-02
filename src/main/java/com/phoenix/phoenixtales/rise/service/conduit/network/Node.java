package com.phoenix.phoenixtales.rise.service.conduit.network;

import java.util.ArrayList;
import java.util.Arrays;

public class Node {
    private ArrayList<Node> nodes;

    public Node(Node... nodes) {
        this.nodes = (ArrayList<Node>) Arrays.asList(nodes);
    }
}
