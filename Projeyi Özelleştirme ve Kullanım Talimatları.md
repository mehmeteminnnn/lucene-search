# Projeyi Özelleştirme ve Kullanım Talimatları

## **Projeyi Fork'layın ve Kendi Makinenize Çekin** 
Projeyi GitHub'dan fork'layarak kendi hesabınıza ekleyin:

1. CSV Dosyasına Göre Alanları Güncelleyin
CSV dosyanızın sütun yapısına uygun olarak aşağıdaki kod parçalarını güncellemeniz gerekiyor. Özellikle, hangi sütunun hangi veriyi temsil ettiğini belirtmeniz önemlidir.

Kodda değiştirilmesi gereken yerler:

String title = fields[1]; // title
String author = fields[2]; // author
String publisher = fields[10]; // publisher
addDocument(writer, title, author, publisher);

2. İndeksleme Kısmını Güncelleyin
İndeksleme sırasında, hangi alanların kaydedileceğini belirtmek için aşağıdaki kodu güncelleyin:

doc.add(new TextField("title", title, TextField.Store.YES));
doc.add(new TextField("author", author, TextField.Store.YES));
doc.add(new TextField("publisher", publisher, TextField.Store.YES));

3. Sorguları CSV'ye Göre Özelleştirin
Kendi verinize göre arama sorgularını düzenlemeniz gerekiyor. Örneğin:

Query query = new QueryParser("title", analyzer).parse("searchTerm");

4. CSV Dosyası Yolu
Projenin varsayılan olarak hangi CSV dosyasını kullanacağını belirlemek için kodda şu kısmı kontrol edin:


BufferedReader br = new BufferedReader(new FileReader("path/to/your/csvfile.csv"));
5.Bu ksıımdan kendi csvnize göre sorgu atıp sonuçlarına bakın
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

Kodu çalıştırmak için mvn -e exec:java komudunu kullanın

İyi Çalışmalarrr

