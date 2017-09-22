# 第2回Linked Open Data（LOD）活用ワークショップ in 神戸 【情報共有用】
[第2回Linked Open Data（LOD）活用ワークショップ in 神戸](http://lod-ws2.peatix.com)の情報共有用のレポジトリです．

講演資料やハンズオンのサンプルプログラム等は，このレポジトリにアップします．  
随時，情報を追記します．


## 1日目　講演会資料

[生命科学とLOD―理研メタデータベースを利用したデータ利活用の実際](https://github.com/KnowledgeGraphJapan/LODws2nd/blob/master/RIKENMetaDatabase_2.pdf) 　理化学研究所 　小林 紀郎



## 2日目　ハンズオン資料

SPARQLの基礎　[説明資料](https://github.com/KnowledgeGraphJapan/LODws2nd/blob/master/LOD-WS-kobe-SPARQL-v1.pdf)  
→[ハンズオン情報共有ページ](https://github.com/KnowledgeGraphJapan/LODws2nd/wiki/%E3%83%8F%E3%83%B3%E3%82%BA%E3%82%AA%E3%83%B3%E3%82%BB%E3%83%83%E3%82%B7%E3%83%A7%E3%83%B3-LOD%E7%94%A8%E6%A4%9C%E7%B4%A2%E8%A8%80%E8%AA%9ESPARQL%E3%81%AE%E5%9F%BA%E7%A4%8E)


Apache Jena ハンズオン
[Javaのプログラムを作成してLODを効率的に処理してみよう　―超入門編―](https://github.com/KnowledgeGraphJapan/LODws2nd/blob/master/ApacheJena%E3%83%8F%E3%83%B3%E3%82%BA%E3%82%AA%E3%83%B3.pdf)　　理化学研究所 　小林 紀郎

## ハンズオンで用いる環境について
ハンズオンに用いる資料やプログラム等の情報は，このレポジトリで順次，公開する予定です．

以下，参考までに，想定している環境をお伝えします．

- 「LOD用クエリ言語SPARQL」のハンズオンでは，基本的にWebブラウザと，テキストエディタのみを使用します．

- 「RDFデータベースの導入方法」のハンズオンでは，Javaで動作するFusekiというRDFデータベースの導入を行います．  
そのため，あらかじめ，Javaのバージョン8以上をインストールされていると，スムーズに進めることができると思われます．

Javaのダウンロード・インストール　https://java.com/ja/download/


- 「Javaのプログラムを作成してLODを効率的に処理してみよう－超入門編－」のハンズオンでは，Apache JenaというJavaのライブラリを用いて解説をします．  
基本的にスライドを用いて解説しますが，手元ので実際にプログラムを実行されたい方は，  
　https://github.com/KnowledgeGraphJapan/Apache-Jena-Sample-Programs  
に公開しているサンプルプログラムを，[Eclipse](https://eclipse.org/)(最新版Oxygenを推奨)にインポートしてお試しください．
  
※ソフトウェアのインストール等についてのご質問は，当日の休憩時間にお声がけいただけましたら，サポートいたします．

## FUSEKIのインストール
Qiitaのサイトを参考にしてインストール

http://archive.apache.org/dist/jena/binaries/

最新は  http://tinyurl.com/Fuseki3-5-0SS0921

Windows編  http://tinyurl.com/fuseki-win10

Mac OS編  http://tinyurl.com/fuseki-MacOSX
  
Qiitaのサイトを参考にしてデータのロード  http://tinyurl.com/fuseki-intro

---
## Federateクエリのサンプル
```PREFIX bp: <http://data.lodosaka.jp/lodws/test/property/> 
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> 
PREFIX wdt: <http://www.wikidata.org/prop/direct/>

SELECT ?s ?wd ?l
WHERE {
  ?s bp:参照 ?wd .
  SERVICE <https://query.wikidata.org/sparql>
   { 
     ?wd rdfs:label ?l.
    FILTER(lang(?l)="en")
   }
}
LIMIT 25
```
