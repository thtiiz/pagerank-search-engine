package com.example.webindexing.model;

import com.example.webindexing.view.ResultView;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.HashMap;
import java.util.Map;

public class ResultHit {
  @JsonView({ResultView.class})
  public String title;

  @JsonView({ResultView.class})
  public String snippet;

  @JsonView({ResultView.class})
  public String path;

  @JsonView({ResultView.class})
  public String url;

  public ResultHit(String title, String snippet, String path, String url) {
    this.title = title;
    this.snippet = snippet;
    this.path = path;
    this.url = url;
  }

  public Map<String, Object> toMap() {
    Map<String, Object> map = new HashMap<>();
    map.put("title", title);
    map.put("snippet", snippet);
    map.put("path", path);
    map.put("url", url);
    return map;
  }
}
