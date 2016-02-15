package com.excilys.computerdatabase.console.ui;

import java.util.List;
import java.util.Scanner;

/**
 * @author Vincent Galloy
 *         The Enum Paginator.
 */
public class Paginator {

    /**
     * The Constant SIZE.
     */
    private static final int SIZE = 10;

    /**
     * The Constant scanner.
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prints the.
     *
     * @param list the list
     */
    public static void print(List<? extends Object> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Object o = list.get(i);
                System.out.println(o.toString());
                if ((i + 1) % SIZE == 0) {
                    if (!waitEnter())
                        return;
                }
            }
        }
    }

    /**
     * Wait enter.
     *
     * @return true, if successful
     */
    private static boolean waitEnter() {
        System.out.println("--More--");
        String result = scanner.nextLine();
        return "".equals(result);
    }
}
