<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<div class="container-wrapper">
		<p>
		<div class="container">
			<h1>Add Product</h1>
			<p class="lead">Fill the below information to add a product:</p>

			<!-- addProduct.jsp 파일의 submit버튼을 눌렀을 때, post방식으로 AdminController.addProduct()메서드 호출  -->
			<!--  csrf토큰이 없어서 에러남=> 해결법(Multipart 해결하는 방법)
			https://docs.spring.io/spring-security/site/docs/current/reference/html/csrf.html#csrf-include-csrf-token-in-action
			  ?${_csrf.parameterName}=${_csrf.token} 
			  => 쿼리 스트링으로 넘어감.
			  -->
			<!-- html form 태그랑 다르게 spring form 태그는 자동으로 csrf 토큰을 보냄 -->
			<sf:form
				action="${pageContext.request.contextPath}/admin/productInventory/addProduct?${_csrf.parameterName}=${_csrf.token}"
				method="post" modelAttribute="product" enctype="multipart/form-data">

				<div class="form-group">
					<label for="name">Name:</label>
					<sf:input path="name" id="name" class="form-control" />
					<sf:errors path="name" cssStyle="color:#ff0000" />
				</div>
				<div class="form-group">
					<label for="category">Category:</label>
					<sf:radiobutton path="category" id="category" value="컴퓨터" />
					컴퓨터
					<sf:radiobutton path="category" id="category" value="가전" />
					가전
					<sf:radiobutton path="category" id="category" value="신발" />
					신발
				</div>
				<div class="form-group">
					<label for="description">Description:</label>
					<sf:textarea path="description" id="description"
						class="form-control" />
				</div>
				<div class="form-group">
					<label for="price">Price:</label>
					<sf:input path="price" id="price" class="form-control" />
					<sf:errors path="price" cssStyle="color:#ff0000" />
				</div>
				<div class="form-group">
					<label for="unitInStock">Unit In Stock:</label>
					<sf:input path="unitInStock" id="unitInStock" class="form-control" />
					<sf:errors path="unitInStock" cssStyle="color:#ff0000" />
				</div>
				<div class="form-group">
					<label for="manufacturer">Manufacturer:</label>
					<sf:input path="manufacturer" id="manufacturer"
						class="form-control" />
					<sf:errors path="manufacturer" cssStyle="color:#ff0000" />
				</div>

				<div class="form-group">
					<label for="productImage">Upload Picture:</label>
					<sf:input path="productImage" id="productImage" type="file"
						class="form-control" />
				</div>

				<br>

				<!-- Post 방식의 addProduct Method 이용 -->
				<input type="submit" value="submit" class="btn btn-default">
				<a href="<c:url value="/admin/productInventory" />"
					class="btn btn-default">Cancel</a>

			</sf:form>
			<br>
		</div>
	</div>
</body>