import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] array = {5,2,5,3,3};
        int[] costs = {3,7,8,6,9};
        System.out.println(getCost(array, costs));
    }

    public static int getSmallestSubarrayLength(int k, int[] arr) {
        int[] pref = new int[arr.length];
        pref[0] = arr[0];
        for (int i = 1; i < pref.length; i++) {
            pref[i] = pref[i - 1] + arr[i];
        }

        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (i == 0) {
                    if (pref[j] >= k) {
                        System.out.println(" pref[j] " + pref[j]);
                        minLength = Math.min(minLength, j + 1);
                    }
                } else {
                    if (pref[j] - pref[i - 1] >= k) {
                        int length = j - i + 1; // Corrected the length calculation
                        System.out.println(" pref[j] " + pref[j] + " pref[i] " + pref[i - 1]);
                        minLength = Math.min(minLength, length);
                    }
                }
            }
        }

        return minLength != Integer.MAX_VALUE ? minLength : -1;
    }

    public static int getCost(int[] array, int[] costs) {
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            if (!map.containsKey(array[i])) {
                map.put(array[i], new PriorityQueue<>());
            }
            map.get(array[i]).add(costs[i]);
        }

        int sum = 0;

        for (int key : map.keySet()) {
            PriorityQueue<Integer> pq = map.get(key);
            if (pq.size() > 1) {
                System.out.println(" key " + key);
                System.out.println(pq);

                while (pq.size() > 1) {
                    int cost = pq.poll();
                    System.out.println(" cost in list " + cost); // Log each added cost
                    sum += cost; 
                }
            }
        }

        return sum;
    }
}
