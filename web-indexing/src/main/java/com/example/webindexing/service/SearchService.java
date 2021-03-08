package com.example.webindexing.service;

import com.example.webindexing.model.ResultHit;
import com.example.webindexing.model.ResultModel;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.th.ThaiAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.expressions.Expression;
import org.apache.lucene.expressions.SimpleBindings;
import org.apache.lucene.expressions.js.JavascriptCompiler;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queries.function.FunctionScoreQuery;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;

@Component("searchService")
public class SearchService {
  private String index = "src/main/resources/index";

  public ResultModel search(String queryString, int page, int size) throws IOException, ParseException, java.text.ParseException {

    IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(this.index)));
    IndexSearcher searcher = new IndexSearcher(reader);
    Analyzer analyzer = new ThaiAnalyzer();
    QueryParser parser = new QueryParser("contents", analyzer);
    Query query = parser.parse(queryString);

    // SimpleBindings just maps variables to SortField instances
    SimpleBindings bindings = new SimpleBindings();
    bindings.add("score", DoubleValuesSource.SCORES);
    bindings.add("pageRankScore", DoubleValuesSource.fromFloatField("pageRankScore"));
    Expression expr = JavascriptCompiler.compile("0.5*score + 0.5*pageRankScore");
    FunctionScoreQuery customQuery = new FunctionScoreQuery(query, expr.getDoubleValuesSource(bindings));
    QueryScorer queryScorer = new QueryScorer(customQuery);

    Formatter formatter = new SimpleHTMLFormatter("<b>", "</b>");
    Highlighter highlighter = new Highlighter(formatter, queryScorer);

    int startIndex = (page - 1) * size;
    TopScoreDocCollector collector = TopScoreDocCollector.create(page * size, Integer.MAX_VALUE);
    searcher.search(query, collector);
    int totalHits = collector.getTotalHits();
    TopDocs results = collector.topDocs(startIndex, size);
    ScoreDoc[] hits = results.scoreDocs;

    ResultModel resultModel = new ResultModel();
    resultModel.setPage(page);
    resultModel.setSize(size);
    resultModel.setTotalHits(totalHits);
    resultModel.setQuery(queryString);

//    int endIndex = Math.min(totalHits, page * size)page;
    for (int i = 0; i < hits.length; i++) {
      Document doc = searcher.doc(hits[i].doc);
      String title = doc.get("title");
      String contents = doc.get("contents");
      String path = doc.get("path");
      String url = doc.get("url");
      String snippet = "";

      TokenStream tokenStream = analyzer.tokenStream("contents", contents);
      highlighter.setTextFragmenter(new SimpleFragmenter(200));

      try {
        snippet = highlighter.getBestFragments(tokenStream, contents, 2, "...");
      } catch (InvalidTokenOffsetsException e) {
        e.printStackTrace();
      }

      resultModel.addResultDocument(new ResultHit(title, snippet, path, url));
    }

    return resultModel;
  }

}
