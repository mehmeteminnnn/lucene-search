package com.example;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.store.Directory;

public class LuceneCsvSearcher {
    public static void main(String[] args) throws Exception {
        // Önceki dizini kullan
        Directory index = LuceneCsvIndexer.getIndex();

        // IndexSearcher oluştur
        IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(index));

        // Sorgu Çalıştırma
        search("Technology", searcher);  // 1 kelimelik sorgu
        search("Man AND Man", searcher); // 2 kelimelik sorgu
        search("Woman AND Man", searcher); // Kelimelerin yerini değiştirerek sorgu
    }

    private static void search(String queryStr, IndexSearcher searcher) throws Exception {
        QueryParser parser = new QueryParser("title", new StandardAnalyzer());
        Query query = parser.parse(queryStr);

        ScoreDoc[] hits = searcher.search(query, 10).scoreDocs;
        System.out.println("Sorgu: " + queryStr);
        for (ScoreDoc hit : hits) {
            Document doc = searcher.doc(hit.doc);
            System.out.println("Sonuç: " + doc.get("title") + " - " + doc.get("author"));
        }
    }
}
