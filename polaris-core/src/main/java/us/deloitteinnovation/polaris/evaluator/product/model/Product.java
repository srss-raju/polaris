package us.deloitteinnovation.polaris.evaluator.product.model;

/**
 * Created by rbentaarit on 9/19/2016.
 */
public class Product {

    private Integer productId;
    private String productName;
    private String clientProductId;
    private String productListPrice;

    private String productLevel1;
    private String productLevel2;
    private String productLevel3;
    private String productLevel4;
    private String productLevel5;
    private String productLevel6;
    private String productLevel7;

    private String productAttribute1;
    private String productAttribute2;
    private String productAttribute3;
    private String productAttribute4;
    private String productAttribute5;

    private String currency;

    public String getProductListPrice() {
        return productListPrice;
    }

    public void setProductListPrice(String productListPrice) {
        this.productListPrice = productListPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getClientProductId() {
        return clientProductId;
    }

    public void setClientProductId(String clientProductId) {
        this.clientProductId = clientProductId;
    }

    public String getProductLevel1() {
        return productLevel1;
    }

    public void setProductLevel1(String productLevel1) {
        this.productLevel1 = productLevel1;
    }

    public String getProductLevel2() {
        return productLevel2;
    }

    public void setProductLevel2(String productLevel2) {
        this.productLevel2 = productLevel2;
    }

    public String getProductLevel3() {
        return productLevel3;
    }

    public void setProductLevel3(String productLevel3) {
        this.productLevel3 = productLevel3;
    }

    public String getProductLevel4() {
        return productLevel4;
    }

    public void setProductLevel4(String productLevel4) {
        this.productLevel4 = productLevel4;
    }

    public String getProductLevel5() {
        return productLevel5;
    }

    public void setProductLevel5(String productLevel5) {
        this.productLevel5 = productLevel5;
    }

    public String getProductLevel6() {
        return productLevel6;
    }

    public void setProductLevel6(String productLevel6) {
        this.productLevel6 = productLevel6;
    }

    public String getProductLevel7() {
        return productLevel7;
    }

    public void setProductLevel7(String productLevel7) {
        this.productLevel7 = productLevel7;
    }

    public String getProductAttribute1() {
        return productAttribute1;
    }

    public void setProductAttribute1(String productAttribute1) {
        this.productAttribute1 = productAttribute1;
    }

    public String getProductAttribute2() {
        return productAttribute2;
    }

    public void setProductAttribute2(String productAttribute2) {
        this.productAttribute2 = productAttribute2;
    }

    public String getProductAttribute3() {
        return productAttribute3;
    }

    public void setProductAttribute3(String productAttribute3) {
        this.productAttribute3 = productAttribute3;
    }

    public String getProductAttribute4() {
        return productAttribute4;
    }

    public void setProductAttribute4(String productAttribute4) {
        this.productAttribute4 = productAttribute4;
    }

    public String getProductAttribute5() {
        return productAttribute5;
    }

    public void setProductAttribute5(String productAttribute5) {
        this.productAttribute5 = productAttribute5;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
