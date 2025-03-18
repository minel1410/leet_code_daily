

import java.lang.Math;

import java.util.HashMap;

import java.util.Stack;

import java.util.Set;
import java.util.HashSet;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

    public static int pomocna(List<Integer> lista, int k) {
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
            return pomocna(veci, k);

        return pomocna(manji, k - veci.size() - 1);
    }

    public static int findKthLargest(int[] nums, int k) {
        List<Integer> lista = new ArrayList<>();
        for (int num : nums)
            lista.add(num);

        return pomocna(lista, k);
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
}
