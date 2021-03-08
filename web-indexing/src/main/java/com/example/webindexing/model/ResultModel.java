package com.example.webindexing.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultModel {
  private int page;
  private int size;
  private int totalHits;
  private String query;
  private List<ResultHit> hits = new ArrayList<>();

  public void addResultDocument(ResultHit resultHit) {
    this.hits.add(resultHit);
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getTotalHits() {
    return totalHits;
  }

  public void setTotalHits(int totalHits) {
    this.totalHits = totalHits;
  }

  public List<ResultHit> getResultDocuments() {
    return hits;
  }

  public Map<String, Object> toMap() {
    Map<String, Object> map = new HashMap<>();
    map.put("page", page);
    map.put("size", size);
    map.put("totalHits", totalHits);
    map.put("query", query);
    map.put("hits", hits.stream().map(e -> e.toMap()).toArray());
    return map;
  }

  public void setQuery(String query) {
    this.query = query;
  }


}
