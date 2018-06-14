<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-wrapper">
	<div class="container">
		<h2>All Products</h2>
		<p class="lead">착한 가격에 모든 상품을 살펴보세요</p>
		<table class="table table-striped">
			<thead>
				<tr class="bg-success">
					<th>Photo Thumb</th>
					<th>Product Name</th>
					<th>Category</th>
					<th>Price</th>
					<th>Manufacturer</th>
					<th>Unit in Stock</th>
					<th>Description</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<!-- 사용자 Request로 수행된 Controller 메소드의 리턴값과 일치해야함. products -->
				<c:forEach var="product" items="${products}">
					<tr>
						<td><img
							src="<c:url value="/resources/images/${product.imageFileName}"/>"
							alt="image" style="width: 60%" /></td>
						<td>${product.name}</td>
						<td>${product.category}</td>
						<td>${product.price}</td>
						<td>${product.manufacturer}</td>
						<td>${product.unitInStock}</td>
						<td>${product.description}</td>
						<td><a href="<c:url value="/viewProduct/${product.id}" />">
								<i class="fa fa-info-circle"></i>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
