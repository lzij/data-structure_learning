import java.util.LinkedList;
import java.util.Queue;

public class MyGraph {
    private int v;
    private LinkedList<Integer>[] adj;

    public MyGraph() {
    }

    public MyGraph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s,int t){
        adj[s].add(t);
        adj[t].add(s);
    }

    public void bfs(int s,int t){
        if(s == t) return;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }
        queue.add(s);
        visited[s] = true;
        while(!queue.isEmpty()){
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i);
                if(!visited[q]){
                    prev[q] = w;
                    if(q == t){
                        print(prev,s,t);
                        return;
                    }
                    queue.add(q);
                    visited[q] = true;
                }
            }
        }
    }

    private void print(int[] prev,int s,int t){
        if(prev[t] != -1 && s != t){
            print(prev,s,t);
        }
        System.out.print(t + " ");
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0,4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(3, 7);
        graph.addEdge(6, 7);
        graph.bfs(4,3);
    }
