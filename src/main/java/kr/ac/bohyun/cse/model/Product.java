package kr.ac.bohyun.cse.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
public class Product implements Serializable{

	private static final long serialVersionUID = -7006464740637399773L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int id;

	@NotEmpty(message = "The product name must not be null.")
	private String name;

	private String category;

	@Min(value = 0, message = "The product price must not be less than zero.")
	private int price;

	@NotEmpty(message = "The manufacturer must not be null.")
	private String manufacturer;

	@Min(value = 0, message = "The product price must not be less than zero.")
	private int unitInStock;
	private String description;

	@Transient //실제로 저장되는 게 아니므로 @Transient
	private MultipartFile productImage;
	//DB에 저장하는 게 아니라 FileSystem에 저장됨. 
	//DB에는 파일 이름만 저장됨.

	private String imageFileName;
	
}
