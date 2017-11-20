package edu.muniz.askalien.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class LuceneHelper implements IndexingHelper {

	private static int MAX_RESULTS=100;
	private static String ID = "ID";
	private static String[] FIELDS = new String[]{"SUBJECT","CONTENT"};

	private static IndexingHelper instance;

	private LuceneHelper() {
		this.indexingPath = System.getenv("LUCENE_INDEX_DIR");
		if(this.indexingPath==null || this.indexingPath.equals("."))
			this.indexingPath = this.getClass().getResource("/lucene").getPath();	
	};

	private String indexingPath;

		
	
	@Override
	public Map<Integer,Float> getIdsFromSearch(String keywords) {

		Map<Integer,Float> ids = new HashMap<Integer,Float>();
		IndexReader reader = null;
		IndexSearcher searcher = null;
		try {
			reader = IndexReader.open(FSDirectory.open(new File(indexingPath)));
			searcher = new IndexSearcher(reader);

			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
			MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_35, FIELDS,analyzer);
			
			keywords = keywords.replaceAll("\\?", " ");
			Query query = parser.parse(keywords);
			
			TopDocs results = searcher.search(query, null, MAX_RESULTS);
			ScoreDoc[] hits = results.scoreDocs;
			for (ScoreDoc hit : hits) {
			    Document doc = searcher.doc(hit.doc);
			    Float score = hit.score;
			    Integer id = Integer.valueOf(doc.get(ID)); 
			    ids.put(id, score);
			}   
	

		} catch (Exception ex) {
			System.out.println("Not possible to search with keyword=" + keywords + "\n with message: " + ex.getMessage());
		} finally {
			try {
				searcher.close();
				reader.close();
			} catch (Exception ex) {
			}
		}

		return ids;
	}

	public static IndexingHelper getInstance() {
		if (instance == null)
			instance = new LuceneHelper();
		return instance;
	}

}
