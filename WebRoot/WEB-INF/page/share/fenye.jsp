<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
当前页：${pageView.currentpage} | 总记录数：${pageView.totalrecords } | 每页显示：${pageView.maxresult }条 | 总页数：${pageView.totalpage }
<c:forEach begin="${pageView.pageindex.firstindex}" end="${pageView.pageindex.lastindex}" var="wp">
     <c:if test="${pageView.currentpage == wp}"><b>第${wp}页</b></c:if>
     <c:if test="${pageView.currentpage != wp}">
         <a href="javascript:topage(${wp})" class="a03">第${wp}页</a>
      </c:if>
</c:forEach>