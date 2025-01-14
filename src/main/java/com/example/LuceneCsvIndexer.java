package com.example;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.BufferedReader;
import java.io.FileReader;

public class LuceneCsvIndexer {
    public static Directory getIndex() throws Exception {
        // Lucene Dizin Deposu
        Directory index = new RAMDirectory();
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(index, config);

        // CSV Dosyasını Okuma
        String csvFile = "C:\\Users\\mhmtm\\Desktop\\erisim lucene\\lucene-search\\resources\\books_1.Best_Books_Ever.csv"; // CSV dosyanızın yolu
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String line;
        
        // Başlık satırını atla
        br.readLine();
        
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            if (fields.length >= 11) { // Gerekli alanların sayısı
                String title = fields[1]; // title
                String author = fields[2]; // author
                String publisher = fields[10]; // publisher
                addDocument(writer, title, author, publisher);
            }
        }
        br.close();

        writer.close();
        System.out.println("Dizin oluşturma tamamlandı!");
        return index;
    }

    private static void addDocument(IndexWriter writer, String title, String author, String publisher) throws Exception {
        Document doc = new Document();
        doc.add(new TextField("title", title, TextField.Store.YES));
        doc.add(new TextField("author", author, TextField.Store.YES));
        doc.add(new TextField("publisher", publisher, TextField.Store.YES));
        writer.addDocument(doc);
    }
}
