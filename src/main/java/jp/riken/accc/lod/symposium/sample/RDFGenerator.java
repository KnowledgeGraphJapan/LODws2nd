package jp.riken.accc.lod.symposium.sample;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class RDFGenerator {

	public static void main(String[] args) throws Exception{

		// デフォルトモデルの作成
		Model model = ModelFactory.createDefaultModel();
		
		// 主語となるリソースを作成 (理研)
		Resource subj = model.createResource("http://riken.jp");
		// プロパティを作成 (ホームページ)
		Property prop = model.createProperty("http://xmlns.com/foaf/0.1/homepage");
		// 目的語となるリソースを作成 (理研のウエブサイト)
		Resource obj = model.createResource("http://www.riken.jp");
		// トリプルを作成
		subj.addProperty(prop, obj);

		// プロパティを作成  (ラベル)
		Property propLabel = model.createProperty("http://www.w3.org/2000/01/rdf-schema#label");
		// リテラルを作成 (英語でRIKEN)
		Literal lit = model.createLiteral("RIKEN", "en");
		// トリプルを作成
		subj.addLiteral(propLabel, lit);

		// リテラルを作成 (日本語で理研)
		Literal litJa = model.createLiteral("理研", "ja");
		// トリプルを作成
		subj.addLiteral(propLabel,  litJa);

		// プロパティを作成  (創立年、ただし独自のプロパティで共通語彙ではない)
		Property propEst = model.createProperty("http://riken.jp/establishedIn");
		// トリプルを作成
		subj.addLiteral(propEst, 1917);

		// N-TRIPLES形式でmodel内のデータを標準出力に表示
		model.write(System.out, "N-TRIPLES"); 
		
		// N-TRIPLES形式でmodel内のデータをファイルに出力
		FileOutputStream fos = new FileOutputStream(new File("/temp/RDFSample1.ttl"));
		model.write(fos, "N-TRIPLES");
		fos.close();

		// モデルを閉じる
		model.close();
	}
}
