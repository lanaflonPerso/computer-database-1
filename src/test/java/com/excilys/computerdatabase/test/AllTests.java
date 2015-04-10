/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.excilys.computerdatabase.test.fonctionnel.TestAddComputer;
import com.excilys.computerdatabase.test.fonctionnel.TestDashboard;
import com.excilys.computerdatabase.test.fonctionnel.TestDashboardFirefox;
import com.excilys.computerdatabase.test.fonctionnel.TestEditComputer;
import com.excilys.computerdatabase.test.unitaire.AllUnitTests;

@RunWith(Suite.class)
@SuiteClasses({ AllUnitTests.class,	TestDashboardFirefox.class, TestAddComputer.class, TestDashboard.class, TestEditComputer.class })
public class AllTests {

}
