import java.util.*;

public class GoogleMapsDijkstra {

    static class Edge {
        int destination;
        int distance;

        Edge(int destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }
    }

    public static void dijkstra(List<List<Edge>> graph, int source) {

        int n = graph.size();
        int[] dist = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {

            int[] current = pq.poll();
            int u = current[0];

            for (Edge edge : graph.get(u)) {

                int v = edge.destination;
                int weight = edge.distance;

                if (dist[u] + weight < dist[v]) {

                    dist[v] = dist[u] + weight;

                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        System.out.println("Shortest Distance from Majestic:");

        for (int i = 0; i < n; i++) {
            System.out.println("Location " + i +
                    " -> Distance = " + dist[i] + " km");
        }
    }

    public static void main(String[] args) {

        int vertices = 5;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < vertices; i++)
            graph.add(new ArrayList<>());

        // Example road network

        graph.get(0).add(new Edge(1, 4)); // Majestic -> MG Road
        graph.get(0).add(new Edge(2, 8)); // Majestic -> Indiranagar

        graph.get(1).add(new Edge(2, 2));
        graph.get(1).add(new Edge(3, 5));

        graph.get(2).add(new Edge(3, 5));
        graph.get(2).add(new Edge(4, 10));

        graph.get(3).add(new Edge(4, 3));

        dijkstra(graph, 0);
    }
}