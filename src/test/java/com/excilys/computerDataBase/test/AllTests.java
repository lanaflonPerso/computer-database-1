/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.excilys.computerDataBase.test.fonctionnel.TestAddComputer;
import com.excilys.computerDataBase.test.fonctionnel.TestDashboard;
import com.excilys.computerDataBase.test.fonctionnel.TestEditComputer;
import com.excilys.computerDataBase.test.unitaire.AllUnitTests;

@RunWith(Suite.class)
@SuiteClasses({ AllUnitTests.class,	TestAddComputer.class, TestDashboard.class, TestEditComputer.class })
public class AllTests {

}
