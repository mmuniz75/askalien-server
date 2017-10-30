package edu.muniz.askalien.util;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


public class LuceneHelperTests {

	@Test
	public void searchKrulians() {
		IndexingHelper indexing = LuceneHelper.getInstance();
		
		Map<Integer,Float> ids = new HashMap<>();
		ids = indexing.getIdsFromSearch("krulians");
		
		assertTrue(ids.size()>=55);
	}
	
	@Test
	public void searchNothing() {
		IndexingHelper indexing = LuceneHelper.getInstance();
		
		Map<Integer,Float> ids = new HashMap<>();
		ids = indexing.getIdsFromSearch("asdfasdfsd");
		
		assertTrue(ids.size()==0);
	}
	
	@Test
	public void testEnv(){
		assertNotEquals(System.getenv("LUCENE_INDEX_DIR"),"","LUCENE_INDEX_DIR not set");
		
	}

}
