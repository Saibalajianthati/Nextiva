import java.util.*;

public class SocialNetwork {
    // Adjacency list to store the friends of each person
    private Map<String, Set<String>> friendsMap;

    // Constructor
    public SocialNetwork() {
        friendsMap = new HashMap<>();
    }

    // Method to add a friend connection
    public void addFriend(String person1, String person2) {
        friendsMap.computeIfAbsent(person1, k -> new HashSet<>()).add(person2);
        friendsMap.computeIfAbsent(person2, k -> new HashSet<>()).add(person1);
    }

    // Method to get all friends of a person
    public Set<String> getFriends(String person) {
        return friendsMap.getOrDefault(person, Collections.emptySet());
    }

    // Method to find common friends between two people
    public Set<String> findCommonFriends(String person1, String person2) {
        Set<String> friends1 = getFriends(person1);
        Set<String> friends2 = getFriends(person2);
        Set<String> commonFriends = new HashSet<>(friends1);
        commonFriends.retainAll(friends2);
        return commonFriends;
    }

    // Method to find the nth connection between two people
    public int findNthConnection(String start, String end) {
        if (start.equals(end)) return 0; // If start and end are the same, return 0
        
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> distanceMap = new HashMap<>();
        
        queue.add(start);
        distanceMap.put(start, 0);
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDistance = distanceMap.get(current);
            
            for (String friend : getFriends(current)) {
                if (!distanceMap.containsKey(friend)) {
                    queue.add(friend);
                    distanceMap.put(friend, currentDistance + 1);
                    
                    if (friend.equals(end)) {
                        return currentDistance + 1;
                    }
                }
            }
        }
        
        return -1; // If no connection found
    }

    // Method to handle user input
    public static void main(String[] args) {
        SocialNetwork sn = new SocialNetwork();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add a friend");
            System.out.println("2. Find all friends of a person");
            System.out.println("3. Find common friends between two people");
            System.out.println("4. Find nth connection between two people");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the first person: ");
                    String person1 = scanner.nextLine();
                    System.out.print("Enter the second person: ");
                    String person2 = scanner.nextLine();
                    sn.addFriend(person1, person2);
                    System.out.println("Friendship added.");
                    break;

                case 2:
                    System.out.print("Enter the person whose friends you want to find: ");
                    String person = scanner.nextLine();
                    System.out.println("Friends of " + person + ": " + sn.getFriends(person));
                    break;

                case 3:
                    System.out.print("Enter the first person: ");
                    String firstPerson = scanner.nextLine();
                    System.out.print("Enter the second person: ");
                    String secondPerson = scanner.nextLine();
                    System.out.println("Common friends: " + sn.findCommonFriends(firstPerson, secondPerson));
                    break;

                case 4:
                    System.out.print("Enter the start person: ");
                    String startPerson = scanner.nextLine();
                    System.out.print("Enter the end person: ");
                    String endPerson = scanner.nextLine();
                    int connection = sn.findNthConnection(startPerson, endPerson);
                    if (connection == -1) {
                        System.out.println("No connection found.");
                    } else {
                        System.out.println("The nth connection is: " + connection);
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
