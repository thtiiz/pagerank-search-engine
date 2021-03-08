<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
       <%@include file="header.jsp" %>
       <div class="container">
       <a href="/">
       <button type="button" class="mt-4 btn btn-primary btn-sm" aria-label="Left Align">
       Back
       </button>
       </a>
       <h4 class="mt-4">Search results for "${result.query}" (${result.totalHits})</h4>
       <h5 class="mt-4">Page ${result.page}</h5>
       <div>
       <c:forEach items="${result.hits}" var="hit">
              <div class="card card-body">
              <h5 class="card-title"><c:out value="${hit.title}" /></h5>
              <p class="card-text"><c:out value="${hit.snippet}" escapeXml="false" /></p>
              <a href="${hit.url}"><c:out value="${hit.url}" /></a>
              </div>
       </c:forEach>
       </div>
       <c:if test="${result.totalHits == 0}">
              <div class="d-flex justify-content-center">
       <img src="https://mantuatownship.com/wp-content/uploads/2018/01/no-results-empty-state.jpg" />
              </div>
       </c:if>
       <div class="mt-4">
              <ul class="pagination col justify-content-end">
                     <c:set var="totalPage" value="${result.totalHits / result.size}"/>
                     <c:set var="startPagination" value="${result.page-2 > 1 ? result.page-2:1}"/>
                     <c:set var="endPagination" value="${totalPage < startPagination+3 ? totalPage:(startPagination+3)}"/>
                     <c:set var="endPagination" value="${endPagination + 1}"/>
                     <c:set var="query" value="?query=${result.query}" />
                     <c:choose>
                            <c:when test="${result.page eq 1}">
                                   <li class="page-item disabled">
                                   <a class="page-link" href="#" tabindex="-1">Previous</a>
                                   </li>
                            </c:when>
                            <c:otherwise>
                                   <li class="page-item">
                                   <a class="page-link" href="${query}&page=${result.page-1}" tabindex="-1">Previous</a>
                                   </li>
                            </c:otherwise>
                     </c:choose>
                     <c:forEach var="i" begin="${startPagination}" end="${endPagination}" step="1">
                            <c:choose>
                                   <c:when test="${i eq result.page}">
                                          <li class="page-item active">
                                   </c:when>
                                   <c:otherwise>
                                          <li class="page-item">
                                   </c:otherwise>
                            </c:choose>
                                   <a class="page-link" href="${query}&page=${i}"><c:out value="${i}"/></a>
                            </li>
                     </c:forEach>
                     <c:choose>
                            <c:when test="${result.page gt totalPage}">
                                   <li class="page-item disabled">
                                          <a class="page-link" href="#">Next</a>
                                   </li>
                            </c:when>
                            <c:otherwise>
                                   <li class="page-item">
                                          <a class="page-link" href="${query}&page=${result.page+1}">Next</a>
                                   </li>
                            </c:otherwise>
                     </c:choose>
              </ul>
       </div>
       </div>
       <%@include file="footer.jsp" %>