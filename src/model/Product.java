package model;

import utils.ValidationUtils;

import java.sql.Date;
import java.util.Scanner;

public class Product {
    private int productId;
    private String productName;
    private String company;
    private float price;
    private String madeIn;
    private Date expiredAt;
    private Date madeAt;

    public Product() {

    }

    public Product(int productId, String productName, String company, float price, String madeIn, Date expiredAt, Date madeAt) {
        this.productId = productId;
        this.productName = productName;
        this.company = company;
        this.price = price;
        this.madeIn = madeIn;
        this.expiredAt = expiredAt;
        this.madeAt = madeAt;
    }

    public  Product inputProductInfo(Scanner input){
        productId = ValidationUtils.readInteger("Enter your product Id: ",input, true);
        input.nextLine();
        System.out.println("Enter your product name : ");
        productName = input.nextLine();
        System.out.println("Enter your company : ");
        company = input.nextLine();
        price = ValidationUtils.readFloat("Enter your price : ", input);
        System.out.println("Enter made in : ");
        input.nextLine();
        madeIn = input.nextLine();
        madeAt = ValidationUtils.readSqlDate("Product made at (yyyy-MM-dd): ", input);
        expiredAt = ValidationUtils.readSqlDate("Product expired at (yyyy-MM-dd): ", input);


        return this;

    }
    public  Product updateProductInfo(Scanner input){
        System.out.println("Enter your product name : ");
        productName = input.nextLine();
        System.out.println("Enter your company : ");
        company = input.nextLine();
        price = ValidationUtils.readFloat("Enter your price : ", input);
        System.out.println("Enter made in : ");
        input.nextLine();
        madeIn = input.nextLine();
        madeAt = ValidationUtils.readSqlDate("Product made at (yyyy-MM-dd): ", input);
        expiredAt = ValidationUtils.readSqlDate("Product expired at (yyyy-MM-dd): ", input);


        return this;

    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", company='" + company + '\'' +
                ", price=" + price +
                ", madeIn='" + madeIn + '\'' +
                ", expiredAt='" + expiredAt + '\'' +
                ", madeAt='" + madeAt + '\'' +
                '}';
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Date getMadeAt() {
        return madeAt;
    }

    public void setMadeAt(Date madeAt) {
        this.madeAt = madeAt;
    }
}
