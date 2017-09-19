package jp.riken.accc.lod.symposium.sample;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;

public class SPARQLSearcherForEndpoint {

	public static void main(String[] args) throws Exception{

		// アクセスするSPARQL EndpointのURLを指定する
		String endpointURI = "http://metadb.riken.jp/sparql";

		// SPARQL クエリ　（文字列）を作成する
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT (count(*) AS ?numTriple)\n");
		sb.append("WHERE {\n");
		sb.append("  ?s ?p ?o.\n");
		sb.append("}");
		String queryString = sb.toString();
		System.out.println(queryString);
		System.out.println("------------------------------");

		//　クエリを発行する
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.sparqlService(endpointURI, query);

		// 結果データセットを取得
		ResultSet results = qe.execSelect();

		// 結果データセットを表形式で表示する
		//		ResultSetFormatter.out(System.out, results, query);

		// 結果データセットから、結果行を一つづつ取得して解析する
		while(results.hasNext()) {
			// 結果行を取得
			QuerySolution solution = results.next();
			// 結果行から変数名 "numTriple" の値を取得
			RDFNode node = solution.get("numTriple");
			System.out.println(node.toString());
		}
		
		// クエリを閉じる
		qe.close();
		
	}

	
}
