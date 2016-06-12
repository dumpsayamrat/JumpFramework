<%@ include file="/WEB-INF/pages/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
    <div class="container">

        <div class="row">

            <div class="col-md-3">
              
            </div>

            <div class="col-md-9">

                <div class="thumbnail">
                    <c:if test="${sessionScope.name == null }">
                    	<div class="caption-full">
	                        <h3> LOGIN... </h3>
	                        <c:url var="action" value="/login.do" />
	                        <form:form action="${action }" method="POST">
	                        	<div class="form-group">
									<label name="username" id="username">Username: </label>
									<input name="username" id="username" class="form-control" type="text" />
								</div>
								<div class="form-group">
									<label name="password" id="password">Password: </label>
									<input name="password" id="password" class="form-control" type="password" />
								</div>
								<input class="btn btn-primary" type="submit" name="action" value="Login" />
	                        </form:form>
	                        <a href='<c:url value="/juser/add"></c:url>' style="margin-top: 60px;font-size:16px ">register ?</a>
                    	</div>
                    </c:if>
                    <c:if test="${sessionScope.name != null }">
                    	<div class="caption-full">
                    	 Hi! ${sessionScope.name }
                    	</div>
                    </c:if>
                </div>

            </div>

        </div>

    </div>
<%@ include file="/WEB-INF/pages/footer.jsp"%>