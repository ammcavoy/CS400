import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class DataStructureADTTest<T extends DataStructureADT<String,String>> {
	
	private T dataStructureInstance;
	
	protected abstract T createInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = createInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = null;
	}

	
	@Test
	void test00_empty_ds_size() {
		if (dataStructureInstance.size() != 0)
		fail("data structure should be empty, with size=0, but size="+dataStructureInstance.size());
	}
	
	// TODO: implement tests 01 - 04

	void test01_after_insert_one_size_is_one() {
	  dataStructureInstance.insert("1", "testVal");
	  if(dataStructureInstance.size() != 1)
	    fail("data strucuture should have size=1, but size=" + dataStructureInstance.size());
	}
	
	void test02_after_insert_one_remove_one_size_is_0() {
	  dataStructureInstance.insert("1", "testVal");
	  dataStructureInstance.remove("1");
	  if(dataStructureInstance.size() != 0)
	    fail("data structure should be empty, with size=0, but size="+dataStructureInstance.size());
	}
	
	void test03_duplicate_exception_is_thrown() {
	  dataStructureInstance.insert("1", "testVal");
	  try {
	    dataStructureInstance.insert("1", "duplicateVal");
	  }
	  catch(RuntimeException e) {
	    if(!e.getMessage().equals("duplicate key"))
	      fail("data structure should have throw error with \"duplicate key\" message, but printed "
	          + e.getMessage());
	  }
	  catch(Exception e) {
	    fail("data structure should have thrown a RuntimeException, but threw a " 
	        + e.getClass().getSimpleName());
	  }
	  fail("data structure did not throw an exception at all when a duplicate key was inserted.");
	}
	
	void test04_remove_returns_false_when_key_not_present() {
	  
	}

	
	// TODO: add tests to ensure that you can detect implementation that fail
	
	// Tip: consider different numbers of inserts and removes and how different combinations of insert and removes


}
