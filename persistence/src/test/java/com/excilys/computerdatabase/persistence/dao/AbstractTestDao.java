package com.excilys.computerdatabase.persistence.dao;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import javax.sql.DataSource;
import java.io.FileInputStream;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 17/02/16.
 */
public class AbstractTestDao {
    public void setUpDatabase(DataSource dataSource) {
        try {
            IDatabaseConnection dbc = new DatabaseConnection(dataSource.getConnection());
            IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/database/fakeDatabase.xml"));
            DatabaseOperation.CLEAN_INSERT.execute(dbc, dataSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
