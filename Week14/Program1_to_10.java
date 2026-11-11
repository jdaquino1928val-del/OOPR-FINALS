import java.util.Scanner;

public class Program1_to_10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("        WEEK 14- F_ACTIVITY 1-Java Array         ");
            System.out.println(" 1. Program 1: Exam Score Statistics ");
            System.out.println(" 2. Program 2: Product Reorder Checker ");
            System.out.println(" 3. Program 3: Branch Sales Matrix ");
            System.out.println(" 4. Program 4: Classroom Seat Booking ");
            System.out.println(" 5. Program 5: Temperature Monitoring ");
            System.out.println(" 6. Program 6: Shopping Cart & Checkout ");
            System.out.println(" 7. Program 7: Warehouse Stock Transfers ");
            System.out.println(" 8. Program 8: Student Performance Analyzer ");
            System.out.println(" 9. Program 9: Consecutive Theater Reservations");
            System.out.println("10. Program 10: Weekly Inventory & Lost Sales");
            System.out.println("11. Exit System");
            
            int choice = readInt(scanner, "Select an option (1-11): ", 1, 11);

            if (choice == 11) {
                System.out.println("Exiting system. Goodbye!");
                break;
            }

            switch (choice) {
                case 1:  runProgram1(scanner); break;
                case 2:  runProgram2(scanner); break;
                case 3:  runProgram3(scanner); break;
                case 4:  runProgram4(scanner); break;
                case 5:  runProgram5(scanner); break;
                case 6:  runProgram6(scanner); break;
                case 7:  runProgram7(scanner); break;
                case 8:  runProgram8(scanner); break;
                case 9:  runProgram9(scanner); break;
                case 10: runProgram10(scanner); break;
            }
        }
        scanner.close();
    }

    private static int readInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int val = scanner.nextInt();
                if (val >= min && val <= max) {
                    return val;
                }
            } else {
                scanner.next();
            }
            System.out.println("Invalid input! Must be a whole number between " + min + " and " + max + ".");
        }
    }

    public static void runProgram1(Scanner scanner) {
        System.out.println("\n--- PROGRAM 1: EXAM SCORE STATISTICS ---");
        int numStudents = readInt(scanner, "Enter number of students (1-50): ", 1, 50);
        int[] scores = new int[numStudents];

        for (int i = 0; i < numStudents; i++) {
            scores[i] = readInt(scanner, "Enter score for Student " + (i + 1) + " (0-100): ", 0, 100);
        }

        int sum = 0, highest = scores[0], lowest = scores[0], passCount = 0, failCount = 0;

        for (int score : scores) {
            sum += score;
            if (score > highest) highest = score;
            if (score < lowest) lowest = score;
            if (score >= 75) passCount++;
            else failCount++;
        }

        double average = (double) sum / numStudents;

        System.out.println("\n=== EXAM SCORE SUMMARY ===");
        System.out.printf("Average Score: %.2f\n", average);
        System.out.println("Highest Score: " + highest);
        System.out.println("Lowest Score:  " + lowest);
        System.out.println("Passing Count (>= 75): " + passCount);
        System.out.println("Failing Count (< 75):  " + failCount);

        System.out.print("Student(s) tied for highest score (" + highest + "): ");
        boolean first = true;
        for (int i = 0; i < numStudents; i++) {
            if (scores[i] == highest) {
                if (!first) System.out.print(", ");
                System.out.print("Student " + (i + 1));
                first = false;
            }
        }
        System.out.println();
    }

    public static void runProgram2(Scanner scanner) {
        System.out.println("\n--- PROGRAM 2: PRODUCT REORDER CHECKER ---");
        String[] names = {"Notebook", "Pen", "Folder", "USB"};
        int[] reorder = {5, 10, 5, 2};
        int[] target = {20, 30, 20, 10};
        int[] stock = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            stock[i] = readInt(scanner, "Enter current stock for " + names[i] + " (0-10000): ", 0, 10000);
        }

        int totalStock = 0, totalSuggestedOrder = 0;

        System.out.println("\n=== INVENTORY & REORDER REPORT ===");
        System.out.printf("%-12s %-15s %-15s %-15s %-20s\n", "Product", "Current Stock", "Reorder Level", "Target Level", "Status / Order Qty");
        System.out.println("----------------------------------------------------------------------------------");

        for (int i = 0; i < names.length; i++) {
            totalStock += stock[i];
            if (stock[i] <= reorder[i]) {
                int suggestedOrder = target[i] - stock[i];
                totalSuggestedOrder += suggestedOrder;
                System.out.printf("%-12s %-15d %-15d %-15d %-20s\n", names[i], stock[i], reorder[i], target[i], "FLAGGED (Order: " + suggestedOrder + ")");
            } else {
                System.out.printf("%-12s %-15d %-15d %-15d %-20s\n", names[i], stock[i], reorder[i], target[i], "Sufficient Stock");
            }
        }
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Total Units in Stock:  " + totalStock);
        System.out.println("Total Suggested Order: " + totalSuggestedOrder + " units");
    }

    public static void runProgram3(Scanner scanner) {
        System.out.println("\n--- PROGRAM 3: BRANCH SALES MATRIX ---");
        int[][] sales = new int[3][5];

        for (int r = 0; r < 3; r++) {
            System.out.println("Enter sales for Branch " + (r + 1) + ":");
            for (int c = 0; c < 5; c++) {
                sales[r][c] = readInt(scanner, "  Day " + (c + 1) + " (PHP 0-1000000): ", 0, 1000000);
            }
        }

        int[] branchTotals = new int[3];
        int[] dayTotals = new int[5];
        int grandTotal = 0;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 5; c++) {
                branchTotals[r] += sales[r][c];
                dayTotals[c] += sales[r][c];
                grandTotal += sales[r][c];
            }
        }

        System.out.println("\n=== SALES MATRIX REPORT (PHP) ===");
        System.out.printf("%-10s %-8s %-8s %-8s %-8s %-8s %-12s\n", "Branch", "Day 1", "Day 2", "Day 3", "Day 4", "Day 5", "Branch Total");
        System.out.println("------------------------------------------------------------------");
        for (int r = 0; r < 3; r++) {
            System.out.printf("%-10s %-8d %-8d %-8d %-8d %-8d PHP %-10d\n",
                "Branch " + (r + 1), sales[r][0], sales[r][1], sales[r][2], sales[r][3], sales[r][4], branchTotals[r]);
        }
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-10s %-8d %-8d %-8d %-8d %-8d PHP %-10d\n", "Day Totals", dayTotals[0], dayTotals[1], dayTotals[2], dayTotals[3], dayTotals[4], grandTotal);

        int highestBranchTotal = branchTotals[0];
        for (int r = 1; r < 3; r++) {
            if (branchTotals[r] > highestBranchTotal) {
                highestBranchTotal = branchTotals[r];
            }
        }

        System.out.print("\nHighest Sales Branch Total (PHP " + highestBranchTotal + "): ");
        boolean first = true;
        for (int r = 0; r < 3; r++) {
            if (branchTotals[r] == highestBranchTotal) {
                if (!first) System.out.print(", ");
                System.out.print("Branch " + (r + 1));
                first = false;
            }
        }
        System.out.println();
    }

    public static void runProgram4(Scanner scanner) {
        System.out.println("\n--- PROGRAM 4: CLASSROOM SEAT BOOKING ---");
        int[][] seats = new int[4][5];
        int totalAvailable = 20;

        while (true) {
            System.out.println("\n--- SEAT BOOKING MENU ---");
            System.out.println("1. Display Matrix");
            System.out.println("2. Book Seat");
            System.out.println("3. Cancel Seat");
            System.out.println("4. Return to Main Menu");
            int opt = readInt(scanner, "Select option (1-4): ", 1, 4);

            if (opt == 4) break;

            if (opt == 1) {
                displaySeats(seats);
                System.out.println("Available Seats: " + totalAvailable + " / 20");
            } else if (opt == 2) {
                int r = readInt(scanner, "Enter Row (1-4): ", 1, 4) - 1;
                int c = readInt(scanner, "Enter Column (1-5): ", 1, 5) - 1;

                if (seats[r][c] == 1) {
                    System.out.println("Error: Seat at Row " + (r + 1) + ", Col " + (c + 1) + " is ALREADY BOOKED.");
                } else {
                    seats[r][c] = 1;
                    totalAvailable--;
                    System.out.println("Successfully booked Row " + (r + 1) + ", Col " + (c + 1) + ".");
                }
                System.out.println("Available Seats Remaining: " + totalAvailable);
            } else if (opt == 3) {
                int r = readInt(scanner, "Enter Row (1-4): ", 1, 4) - 1;
                int c = readInt(scanner, "Enter Column (1-5): ", 1, 5) - 1;

                if (seats[r][c] == 0) {
                    System.out.println("Error: Seat at Row " + (r + 1) + ", Col " + (c + 1) + " is ALREADY EMPTY.");
                } else {
                    seats[r][c] = 0;
                    totalAvailable++;
                    System.out.println("Successfully cancelled booking for Row " + (r + 1) + ", Col " + (c + 1) + ".");
                }
                System.out.println("Available Seats Remaining: " + totalAvailable);
            }
        }
    }

    private static void displaySeats(int[][] seats) {
        System.out.println("\n    Col 1  Col 2  Col 3  Col 4  Col 5");
        for (int r = 0; r < 4; r++) {
            System.out.print("Row " + (r + 1) + "  ");
            for (int c = 0; c < 5; c++) {
                System.out.print("[" + (seats[r][c] == 1 ? "X" : "O") + "]    ");
            }
            System.out.println();
        }
    }

    public static void runProgram5(Scanner scanner) {
        System.out.println("\n--- PROGRAM 5: TEMPERATURE MONITORING ---");
        int[][] temps = new int[3][4];

        for (int s = 0; s < 3; s++) {
            System.out.println("Enter temperatures for Sensor " + (s + 1) + ":");
            for (int p = 0; p < 4; p++) {
                temps[s][p] = readInt(scanner, "  Period " + (p + 1) + " (-20 to 60 °C): ", -20, 60);
            }
        }

        System.out.println("\n=== SENSOR AVERAGE TEMPERATURES ===");
        int maxTemp = temps[0][0];

        for (int s = 0; s < 3; s++) {
            int sum = 0;
            for (int p = 0; p < 4; p++) {
                sum += temps[s][p];
                if (temps[s][p] > maxTemp) maxTemp = temps[s][p];
            }
            System.out.printf("Sensor %d Average: %.2f °C\n", (s + 1), (double) sum / 4);
        }

        System.out.println("\n=== HIGH TEMPERATURE ALERTS (> 35°C) ===");
        boolean alertFound = false;
        for (int s = 0; s < 3; s++) {
            for (int p = 0; p < 4; p++) {
                if (temps[s][p] > 35) {
                    System.out.println("ALERT: Sensor " + (s + 1) + " at Period " + (p + 1) + " recorded " + temps[s][p] + " °C");
                    alertFound = true;
                }
            }
        }
        if (!alertFound) System.out.println("None (No readings strictly exceeded 35°C).");

        System.out.println("\n=== MAXIMUM TEMPERATURE RECORD ===");
        System.out.println("Maximum Reading: " + maxTemp + " °C");
        System.out.print("Recorded at: ");
        boolean first = true;
        for (int s = 0; s < 3; s++) {
            for (int p = 0; p < 4; p++) {
                if (temps[s][p] == maxTemp) {
                    if (!first) System.out.print(", ");
                    System.out.print("[Sensor " + (s + 1) + ", Period " + (p + 1) + "]");
                    first = false;
                }
            }
        }
        System.out.println();
    }

    public static void runProgram6(Scanner scanner) {
        System.out.println("\n--- PROGRAM 6: SHOPPING CART AND CHECKOUT ---");
        String[] names = {"USB", "Notebook", "Pen", "Folder"};
        int[] prices = {350, 200, 20, 15};
        int[] stock = {10, 10, 20, 20};
        int[] cart = new int[4];

        while (true) {
            System.out.println("\n--- STORE SHOPPING MENU ---");
            System.out.println("1. Add Item to Cart");
            System.out.println("2. Remove Item Quantity");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout & Pay");
            System.out.println("5. Cancel Cart");
            System.out.println("6. Exit to Main Menu");
            int opt = readInt(scanner, "Select option (1-6): ", 1, 6);

            if (opt == 6) break;

            if (opt == 1) {
                displayStoreItems(names, prices, stock);
                int choice = readInt(scanner, "Select product (1-4): ", 1, 4) - 1;
                int qty = readInt(scanner, "Enter quantity to add: ", 1, 100);

                if (cart[choice] + qty > stock[choice]) {
                    System.out.println("Error: Cannot add " + qty + " units. Only " + (stock[choice] - cart[choice]) + " available for cart.");
                } else {
                    cart[choice] += qty;
                    System.out.println("Added " + qty + " x " + names[choice] + " to cart.");
                }
            } else if (opt == 2) {
                displayCartContents(names, prices, cart);
                int choice = readInt(scanner, "Select product (1-4): ", 1, 4) - 1;
                if (cart[choice] == 0) {
                    System.out.println("Error: " + names[choice] + " is not in cart.");
                } else {
                    int qty = readInt(scanner, "Enter quantity to remove: ", 1, cart[choice]);
                    cart[choice] -= qty;
                    System.out.println("Removed " + qty + " x " + names[choice] + " from cart.");
                }
            } else if (opt == 3) {
                displayCartContents(names, prices, cart);
            } else if (opt == 4) {
                int subtotal = 0;
                for (int i = 0; i < 4; i++) {
                    subtotal += cart[i] * prices[i];
                }

                if (subtotal == 0) {
                    System.out.println("Error: Checkout rejected. Cart is empty!");
                    continue;
                }

                int subtotalCentavos = subtotal * 100;
                int discountCentavos = (subtotal >= 1000) ? (int) Math.round(subtotalCentavos * 0.10) : 0;
                int finalCentavos = subtotalCentavos - discountCentavos;
                double finalAmountPesos = finalCentavos / 100.0;

                System.out.println("\n=== CHECKOUT RECEIPT ===");
                displayCartContents(names, prices, cart);
                System.out.println("Subtotal:  PHP " + subtotal + ".00");
                System.out.printf("Discount:  PHP %.2f %s\n", (discountCentavos / 100.0), (subtotal >= 1000 ? "(10% Applied)" : "(No Discount)"));
                System.out.printf("Total Due: PHP %.2f\n", finalAmountPesos);

                int payment = readInt(scanner, "Enter cash payment (PHP whole amount): ", 0, 100000);
                if (payment * 100 < finalCentavos) {
                    System.out.printf("Error: Insufficient payment! Required PHP %.2f. Cart and stock unchanged.\n", finalAmountPesos);
                } else {
                    double change = payment - finalAmountPesos;
                    for (int i = 0; i < 4; i++) {
                        stock[i] -= cart[i];
                        cart[i] = 0;
                    }
                    System.out.printf("Payment Successful! Change: PHP %.2f\nThank you for shopping!\n", change);
                    break;
                }
            } else if (opt == 5) {
                for (int i = 0; i < 4; i++) cart[i] = 0;
                System.out.println("Cart cancelled. All items cleared. Stock unchanged.");
            }
        }
    }

    private static void displayStoreItems(String[] names, int[] prices, int[] stock) {
        System.out.println("\nAvailable Products:");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%d. %-10s | Price: PHP %-5d | Stock: %d\n", (i + 1), names[i], prices[i], stock[i]);
        }
    }

    private static void displayCartContents(String[] names, int[] prices, int[] cart) {
        System.out.println("\nCurrent Cart:");
        int total = 0;
        for (int i = 0; i < names.length; i++) {
            if (cart[i] > 0) {
                int itemTotal = cart[i] * prices[i];
                total += itemTotal;
                System.out.printf("%d. %-10s x %-3d = PHP %d\n", (i + 1), names[i], cart[i], itemTotal);
            }
        }
        System.out.println("Subtotal: PHP " + total);
    }

    public static void runProgram7(Scanner scanner) {
        System.out.println("\n--- PROGRAM 7: WAREHOUSE STOCK TRANSFERS ---");
        int[][] stock = {
            {20, 30, 40, 50},
            {15, 25, 35, 45},
            {10, 20, 30, 40}
        };
        int[][] history = new int[50][4];
        int historyCount = 0;

        while (true) {
            System.out.println("\n--- WAREHOUSE MANAGEMENT MENU ---");
            System.out.println("1. Perform Stock Transfer");
            System.out.println("2. Display Stock & Totals");
            System.out.println("3. View Audit History Trail");
            System.out.println("4. Exit to Main Menu");
            int opt = readInt(scanner, "Select option (1-4): ", 1, 4);

            if (opt == 4) break;

            if (opt == 1) {
                if (historyCount >= 50) {
                    System.out.println("Error: Transfer log history is full (50/50). Operation rejected.");
                    continue;
                }

                int src = readInt(scanner, "Enter Source Warehouse (1-3): ", 1, 3) - 1;
                int dst = readInt(scanner, "Enter Destination Warehouse (1-3): ", 1, 3) - 1;

                if (src == dst) {
                    System.out.println("Error: Source and Destination warehouses must be different! Transfer rejected.");
                    continue;
                }

                int prod = readInt(scanner, "Enter Product Number (1-4): ", 1, 4) - 1;
                int qty = readInt(scanner, "Enter Quantity to Transfer: ", 1, 1000);

                if (stock[src][prod] < qty) {
                    System.out.println("Error: Insufficient stock at Warehouse " + (src + 1) + ". Available: " + stock[src][prod]);
                } else if (stock[dst][prod] + qty > 1000) {
                    System.out.println("Error: Destination Warehouse " + (dst + 1) + " exceeds capacity of 1000 units.");
                } else {
                    stock[src][prod] -= qty;
                    stock[dst][prod] += qty;

                    history[historyCount][0] = src + 1;
                    history[historyCount][1] = dst + 1;
                    history[historyCount][2] = prod + 1;
                    history[historyCount][3] = qty;
                    historyCount++;

                    System.out.println("Transfer successful! " + qty + " units of Product " + (prod + 1) +
                                       " moved from W" + (src + 1) + " to W" + (dst + 1) + ".");
                }
            } else if (opt == 2) {
                displayWarehouseStock(stock);
            } else if (opt == 3) {
                System.out.println("\n=== TRANSFER AUDIT TRAIL ===");
                if (historyCount == 0) {
                    System.out.println("No transfers recorded yet.");
                } else {
                    System.printf("%-10s %-12s %-15s %-10s %-10s\n", "Log #", "Source W", "Destination W", "Product", "Quantity");
                    for (int i = 0; i < historyCount; i++) {
                        System.out.printf("%-10d W%-11d W%-14d Product %-3d %-10d\n",
                            (i + 1), history[i][0], history[i][1], history[i][2], history[i][3]);
                    }
                }
            }
        }
    }

    private static void displayWarehouseStock(int[][] stock) {
        System.out.println("\n=== WAREHOUSE STOCK MATRIX ===");
        System.out.printf("%-15s %-10s %-10s %-10s %-10s\n", "Warehouse", "Prod 1", "Prod 2", "Prod 3", "Prod 4");
        System.out.println("----------------------------------------------------------");
        int[] prodTotals = new int[4];
        for (int w = 0; w < 3; w++) {
            System.out.printf("Warehouse %-5d ", (w + 1));
            for (int p = 0; p < 4; p++) {
                System.out.printf("%-10d ", stock[w][p]);
                prodTotals[p] += stock[w][p];
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------");
        System.out.printf("%-15s ", "Product Totals");
        for (int p = 0; p < 4; p++) {
            System.out.printf("%-10d ", prodTotals[p]);
        }
        System.out.println();
    }

    public static void runProgram8(Scanner scanner) {
        System.out.println("\n--- PROGRAM 8: STUDENT PERFORMANCE ANALYZER ---");
        int n = readInt(scanner, "Enter number of students (2-10): ", 2, 10);
        int[][] marks = new int[n][4];
        double[] avg = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter 4 subject marks for Student " + (i + 1) + ":");
            int sum = 0;
            for (int s = 0; s < 4; s++) {
                marks[i][s] = readInt(scanner, "  Subject " + (s + 1) + " (0-100): ", 0, 100);
                sum += marks[i][s];
            }
            avg[i] = sum / 4.0;
        }

        double[] subjAvg = new double[4];
        for (int s = 0; s < 4; s++) {
            int sum = 0;
            for (int i = 0; i < n; i++) sum += marks[i][s];
            subjAvg[s] = sum / (double) n;
        }

        int[] order = new int[n];
        for (int i = 0; i < n; i++) order[i] = i;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (avg[order[j]] < avg[order[j + 1]]) {
                    int temp = order[j];
                    order[j] = order[j + 1];
                    order[j + 1] = temp;
                }
            }
        }

        int[] rank = new int[n];
        rank[order[0]] = 1;
        for (int i = 1; i < n; i++) {
            if (Math.abs(avg[order[i]] - avg[order[i - 1]]) < 0.001) {
                rank[order[i]] = rank[order[i - 1]];
            } else {
                rank[order[i]] = i + 1;
            }
        }

        System.out.println("\n=== STUDENT PERFORMANCE RESULTS ===");
        System.out.printf("%-10s %-8s %-8s %-8s %-8s %-10s %-15s %-6s\n", "Student", "Subj 1", "Subj 2", "Subj 3", "Subj 4", "Average", "Qualification", "Rank");
        System.out.println("-------------------------------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            boolean passAllSubj = true;
            for (int s = 0; s < 4; s++) {
                if (marks[i][s] < 60) passAllSubj = false;
            }
            boolean qualified = (avg[i] >= 75.0) && passAllSubj;
            String status = qualified ? "QUALIFIED" : "NOT QUALIFIED";

            System.out.printf("Student %-2d %-8d %-8d %-8d %-8d %-10.2f %-15s %-6d\n",
                (i + 1), marks[i][0], marks[i][1], marks[i][2], marks[i][3], avg[i], status, rank[i]);
        }

        System.out.println("\n=== SUBJECT AVERAGES & LOWEST TIED ===");
        double lowestSubjAvg = subjAvg[0];
        for (int s = 0; s < 4; s++) {
            System.out.printf("Subject %d Average: %.2f\n", (s + 1), subjAvg[s]);
            if (subjAvg[s] < lowestSubjAvg) lowestSubjAvg = subjAvg[s];
        }

        System.out.print("Subject(s) tied for lowest average (%.2f): " + lowestSubjAvg + " -> ");
        boolean first = true;
        for (int s = 0; s < 4; s++) {
            if (Math.abs(subjAvg[s] - lowestSubjAvg) < 0.001) {
                if (!first) System.out.print(", ");
                System.out.print("Subject " + (s + 1));
                first = false;
            }
        }
        System.out.println();
    }

    public static void runProgram9(Scanner scanner) {
        System.out.println("\n--- PROGRAM 9: CONSECUTIVE THEATER RESERVATIONS ---");
        int[][] seats = new int[5][8];
        int currentBookingId = 1;

        while (true) {
            System.out.println("\n--- THEATER RESERVATION MENU ---");
            System.out.println("1. Group Booking");
            System.out.println("2. Cancel Booking by ID");
            System.out.println("3. Display Seating Chart");
            System.out.println("4. Exit to Main Menu");
            int opt = readInt(scanner, "Select option (1-4): ", 1, 4);

            if (opt == 4) break;

            if (opt == 1) {
                int size = readInt(scanner, "Enter group size (1-8): ", 1, 8);
                boolean booked = false;

                for (int r = 0; r < 5; r++) {
                    int consecutive = 0;
                    int startCol = -1;
                    for (int c = 0; c < 8; c++) {
                        if (seats[r][c] == 0) {
                            if (consecutive == 0) startCol = c;
                            consecutive++;
                            if (consecutive == size) {
                                for (int k = startCol; k < startCol + size; k++) {
                                    seats[r][k] = currentBookingId;
                                }
                                System.out.println("Successfully booked " + size + " seat(s) in Row " + (r + 1) +
                                                   ", Cols " + (startCol + 1) + "-" + (startCol + size) +
                                                   " with Booking ID #" + currentBookingId);
                                currentBookingId++;
                                booked = true;
                                break;
                            }
                        } else {
                            consecutive = 0;
                        }
                    }
                    if (booked) break;
                }

                if (!booked) {
                    System.out.println("Booking Failed: No contiguous block of " + size + " available seats found in any single row.");
                }
                displayAvailableSeatCount(seats);
            } else if (opt == 2) {
                int idToCancel = readInt(scanner, "Enter Booking ID to cancel: ", 1, 1000);
                boolean found = false;

                for (int r = 0; r < 5; r++) {
                    for (int c = 0; c < 8; c++) {
                        if (seats[r][c] == idToCancel) {
                            seats[r][c] = 0;
                            found = true;
                        }
                    }
                }

                if (found) {
                    System.out.println("Successfully cancelled all seats associated with Booking ID #" + idToCancel);
                } else {
                    System.out.println("Error: Booking ID #" + idToCancel + " not found or already cancelled.");
                }
                displayAvailableSeatCount(seats);
            } else if (opt == 3) {
                displayTheaterGrid(seats);
                displayAvailableSeatCount(seats);
            }
        }
    }

    private static void displayTheaterGrid(int[][] seats) {
        System.out.println("\n=== THEATER SEATING GRID ===");
        System.out.print("       ");
        for (int c = 1; c <= 8; c++) System.out.printf("C%-3d ", c);
        System.out.println();
        for (int r = 0; r < 5; r++) {
            System.out.printf("Row %d  ", (r + 1));
            for (int c = 0; c < 8; c++) {
                if (seats[r][c] == 0) System.out.print("[ . ]");
                else System.out.printf("[%2d]", seats[r][c]);
            }
            System.out.println();
        }
    }

    private static void displayAvailableSeatCount(int[][] seats) {
        int avail = 0;
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 8; c++) {
                if (seats[r][c] == 0) avail++;
            }
        }
        System.out.println("Total Available Seats Remaining: " + avail + " / 40");
    }

    public static void runProgram10(Scanner scanner) {
        System.out.println("\n--- PROGRAM 10: WEEKLY INVENTORY AND LOST SALES ---");
        String[] pNames = {"Product 1", "Product 2", "Product 3"};
        int[] openingStock = {10, 15, 20};
        int[] currentStock = {10, 15, 20};
        int[] reorder = {5, 5, 5};
        int[] price = {100, 50, 25};

        int[][] received = new int[3][7];
        int[][] demand = new int[3][7];
        int[][] sold = new int[3][7];
        int[][] closing = new int[3][7];
        int[][] lostSales = new int[3][7];

        for (int day = 0; day < 7; day++) {
            System.out.println("\n--- DAY " + (day + 1) + " INPUTS ---");
            for (int p = 0; p < 3; p++) {
                System.out.println(pNames[p] + " (Current Stock: " + currentStock[p] + "):");
                received[p][day] = readInt(scanner, "  Enter Delivery Received (0-1000): ", 0, 1000);
                demand[p][day]   = readInt(scanner, "  Enter Customer Demand   (0-1000): ", 0, 1000);

                int available = currentStock[p] + received[p][day];
                sold[p][day] = Math.min(demand[p][day], available);
                lostSales[p][day] = demand[p][day] - sold[p][day];
                closing[p][day] = available - sold[p][day];
                currentStock[p] = closing[p][day];
            }
        }

        System.out.println("\n==================================================================================");
        System.out.println("                          WEEKLY INVENTORY SUMMARY REPORT                         ");
        System.out.println("==================================================================================");

        int[] prodTotalSold = new int[3];
        int[] prodTotalLost = new int[3];
        int[] prodTotalRevenue = new int[3];
        int[] prodTotalDeliveries = new int[3];

        for (int p = 0; p < 3; p++) {
            for (int d = 0; d < 7; d++) {
                prodTotalDeliveries[p] += received[p][d];
                prodTotalSold[p] += sold[p][d];
                prodTotalLost[p] += lostSales[p][d];
            }
            prodTotalRevenue[p] = prodTotalSold[p] * price[p];
        }

        System.out.printf("%-12s %-12s %-12s %-14s %-12s %-12s\n", "Product", "Total Sold", "Total Lost", "Total Revenue", "Final Stock", "Reorder Alert");
        System.out.println("----------------------------------------------------------------------------------");
        for (int p = 0; p < 3; p++) {
            String alert = (closing[p][6] <= reorder[p]) ? "LOW STOCK ALERT" : "OK";
            System.out.printf("%-12s %-12d %-12d PHP %-10d %-12d %-12s\n",
                pNames[p], prodTotalSold[p], prodTotalLost[p], prodTotalRevenue[p], closing[p][6], alert);
        }

        System.out.println("\n=== DAILY REVENUE BREAKDOWN ===");
        int[] dailyRevenue = new int[7];
        int highestDailyRevenue = 0;

        for (int d = 0; d < 7; d++) {
            for (int p = 0; p < 3; p++) {
                dailyRevenue[d] += sold[p][d] * price[p];
            }
            System.out.println("Day " + (d + 1) + " Total Revenue: PHP " + dailyRevenue[d]);
            if (dailyRevenue[d] > highestDailyRevenue) {
                highestDailyRevenue = dailyRevenue[d];
            }
        }

        System.out.print("\nDay(s) tied for highest revenue (PHP " + highestDailyRevenue + "): ");
        boolean first = true;
        for (int d = 0; d < 7; d++) {
            if (dailyRevenue[d] == highestDailyRevenue) {
                if (!first) System.out.print(", ");
                System.out.print("Day " + (d + 1));
                first = false;
            }
        }
        System.out.println();

        System.out.println("\n=== INVENTORY BALANCE AUDIT VERIFICATION ===");
        System.out.println("Formula: Opening Stock + Total Deliveries - Total Sales == Final Closing Stock");
        for (int p = 0; p < 3; p++) {
            int calculatedClosing = openingStock[p] + prodTotalDeliveries[p] - prodTotalSold[p];
            boolean verified = (calculatedClosing == closing[p][6]);
            System.out.printf("%-12s: %d + %d - %d = %d (Actual Closing: %d) -> Verification: %s\n",
                pNames[p], openingStock[p], prodTotalDeliveries[p], prodTotalSold[p], calculatedClosing, closing[p][6], (verified ? "PASSED" : "FAILED"));
        }
    }
}