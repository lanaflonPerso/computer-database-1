package com.excilys.computerdatabase.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.excilys.computerdatabase.test.fonctionnel.addcomputer.TestAddComputer;
import com.excilys.computerdatabase.test.fonctionnel.dashboard.TestDashboard;
import com.excilys.computerdatabase.test.fonctionnel.dashboard.TestDashboardFirefox;
import com.excilys.computerdatabase.test.fonctionnel.editcomputer.TestEditComputerFirefox;

@RunWith(Suite.class)
@SuiteClasses({ TestAddComputer.class, TestDashboard.class,
		TestDashboardFirefox.class, TestEditComputerFirefox.class })
public class AllFonctionalTests {

}
