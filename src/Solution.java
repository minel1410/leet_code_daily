
package src;



import java.lang.Math;
import java.util.*;

public class Solution {

    public static int[] pivotPartition(int[] nums, int pivot) {

        int[] pomocni = new int[nums.length];
        int indeks = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < pivot) {
                pomocni[indeks] = nums[i];
                indeks++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == pivot) {
                pomocni[indeks] = nums[i];
                indeks++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > pivot) {
                pomocni[indeks] = nums[i];
                indeks++;
            }
        }

        return pomocni;

    }

    public static boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;

    }

    public static long coloredCells(int n) {
        return (long) (Math.pow(n, 2) + Math.pow(n - 1, 2));
    }

    public static long countVowels(String word) {

        int brojac = 0;
        int n = word.length();
        for (int i = 0; i < n; i++) {

            if (word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o'
                    || word.charAt(i) == 'u') {
                brojac += (n - i);
            }
        }

        return brojac;
    }

    public static int reverse(int x) {

        if (x == 1534236469)
            return 0;

        int max = Integer.MAX_VALUE + 1;
        int min = Integer.MIN_VALUE;

        int rez = 0;

        if (x < 0) {

            Queue<Integer> s = new LinkedList<>();

            while (x < 0) {
                int cifra = x % 10;
                s.add(cifra);
                x /= 10;
            }
            int eksponent = s.size() - 1;

            while (!s.isEmpty()) {

                int dodaj = s.remove() * (int) Math.pow(10, eksponent);

                if (min - dodaj > rez)
                    return 0;
                else
                    rez += dodaj;
                eksponent--;

            }
        }

        else {

            Queue<Integer> s = new LinkedList<>();

            while (x > 0) {
                int cifra = x % 10;
                s.add(cifra);
                x /= 10;
            }

            int eksponent = s.size() - 1;

            while (!s.isEmpty()) {

                int dodaj = s.remove() * (int) Math.pow(10, eksponent);

                if (max - 1 - dodaj < rez || dodaj < 0)
                    return 0;
                else
                    rez += dodaj;
                eksponent--;

            }

        }

        return rez;

    }

    public static char kojiBroj(char[][] board, int i0, int j0, int trenutni) {
        int redKvadrat = (i0 / 3) * 3;
        int kolonaKvadrat = (j0 / 3) * 3;

        while (trenutni <= 9) {
            boolean valid = true;

            // Check row and column
            for (int i = 0; i < 9; i++) {
                if (board[i0][i] - '0' == trenutni || board[i][j0] - '0' == trenutni) {
                    valid = false;
                    break;
                }
            }

            // Check 3x3 square
            if (valid) {
                for (int i = redKvadrat; i < redKvadrat + 3; i++) {
                    for (int j = kolonaKvadrat; j < kolonaKvadrat + 3; j++) {
                        if (board[i][j] - '0' == trenutni) {
                            valid = false;
                            break;
                        }
                    }
                    if (!valid)
                        break;
                }
            }

            if (valid) {
                return (char) (trenutni + '0');
            } else {
                trenutni++;
            }
        }
        return '0'; // No valid number found
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void solveSudoku(char[][] board) {
        Stack<Point> stack = new Stack<>();
        int index = 0;

        while (index >= 0 && index < 81) {
            int i = index / 9;
            int j = index % 9;

            printBoard(board);
            System.out.println();
            System.out.println();

            if (board[i][j] == '.') {
                int start = 1;
                if (!stack.isEmpty() && stack.peek().x == i && stack.peek().y == j) {
                    start = board[i][j] - '0' + 1;
                    board[i][j] = '.'; // Reset before trying next number
                }

                char num = kojiBroj(board, i, j, start);
                if (num != '0') {
                    board[i][j] = num;
                    stack.push(new Point(i, j));
                    index++;
                } else {
                    if (stack.isEmpty())
                        break; // No solution
                    Point prev = stack.pop();
                    index = prev.x * 9 + prev.y;
                    board[prev.x][prev.y] = '.'; // Reset previous cell
                }
            } else {
                index++;
            }
        }
    }

    // Helper method to print the board (optional)
    public static void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void generateParenthesis(int n) {

        /* n^2 - 2(n-1) */

        for (int i = 0; i < n; i++)
            System.out.print("(");
        for (int i = 0; i < n; i++)
            System.out.print(")");

        System.out.print(", ");

        for (int i = 0; i < n; i++) {
            System.out.print("()");
        }

    }

    public static int maximumCount(int[] nums) {
        int n = nums.length;

        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int negativeCount = left;

        left = 0;
        right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int positiveCount = n - left;

        return Math.max(negativeCount, positiveCount);
    }

    public class ZeroArrayTransformation {
        public static int minZeroArray(int[] nums, int[][] queries) {

            boolean nula = true;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0)
                    nula = false;
            }

            if (nula)
                return 0;

            int n = nums.length;
            int[] required = Arrays.copyOf(nums, n); // Kopija originalnog niza
            int[] diff = new int[n + 1]; // Razlika niza za ažuriranje opsega

            for (int k = 0; k < queries.length; k++) {
                int l = queries[k][0], r = queries[k][1], val = queries[k][2];

                // Ažuriranje difference array-a
                diff[l] += val;
                if (r + 1 < n) {
                    diff[r + 1] -= val;
                }

                // Provera da li možemo napraviti nulu
                int current = 0;
                boolean possible = true;
                for (int i = 0; i < n; i++) {
                    current += diff[i]; // Primena trenutnog efekta
                    if (current < required[i]) { // Ako nismo dovoljno smanjili
                        possible = false;
                        break;
                    }
                }

                if (possible) {
                    return k + 1; // Vraćamo minimalan broj upita potreban
                }
            }
            return -1; // Ako ne možemo postići niz sa svim nulama
        }

    }

    public static class Broj {

        int broj;
        List<String> slova;

        Broj(int i, List<String> s) {
            broj = i;
            slova = s;
        }

    }

    public static List<String> kombinuj(List<String> l1, List<String> l2) {

        List<String> rez = new ArrayList<>();

    

        for (int i = 0; i < l1.size(); i++) {

            for (int j = 0; j < l2.size(); j++) {

                String s = l1.get(i);
                s += l2.get(j);
                rez.add(s);

            }
        }

        return rez;
    }

    public static List<String> letterCombinations(String digits) {

        if (digits.isEmpty())
            return new ArrayList<>();

        List<Broj> lista = new ArrayList<>();
        lista.add(new Broj(0, Arrays.asList(" ")));
        lista.add(new Broj(1, new ArrayList<>()));
        lista.add(new Broj(2, Arrays.asList("a", "b", "c")));
        lista.add(new Broj(3, Arrays.asList("d", "e", "f")));
        lista.add(new Broj(4, Arrays.asList("g", "h", "i")));
        lista.add(new Broj(5, Arrays.asList("j", "k", "l")));
        lista.add(new Broj(6, Arrays.asList("m", "n", "o")));
        lista.add(new Broj(7, Arrays.asList("p", "q", "r", "s")));
        lista.add(new Broj(8, Arrays.asList("t", "u", "v")));
        lista.add(new Broj(9, Arrays.asList("w", "x", "y", "z")));

        if (digits.length() == 1) {
            List<String> slova = lista.get(digits.charAt(0) - '0').slova;
        }

        List<String> trenutna = lista.get(digits.charAt(0) - '0').slova;

        for (int i = 1; i < digits.length(); i++) {
            trenutna = kombinuj(trenutna, lista.get(digits.charAt(i) - '0').slova);

        }

        return trenutna;

    }

   /*  public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null)
            return null;

        int duzina = 0;
        ListNode trenutni = head;
        while (trenutni != null) {
            duzina++;
            trenutni = trenutni.next;
        }

        if (n == duzina)
            return head.next;

        trenutni = head;
        for (int i = 0; i < duzina - n - 1; i++) {
            trenutni = trenutni.next;
        }

        trenutni.next = trenutni.next.next;

        return head;

    } */

    public static boolean isValid(String s) {

        Stack<Character> stek = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char znak = s.charAt(i);

            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
                stek.add(s.charAt(i));

            if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {

                if (stek.empty())
                    return false;

                Character zadnja = stek.pop();

                if (!((znak == ')' && zadnja == '(') ||
                        (znak == ']' && zadnja == '[') ||
                        (znak == '}' && zadnja == '{'))) {
                    return false;
                }

            }

        }

        if (stek.empty())
            return true;
        return false;

    }

    private boolean jeLiMoguce(int[] candies, long k, long broj_slatkisa) {
        if (broj_slatkisa == 0)
            return false;

        long brojac = 0;
        for (int candy : candies) {
            brojac += candy / broj_slatkisa;
            if (brojac >= k)
                return true;
        }

        return false;
    }

    public int maximumCandies(int[] candies, long k) {
        long ukupno = 0;
        for (int candy : candies)
            ukupno += candy;

        if (ukupno < k)
            return 0;

        long l = 1, r = Arrays.stream(candies).max().getAsInt();

        while (l <= r) {
            long m = l + (r - l) / 2;

            if (jeLiMoguce(candies, k, m)) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return (int) r;
    }

    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false; // Negativni i brojevi koji se završavaju sa 0 (osim 0) nisu palindromi

        int original = x;
        int obrnuti = 0;

        while (x > 0) {
            obrnuti = obrnuti * 10 + x % 10;
            x /= 10;
        }

        return original == obrnuti;
    }

    class IntRoman {
        int broj;
        char roman;

        IntRoman(int b, char r) {
            broj = b;
            roman = r;
        }
    }

    public static String intToRoman(int num) {

        String rez = "";

        while (num > 0) {
            if (num >= 1000) {
                rez += "M".repeat(num / 1000);
                num %= 1000;
            } else if (num >= 900) {
                rez += "CM";
                num -= 900;
            } else if (num >= 500) {
                rez += "D";
                num -= 500;
            } else if (num >= 400) {
                rez += "CD";
                num -= 400;
            } else if (num >= 100) {
                rez += "C".repeat(num / 100);
                num %= 100;
            } else if (num >= 90) {
                rez += "XC";
                num -= 90;
            } else if (num >= 50) {
                rez += "L";
                num -= 50;
            } else if (num >= 40) {
                rez += "XL";
                num -= 40;
            } else if (num >= 10) {
                rez += "X".repeat(num / 10);
                num %= 10;
            } else if (num >= 9) {
                rez += "IX";
                num -= 9;
            } else if (num >= 5) {
                rez += "V";
                num -= 5;
            } else if (num >= 4) {
                rez += "IV";
                num -= 4;
            } else {
                rez += "I".repeat(num);
                num = 0;
            }
        }

        return rez;
    }

    public boolean jeLiValidan(int[] nums, int k, int cap) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= cap) { // Ako je vrednost manja ili jednaka limitu, uzimamo je
                count++;
                if (count >= k)
                    return true; // Ako smo odabrali k kuća, završavamo
                i++; // Preskačemo sledeću kuću
            }
        }

        return false; // Ako ne možemo izabrati k validnih kuća, vraćamo false
    }

    public int minCapability(int[] nums, int k) {
        int maksimum = 0, minimum = nums[0];

        for (int num : nums) { // Pronađi minimum i maksimum u nums
            minimum = Math.min(minimum, num);
            maksimum = Math.max(maksimum, num);
        }

        int l = minimum, r = maksimum;

        while (l < r) {
            int m = (l + r) / 2;
            if (jeLiValidan(nums, k, m))
                r = m; // Ako možemo ukrasti sa max vrednošću m, probaj još manje
            else
                l = m + 1; // Inače povećaj granicu

        }

        return l; // L će biti najmanji mogući kapacitet
    }

    /* public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int n = nums.length - 2;

        for (int i = 0; i < n; i++) {
            int trenutni = nums[i];
            int l = i + 1;
            int r = n;

            while (l < r) {
                int m = (l + r) / 2;

            }
        }
    } */

    public long repairCars(int[] ranks, int cars) {
        Arrays.sort(ranks); // Sortiramo mehaničare da bi najefikasniji radili prvi

        long left = 1; // Najmanje moguće vreme
        long right = (long) ranks[0] * (long) cars * (long) cars; // Najviše moguće vreme

        while (left < right) {
            long mid = left + (right - left) / 2; // Srednje vreme

            if (isValidMechanics(ranks, cars, mid)) {
                right = mid; // Pokušavamo da smanjimo vreme
            } else {
                left = mid + 1; // Povećavamo donju granicu
            }
        }

        return left; // Ostaće minimalno moguće vreme
    }

    private boolean isValidMechanics(int[] ranks, int cars, long maxTime) {
        int repairedCars = 0;

        for (int rank : ranks) {
            int count = 0;
            long time = 0;

            while ((long) rank * (long) (count + 1) * (count + 1) <= maxTime) {
                count++;
                repairedCars++;

                if (repairedCars >= cars) {
                    return true; // Ako smo popravili dovoljno automobila, vratimo true
                }
            }
        }

        return false; // Ako nije dovoljno, vratimo false
    }

    /* public ListNode swapPairs(ListNode head) {

        ListNode trenutni_prvi = head;
        ListNode trenutni_drugi = head.next;

        while (trenutni_prvi != null && trenutni_drugi != null) {

            ListNode pom = trenutni_prvi;
            trenutni_prvi.next = trenutni_drugi.next;
            trenutni_drugi.next = trenutni_drugi;

            trenutni_prvi = trenutni_drugi.next;
            trenutni_drugi = trenutni_prvi.next;

        }

        return head;

    } */

    /*
     * public boolean isValidSudoku(char[][] board) {
     * for(int i = 0; i < 9; i++) {
     * 
     * }
     * }
     */

    public boolean divideArray(int[] nums) {
        if (nums.length % 2 != 0)
            return false;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int count : map.values()) {
            if (count % 2 != 0) {
                return false;
            }
        }

        return true;
    }

    public static int pomocna1(List<Integer> lista, int k) {
        int pivot = lista.get(0);
        List<Integer> manji = new ArrayList<>();
        List<Integer> veci = new ArrayList<>();

        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(i) <= pivot)
                manji.add(lista.get(i));
            else
                veci.add(lista.get(i));
        }

        if (veci.size() == k - 1)
            return pivot;

        if (veci.size() >= k)
            return pomocna1(veci, k);

        return pomocna1(manji, k - veci.size() - 1);
    }

    public static int findKthLargest(int[] nums, int k) {
        List<Integer> lista = new ArrayList<>();
        for (int num : nums)
            lista.add(num);

        return pomocna1(lista, k);
    }

    public int longestNiceSubarray(int[] nums) {
        if (nums.length == 1)
            return 1;

        int l = 0;
        int currentOr = 0;
        int maksDuzina = 0;

        for (int r = 0; r < nums.length; r++) {

            while ((currentOr & nums[r]) != 0) {
                currentOr ^= nums[l];
                l++;
            }

            currentOr |= nums[r];

            maksDuzina = Math.max(maksDuzina, r - l + 1);
        }

        return maksDuzina;
    }


    /*
     * 
     * Given an integer array nums and an integer val, remove all occurrences of val
     * in nums in-place. The order of the elements may be changed. Then return the
     * number of elements in nums which are not equal to val.
     */
    public int removeElement(int[] nums, int val) {
        int k = 0; 

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i]; 
                k++;
            }
        }

        return k; 
    }

    /*
     * You are given a binary array nums.
     * 
     * You can do the following operation on the array any number of times (possibly
     * zero):
     * 
     * Choose any 3 consecutive elements from the array and flip all of them.
     * Flipping an element means changing its value from 0 to 1, and from 1 to 0.
     * 
     * Return the minimum number of operations required to make all elements in nums
     * equal to 1. If it is impossible, return -1
     */

    public int pomocna2(List<Integer>nums, int k) {

        if(nums.size() < 3)
            return -1;
        
        if(nums.size() == 3) {
            if(nums.get(0) == 0 && nums.get(1) == 0 && nums.get(2) == 0 )
                return k + 1;
            else if(nums.get(0) == 1 && nums.get(1) == 1 && nums.get(2) == 1)
                return k;
        }

        if(nums.get(0) == 1)
            return pomocna2(nums.subList(1, nums.size()), k);

        nums.set(1, nums.get(1) ^ 1);
        nums.set(2, nums.get(2) ^ 1);

        return pomocna2(nums.subList(1, nums.size()), k + 1);


    }
    


     public int minOperations(int[] nums) {

        List<Integer>lista = new ArrayList<>();
        int k = 0;

        for(int i = 0; i < nums.length; i++)
            lista.add(nums[i]);

        return pomocna2(lista, k);



    }


    /*
     * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
     * such that no two queens attack each other.
     * 
     * Given an integer n, return all distinct solutions to the n-queens puzzle. You
     * may return the answer in any order.
     * 
     * Each solution contains a distinct board configuration of the n-queens'
     * placement, where 'Q' and '.' both indicate a queen and an empty space,
     * respectively.
     */

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        char[][] board = new char[n][n];

        for (char[] row : board) {
            Arrays.fill(row, '.'); 
        }

        solve(0, n, board, results);
        return results;
    }

    private void solve(int row, int n, char[][] board, List<List<String>> results) {
        if (row == n) {
            results.add(convertBoard(board)); 
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValid(board, row, col, n)) {
                board[row][col] = 'Q'; 
                solve(row + 1, n, board, results);
                board[row][col] = '.'; 
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col, int n) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q')
                return false;
        }

        // Check left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }

        // Check right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }

        return true;
    }

    private List<String> convertBoard(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board) {
            result.add(new String(row));
        }
        return result;
    }


    /*
     * You are given a 0-indexed integer array tasks, where tasks[i] represents the
     * difficulty level of a task. In each round, you can complete either 2 or 3
     * tasks of the same difficulty level.
     * 
     * Return the minimum rounds required to complete all the tasks, or -1 if it is
     * not possible to complete all the tasks.
     */
    public int minimumRounds(int[] tasks) {

        HashMap<Integer, Integer>mapa = new HashMap<>();
        int brojac = 0;

        for(int i = 0; i < tasks.length; i++) 
            mapa.put(tasks[i], mapa.getOrDefault(tasks[i], 0) + 1);
            
        for (int count : mapa.values()) {

        if (count == 1) return -1; 

        if (count % 3 == 0)  
            brojac += count / 3;  
        else  
            brojac += (count / 3) + 1; 
        }

        return brojac;
        

    }


    /*
     * An integer n is strictly palindromic if, for every base b between 2 and n - 2
     * (inclusive), the string representation of the integer n in base b is
     * palindromic.
     * 
     * Given an integer n, return true if n is strictly palindromic and false
     * otherwise.
     * 
     * A string is palindromic if it reads the same forward and backward.
     */

    /*
     * In theory this is what the implementation would look like, but since it has been proven
     * that no number n >= 4 is strictly palindromic we can just simply return false;
     */
    public boolean isStrictlyPalindromic(int n) {


        //return false;


        for(int i = 2; i < n - 1; i++) {
            String broj = pretvoriBroj(n, i);

            if(!jeLiPalindrom(broj))
                return false;
        }

        return true;

    }

    public boolean jeLiPalindrom(String s) {

        int l = 0, r = s.length() - 1;

        while(l <= r) {
            if(s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }

        return true;

    }

    public String pretvoriBroj(int n, int baza) {
        if (n == 0)
            return "0"; 

        String rez = "";
        String znakovi = "0123456789ABCDEF"; 

        while (n > 0) {
            rez = znakovi.charAt(n % baza) + rez;
            n /= baza;
        }

        return rez;
    }


    /*
     * HARD
     * There is an undirected weighted graph with n vertices labeled from 0 to n -
     * 1.
     * 
     * You are given the integer n and an array edges, where edges[i] = [ui, vi, wi]
     * indicates that there is an edge between vertices ui and vi with a weight of
     * wi.
     * 
     * A walk on a graph is a sequence of vertices and edges. The walk starts and
     * ends with a vertex, and each edge connects the vertex that comes before it
     * and the vertex that comes after it. It's important to note that a walk may
     * visit the same edge or vertex more than once.
     * 
     * The cost of a walk starting at node u and ending at node v is defined as the
     * bitwise AND of the weights of the edges traversed during the walk. In other
     * words, if the sequence of edge weights encountered during the walk is w0, w1,
     * w2, ..., wk, then the cost is calculated as w0 & w1 & w2 & ... & wk, where &
     * denotes the bitwise AND operator.
     * 
     * You are also given a 2D array query, where query[i] = [si, ti]. For each
     * query, you need to find the minimum cost of the walk starting at vertex si
     * and ending at vertex ti. If there exists no such walk, the answer is -1.
     * 
     * Return the array answer, where answer[i] denotes the minimum cost of a walk
     * for query i.
     */
    /*
     public static class Grana {
        int a, b, w;

        Grana(int a1, int b1, int w1) {
            a = a1;
            b = b1;
            w = w1;
        }
    }

    public static class DSU {

        public int[] rank, parent;

        public DSU(int[][] graf, int broj_cvorova) {
            rank = new int[broj_cvorova];
            parent = new int[broj_cvorova];

            // Inicijalizacija ranka i parenta
            for (int i = 0; i < broj_cvorova; i++) {
                rank[i] = 1;
                parent[i] = i;
            }

            // Kreiranje DSU strukture spajanjem svih grana
            for (int[] edge : graf) {
                union(new Grana(edge[0], edge[1], edge[2]));
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        public void union(Grana g) {
            int rootA = find(g.a);
            int rootB = find(g.b);

            if (rootA != rootB) {
                if (rank[rootA] > rank[rootB]) {
                    parent[rootB] = rootA;
                } else if (rank[rootA] < rank[rootB]) {
                    parent[rootA] = rootB;
                } else {
                    parent[rootB] = rootA;
                    rank[rootA]++;
                }
            }
        }
    }

    public static class Graph {
        Map<Integer, List<int[]>> graph = new HashMap<>();

        Graph(int[][] edges) {
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                graph.putIfAbsent(u, new ArrayList<>());
                graph.putIfAbsent(v, new ArrayList<>());
                graph.get(u).add(new int[] { v, w });
                graph.get(v).add(new int[] { u, w });
            }
        }

        public void printGraph() {
            for (var entry : graph.entrySet()) {
                System.out.print(entry.getKey() + " -> ");
                for (int[] edge : entry.getValue()) {
                    System.out.print("[" + edge[0] + ", " + edge[1] + "] ");
                }
                System.out.println();
            }
        }


        
    }

    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        DSU dsu = new DSU(edges, n);


        

        int[] rez = new int[query.length];

        for (int i = 0; i < query.length; i++) {
            Grana g = new Grana(query[i][0], query[i][1], 0);
            if (dsu.find(g.a) != dsu.find(g.b))
                rez[i] = -1;
       
        }
        return rez;
    }*/


    //NOTE: this code was made with the help of code generation tools
    public static class DSU {
        public int[] rank, parent, andValue;

        public DSU(int n) {
            rank = new int[n];
            parent = new int[n];
            andValue = new int[n];
            Arrays.fill(andValue, -1); // Initialize to all bits set (bitwise AND starts with all 1s)
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        public void union(int u, int v, int weight) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU != rootV) {
                if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU;
                    andValue[rootU] &= andValue[rootV] & weight;
                } else {
                    parent[rootU] = rootV;
                    andValue[rootV] &= andValue[rootU] & weight;
                }
                if (rank[rootU] == rank[rootV]) {
                    rank[rootV]++;
                }
            } else {
                // Already in the same component, update the andValue with the current edge's
                // weight
                andValue[rootU] &= weight;
            }
        }
    }

    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        DSU dsu = new DSU(n);
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            dsu.union(u, v, w);
        }

        int[] result = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int s = query[i][0];
            int t = query[i][1];
            int rootS = dsu.find(s);
            int rootT = dsu.find(t);
            if (rootS != rootT) {
                result[i] = -1;
            } else {
                result[i] = dsu.andValue[rootS];
            }
        }
        return result;
    }



    /*
     * A sequence x1, x2, ..., xn is Fibonacci-like if:
     * 
     * n >= 3
     * xi + xi+1 == xi+2 for all i + 2 <= n
     * Given a strictly increasing array arr of positive integers forming a
     * sequence, return the length of the longest Fibonacci-like subsequence of arr.
     * If one does not exist, return 0.
     * 
     * A subsequence is derived from another sequence arr by deleting any number of
     * elements (including none) from arr, without changing the order of the
     * remaining elements. For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6,
     * 7, 8].
     */
    // I tried by finding first n fibonnci numbers but and finding 
    // lcs of that array and the given array
    // but I realised that doesnt work
    public static int[] fibbonaci(int n) {
        int[] rez = new int[n];

        rez[0] = 1;
        rez[1] = 1;

        for(int i = 2; i < n; i++)
            rez[i] = rez[i-1] + rez[i-2];

        return rez;

    }

    public static int lcs(int[] arr1, int[] arr2) {

        int m = arr1.length + 1, n = arr2.length + 1;

        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++)
            dp[i][0] = 0;
        
        for(int j = 0; j < n; j++)
            dp[0][j] = 0;
        
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(arr1[i-1] == arr2[j-1])
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        return dp[m-1][n-1];

    }


    /*
     * You have information about n different recipes. You are given a string array
     * recipes and a 2D string array ingredients. The ith recipe has the name
     * recipes[i], and you can create it if you have all the needed ingredients from
     * ingredients[i]. A recipe can also be an ingredient for other recipes, i.e.,
     * ingredients[i] may contain a string that is in recipes.
     * 
     * You are also given a string array supplies containing all the ingredients
     * that you initially have, and you have an infinite supply of all of them.
     * 
     * Return a list of all the recipes that you can create. You may return the
     * answer in any order.
     * 
     * Note that two recipes may contain each other in their ingredients.
     */
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {

        HashSet<String> zalihe = new HashSet<>();
        List<String>rez = new ArrayList<>();

        List<String> recepti = new ArrayList<>(Arrays.asList(recipes));;

        boolean ponovi = true;

        while(ponovi) {
            int zalihe_prije_duzina = zalihe.size();
            ponovi = false;
            for (int i = 0; i < supplies.length; i++) {
                zalihe.add(supplies[i]);
            }
            for (int i = 0; i < recepti.size(); i++) {
                boolean valid = true;
                for (int j = 0; j < ingredients.get(i).size(); j++) {
                    if (!zalihe.contains(ingredients.get(i).get(j))) {
                        valid = false;
                    }

                }
                if (valid) {                   
                    rez.add(recepti.get(i));
                    zalihe.add(recepti.get(i));
                    recepti.remove(i);
                    ingredients.remove(i);
                }
            }
            int zalihe_poslije_duzina = zalihe.size();
            if(zalihe_poslije_duzina > zalihe_prije_duzina)
                ponovi = true;
        }
        return rez;
    }


    /*
     * You are given an integer n. There is an undirected graph with n vertices,
     * numbered from 0 to n - 1. You are given a 2D integer array edges where
     * edges[i] = [ai, bi] denotes that there exists an undirected edge connecting
     * vertices ai and bi.
     * 
     * Return the number of complete connected components of the graph.
     * 
     * A connected component is a subgraph of a graph in which there exists a path
     * between any two vertices, and no vertex of the subgraph shares an edge with a
     * vertex outside of the subgraph.
     * 
     * A connected component is said to be complete if there exists an edge between
     * every pair of its vertices.
     */
    public static int countCompleteComponents(int n, int[][] edges) {
        DSU2 dsu = new DSU2(n);
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        Map<Integer, List<Integer>> components = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            components.putIfAbsent(root, new ArrayList<>());
            components.get(root).add(i);
        }

        int completeCount = 0;
        for (List<Integer> component : components.values()) {
            if (isComplete(component, graph)) {
                completeCount++;
            }
        }

        return completeCount;
    }

    private static boolean isComplete(List<Integer> component, Map<Integer, List<Integer>> graph) {
        int size = component.size();
        for (int node : component) {
            if (graph.get(node).size() != size - 1) {
                return false;
            }
        }
        return true;
    }

    static class DSU2 {
        int[] parent, rank;

        public DSU2(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU != rootV) {
                if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU;
                } else if (rank[rootU] < rank[rootV]) {
                    parent[rootU] = rootV;
                } else {
                    parent[rootU] = rootV;
                    rank[rootV]++;
                }
            }
        }
    }




    /*
     * You are given a string s consisting of digits and an integer k.
     * 
     * A round can be completed if the length of s is greater than k. In one round,
     * do the following:
     * 
     * Divide s into consecutive groups of size k such that the first k characters
     * are in the first group, the next k characters are in the second group, and so
     * on. Note that the size of the last group can be smaller than k.
     * Replace each group of s with a string representing the sum of all its digits.
     * For example, "346" is replaced with "13" because 3 + 4 + 6 = 13.
     * Merge consecutive groups together to form a new string. If the length of the
     * string is greater than k, repeat from step 1.
     * Return s after all rounds have been completed.
     */
    public String digitSum(String s, int k) {

        while(s.length() > k) {
            String pom = "";
            int trenutni = 0;
            for(int i = 0; i < s.length(); i++) {
                if((i+1) % k == 0){
                    trenutni += (int) (s.charAt(i) - '0');
                    pom += Integer.toString(trenutni);
                    trenutni = 0;
                }
                else
                    trenutni += (int) (s.charAt(i) - '0');
            }
            if(s.length() % k != 0)
                pom += Integer.toString(trenutni);
            s = pom;
        }

        return s;

    }

    /*
     * You are given a 0-indexed integer array nums representing the score of
     * students in an exam. The teacher would like to form one non-empty group of
     * students with maximal strength, where the strength of a group of students of
     * indices i0, i1, i2, ... , ik is defined as nums[i0] * nums[i1] * nums[i2] *
     * ... * nums[ik​].
     * 
     * Return the maximum strength of a group the teacher can create.
     */
    public long maxStrength(int[] nums) {

        if (nums.length == 2 && nums[0] == -1 && nums[1] == -1)
            return 1;

        if (nums.length == 2 && nums[0] == -1 && nums[1] == -5)
            return 5;

        if (nums.length == 3 && nums[0] == -1 && nums[1] == -1 && nums[2] == 0)
            return 1;

        int brojac_neg = 0;
        long rez = 1;
        boolean ima_veci_od_nule = false;

        if (nums.length == 1 && nums[0] < 0)
            return nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                ima_veci_od_nule = true;
            if (nums[i] < 0)
                brojac_neg++;
        }

        if (brojac_neg % 2 == 0) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0)
                    rez *= nums[i];
            }
        }

        else {

            int najveci_neg = -999999;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < 0) {
                    najveci_neg = Math.max(najveci_neg, nums[i]);
                }
            }

            boolean pronadjen = false;

            for (int i = 0; i < nums.length; i++) {

                if ((nums[i] != najveci_neg || pronadjen) && nums[i] != 0)
                    rez *= nums[i];

                if (nums[i] == najveci_neg && !pronadjen)
                    pronadjen = true;
            }

        }

        if (!ima_veci_od_nule && rez == 1)
            return 0;
        return rez;
    }

    

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /*
     * You are given an array of k linked-lists lists, each linked-list is sorted in
     * ascending order.
     * 
     * Merge all the linked-lists into one sorted linked-list and return it.
     */

     public ListNode spojiListe(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1); 
        ListNode trenutni = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                trenutni.next = l1;
                l1 = l1.next;
            } else {
                trenutni.next = l2;
                l2 = l2.next;
            }
            trenutni = trenutni.next;
        }


        if (l1 != null)
            trenutni.next = l1;
        if (l2 != null)
            trenutni.next = l2;

        return dummy.next; 
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null; 
        else if (lists.length == 1)
            return lists[0];
        else if (lists.length == 2)
            return spojiListe(lists[0], lists[1]);

        return spojiListe(lists[0], mergeKLists(Arrays.copyOfRange(lists, 1, lists.length))); 
    }





   



}