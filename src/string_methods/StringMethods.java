package string_methods;

public class StringMethods {

    public interface Counter<T> {
        T count(T arg, int index);
    }

    public static int[] prefixFunctionArray(String str) {
        int[] result = new int[str.length() + 1];
        result[0] = -1;

        int prefValue = -1;
        for (int i = 1; i < result.length; ++i) {
            while (prefValue >= 0 && str.charAt(i - 1) != str.charAt(prefValue)) {
                prefValue = result[prefValue];
            }
            result[i] = ++prefValue;
        }

        return result;
    }

    public static <T> T find(String base, String search, int begin, T start_arg, Counter<T> fun) {
        if (begin < 0) {
            throw new IndexOutOfBoundsException();
        }

        int[] search_pref = prefixFunctionArray(search);

        int prefValue = 0;
        for (int i = begin; i < base.length(); ++i) {
            while (prefValue >= 0 && (prefValue == search.length() || search.charAt(prefValue) != base.charAt(i))) {
                prefValue = search_pref[prefValue];
            }
            ++prefValue;
            if (prefValue == search.length() && begin <= i - prefValue + 1) {
                start_arg = fun.count(start_arg, i - prefValue + 1);
            }
        }

        return start_arg;
    }

    static int count(String base, String search, int begin) {
        return find(base, search, begin, 0, (Integer arg, int index) -> (++arg));
    }

    static int count(String base, String search) {
        return count(base, search, 0);
    }

    static int findNTH(String base, String search, int begin, int n) {
        int result = find(base, search, begin, -n,
                (Integer arg, int index) -> (arg < -1 ? (arg + 1) : (arg == -1 ? index : arg)));
        return (result < 0 ? -1 : result);
    }

    static int findNTH(String base, String search, int n) {
        return findNTH(base, search, 0, n);
    }

    static int findFirst(String base, String search, int begin) {
        return findNTH(base, search, begin, 1);
    }

    static int findFirst(String base, String search) {
        return findFirst(base, search, 0);
    }

    static int findLast(String base, String search, int begin) {
        return find(base, search, begin, -1, (Integer arg, int index) -> (index));
    }

    static int findLast(String base, String search) {
        return findLast(base, search, 0);
    }

    public static void examples() {
        String base = "aabbababbbaa";
        String search = "ab";

        System.out.println("StringMethod - examples:");

        System.out.println("Searching " + search + " in " + base);
        System.out.println("Count = " + count(base, search));
        System.out.println("Count from 2 = " + count(base, search, 2));
        System.out.println("Find first = " + findFirst(base, search));
        System.out.println("Find first from 2 = " + findFirst(base, search, 2));
        System.out.println("Find last = " + findLast(base, search));
        System.out.println("Find 2nd = " + findNTH(base, search, 2));
        System.out.println("Find 2nd from 3 = " + findNTH(base, search, 3, 2));
        System.out.println("Find 2nd from 7 = " + findNTH(base, search, 7, 2));
    }
}
