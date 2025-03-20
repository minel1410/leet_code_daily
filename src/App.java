
package src;

import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        

        int[][] graf = {
                { 0, 2, 7 }, { 0, 1, 15 }, { 1, 2, 6 }, { 1, 2, 1}

        };


       Solution.Graph g = new Solution.Graph(graf);

       
    g.printGraph();

    List<List<Integer>> paths = g.bfsAllPaths(0, 3);

        for (List<Integer> path : paths) {
            System.out.println(path);
        }
}
}
