package jp.riken.accc.lod.symposium.sample;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

public class JenaSearcher {

	public static void main(String[] args) throws Exception{

		// デフォルトモデルの作成
		Model model = ModelFactory.createDefaultModel();
	
		// RDFファイルを読み込む
		model.read("/temp/RDFSample1.ttl");
	
		// Statementのイテレータとして、トリプルリストを取得する
		StmtIterator sit = model.listStatements();
		// 次の結果があるかを調べ、あったら処理する
		while( sit.hasNext() ) {
			// Statementを取り出す
			Statement st = sit.next();
			Resource subj = st.getSubject();
			Property prop = st.getPredicate();
			RDFNode objNode = st.getObject();
			if( objNode.isResource() ) {
				// objNodeがResourceであるときの処理
				Resource obj = objNode.asResource();
				System.out.println(subj.getURI() + "---" +  prop.getURI() + "--> " + obj.getURI());
			}else {
				if(objNode.isLiteral()) {
					// objNodeがLiteralであるときの処理
					Literal lit = objNode.asLiteral();
					String datatypeURI = lit.getDatatypeURI();
					// ここでは、データ型を見て、langString　か long のときのみ処理する。必要に応じて他のデータ型を追加すればよい
					if( datatypeURI.equals("http://www.w3.org/1999/02/22-rdf-syntax-ns#langString")) {
						String value = lit.getString();
						String lang = lit.getLanguage();
						System.out.println(subj.getURI() + "---" +  prop.getURI() + "--> \"" + value + "\"@" + lang);
					}
					if( datatypeURI.equals("http://www.w3.org/2001/XMLSchema#long")) {
						long value = lit.getLong();
						System.out.println(subj.getURI() + "---" +  prop.getURI() + "--> " + value );
					}
				}
			}
		}

		// 	主語となっているResourceのリストを取得する
		System.out.println("==== listSubjects ==========");
		ResIterator rit = model.listSubjects();
		while( rit.hasNext() ) {
			Resource res = rit.next();
			System.out.println(res.getURI());
		}
		
		
		// 主語を指定してStatementのリストを取得する
		System.out.println("==== listStatements ========");
		Resource res = model.getResource("http://riken.jp");
		sit = model.listStatements(res, (Property)null, (RDFNode)null);
		while( sit.hasNext() ) {
			Statement st = sit.next();
			Property prop = st.getPredicate();
			RDFNode objNode = st.getObject();
			System.out.println(prop.getURI() + ", " + objNode.toString());
		}
		
		// 主語と述語を指定して目的語のリストを取得する
		System.out.println("==== listObjectsOfProperty ========");
		Property prop = model.getProperty("http://www.w3.org/2000/01/rdf-schema#label");
		NodeIterator nit = model.listObjectsOfProperty(res, prop);
		while( nit.hasNext() ) {
			RDFNode objNode = nit.next();
			System.out.println(objNode.toString());
		}
		
		model.close();

	}

}
