//
// Sample code to show how to use a simple database connection factory with the 
// GStorm ORM
// see: https://github.com/kdabir/gstorm
//
// ----------------------------------------------------------------------------
//
// NOTE: The below doesn't work, as the original repo is built with a different 
// version of Java.
//
// Because of this, I build the jar locally and add it to the classpath as 
// follows:
//
// groovy -cp gstorm-master-0.4-dev.jar GStormFactory.groovy
//
//
// @GrabResolver(name='gstorm', root='http://kdabir.github.io/mavenrepo/') 
// @Grab('gstorm:gstorm:0.3')

import groovy.sql.*
import gstorm.*

@GrabConfig(systemClassLoader = true) 
@Grab('org.hsqldb:hsqldb:2.2.9')

// Singleton class
// @Singleton
@Singleton(lazy = true)
class GStormFactory {

	private Sql sqlInstance = null
	private Gstorm g = null

    private GStormFactory() {
	  if (sqlInstance == null) {
	    // TODO: Read from a properties file
		sqlInstance = Sql.newInstance("jdbc:hsqldb:file:./db/testdb", "sa", "", 	  
		//sqlInstance = Sql.newInstance("jdbc:hsqldb:mem:database", "sa", "", 
		  "org.hsqldb.jdbcDriver")
		this.g = new Gstorm(sqlInstance)		
	  }
    }
	
    public Gstorm getGstorm() { 
        return this.g;
    }
	
	// Expose so that we can call GSql behavior not exposed by GStorm
    public Sql getGSql() { 
        return this.sqlInstance;
    }	
	
	// Remember to close the connection
	def closeConnection() {
	  if (sqlInstance != null) {
	    sqlInstance.close()
	    sqlInstance = null
	    g = null		
	  }
	}
}
