<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="<c:url value="/resources/js/controller.js"/>"></script>

<div class="container-wrapper">

	<div class="container">

		<div class="jumbotron">
			<div class="container">
				<h2>Cart</h2>
				<p>All the selected products in your shopping cart</p>
			</div>
		</div>

		<section class="container" ng-app="cartApp">

			<!-- initCartId(cartId) 메서드 호출로 scope에 cart정보가 담겨져 있게 된다. -->
			<div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">

				<!-- 누르면 clearCart() 메서드 호출 -->
				<a class="btn btn-warning pull-left" ng-click="clearCart()"> <i
					calss="fa fa-trash"></i> Clear Cart
				</a>

				<div class="table-responsive">
					<table class="table table-hover table-condensed">
						<tr>
							<th>Product</th>
							<th>Unit Price</th>
							<th>Quantity</th>
							<th>Total Price</th>
							<th>Action</th>
						</tr>

						<!-- 루프 -->
						<tr ng-repeat="item in cart.cartItems">
							<td>{{item.product.name}}</td>
							<td>{{item.product.price}}</td>
							<td>{{item.quantity}}</td>
							<td>{{item.totalPrice}}</td>
							<td style="width: 100%">
								<!-- removeFromCart(productId) 메서드 호출 --> <a
								class="btn btn-danger btn-sm"
								ng-click="removeFromCart(item.product.id)"> <span
									class="fa fa-minus"></span> remove
							</a> <!-- plusFromCart(productId) 메서드 호출 --> <a
								class="btn btn-danger btn-sm"
								ng-click="plusFromCart(item.product.id)"> <span
									class="fa fa-plus"></span> plus
							</a> <!-- minusFromCart(productId) 메서드 호출 --> <a
								class="btn btn-danger btn-sm"
								ng-click="minusFromCart(item.product.id)"> <span
									class="fa fa-minus"></span> minus
							</a>
							</td>
						</tr>

						<tr>
							<td></td>
							<td></td>
							<td>Grand Total</td>
							<td>{{calGrandTotal()}}</td>
							<td></td>
						</tr>
					</table>
				</div>

				<a class="btn btn-info" href="<c:url value="/products"/>"
					class="btn btn-default">Continue Shopping</a>
			</div>
		</section>
	</div>

</div>