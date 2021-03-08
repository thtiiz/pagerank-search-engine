package com.example.webindexing.controller;

import com.example.webindexing.model.ResultModel;
import com.example.webindexing.service.SearchService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ResultController {
  @Autowired
  private SearchService searchService;

  @RequestMapping(value = "/results", method = RequestMethod.GET)
  public ModelAndView showResultPage(
          @RequestParam String query, @RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "5") String size) throws IOException, ParseException, java.text.ParseException {
    ResultModel resultModel = searchService.search(query, Integer.parseInt(page), Integer.parseInt(size));
    return new ModelAndView("results", "result", resultModel.toMap());
  }
}
