package string_methods;

import static string_methods.StringMethods.*;

public class Examples {
    public static void main(String[] args) {
        String base = "aabbababbbaa";
        String search = "ab";

        System.out.println("StringMethod - examples:");

        System.out.println("    " + "Searching " + search + " in " + base);
        System.out.println("    " + "Count = " + count(base, search));
        System.out.println("    " + "Count from 2 = " + count(base, search, 2));
        System.out.println("    " + "Find first = " + findFirst(base, search));
        System.out.println("    " + "Find first from 2 = " + findFirst(base, search, 2));
        System.out.println("    " + "Find last = " + findLast(base, search));
        System.out.println("    " + "Find 2nd = " + findNTH(base, search, 2));
        System.out.println("    " + "Find 2nd from 3 = " + findNTH(base, search, 3, 2));
        System.out.println("    " + "Find 2nd from 7 = " + findNTH(base, search, 7, 2));
    }
}
