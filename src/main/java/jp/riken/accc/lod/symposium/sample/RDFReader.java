package jp.riken.accc.lod.symposium.sample;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class RDFReader {

	public static void main(String[] args) throws Exception{

		// デフォルトモデルの作成
		Model model = ModelFactory.createDefaultModel();
		
		// RDFファイルを読み込む
		model.read("/temp/RDFSample1.ttl");

		// N-TRIPLES形式でmodel内のデータを標準出力に表示
		model.write(System.out, "N-TRIPLES"); 

		// モデルを閉じる
		model.close();
	}
}
