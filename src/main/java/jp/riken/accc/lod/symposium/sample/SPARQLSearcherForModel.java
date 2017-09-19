package jp.riken.accc.lod.symposium.sample;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class SPARQLSearcherForModel {

	public static void main(String[] args) throws Exception{

		// デフォルトモデルの作成
		Model model = ModelFactory.createDefaultModel();
		
		// RDFファイルを読み込む
		model.read("/temp/RDFSample1.ttl");
		
		// 読み込んだデータを念のため表示する
		model.write(System.out, "N-TRIPLES");
		System.out.println("------------------------------");

		// SPARQLクエリ　（文字列) を作成する
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ?hp ?label\n");
		sb.append("WHERE {\n");
		sb.append("  ?s <http://xmlns.com/foaf/0.1/homepage> ?hp.\n");
		sb.append("  ?s <http://www.w3.org/2000/01/rdf-schema#label> ?label.\n");
		sb.append("}");
		String queryString = sb.toString();
		System.out.println(queryString);
		System.out.println("------------------------------");

		// モデルに対してクエリーを発行する
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, model);

		// 結果データセットを取得する
		ResultSet results = qe.execSelect();
		// 結果データセットを表形式で表示する
		ResultSetFormatter.out(System.out, results, query);
		// クエリを閉じる
		qe.close();
		
		// モデルを閉じる
		model.close();
	}
	
}
