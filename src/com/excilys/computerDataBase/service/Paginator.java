package com.excilys.computerDataBase.service;

import java.util.List;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Enum Paginator.
 */
public enum Paginator {

	/** The instance. */
	INSTANCE;

	/** The Constant SIZE. */
	private static final int SIZE = 10;

	/** The Constant scanner. */
	private static final Scanner scanner = new Scanner(System.in);

	/**
	 * Prints the.
	 *
	 * @param list
	 *            the list
	 */
	public void print(List<? extends Object> list) {
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
	private boolean waitEnter() {
		System.out.println("--More--");
		String result = scanner.nextLine();
		if ("".equals(result)) {
			return true;
		} else {
			return false;
		}
	}
}
