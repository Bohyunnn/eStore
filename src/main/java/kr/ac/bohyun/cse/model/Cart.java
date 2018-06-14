package kr.ac.bohyun.cse.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Cart implements Serializable {

	//클래스의 버전 정의
	private static final long serialVersionUID = -112450066165733271L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy="cart", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<CartItem> cartItems = new ArrayList<CartItem>();

	private double grandTotal;

}
