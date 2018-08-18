package us.deloitteinnovation.polaris.evalcalculation.model;

import java.util.Date;

/**
 * Created by rbentaarit on 8/31/2016.
 */
public class VolumeDecompDetail {
    private int id;
    private String promoType;
    private  String promoTactic;
    private int customerId;
    private Date date;
    private float promoPrice;
    private float nonPromoPrice;
    private Boolean cp;
    private Boolean cnp;
    private Boolean pv1;
    private Boolean pv2;
    private Boolean pv3;
    private Boolean pv4;
    private Boolean pv5;
    private Boolean npv1;
    private Boolean npv2;
    private Boolean npv3;
    private Boolean npv4;
    private Boolean npv5;
    private float v;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromoType() {
        return promoType;
    }

    public void setPromoType(String promoType) {
        this.promoType = promoType;
    }

    public String getPromoTactic() {
        return promoTactic;
    }

    public void setPromoTactic(String promoTactic) {
        this.promoTactic = promoTactic;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(float promoPrice) {
        this.promoPrice = promoPrice;
    }

    public float getNonPromoPrice() {
        return nonPromoPrice;
    }

    public void setNonPromoPrice(float nonPromoPrice) {
        this.nonPromoPrice = nonPromoPrice;
    }

    public Boolean isCp() {
        return cp;
    }

    public void setCp(boolean cp) {
        this.cp = cp;
    }

    public Boolean isCnp() {
        return cnp;
    }

    public void setCnp(boolean cnp) {
        this.cnp = cnp;
    }

    public Boolean isPv1() {
        return pv1;
    }

    public void setPv1(boolean pv1) {
        this.pv1 = pv1;
    }

    public Boolean isPv2() {
        return pv2;
    }

    public void setPv2(boolean pv2) {
        this.pv2 = pv2;
    }

    public Boolean isPv3() {
        return pv3;
    }

    public void setPv3(boolean pv3) {
        this.pv3 = pv3;
    }

    public Boolean isPv4() {
        return pv4;
    }

    public void setPv4(boolean pv4) {
        this.pv4 = pv4;
    }

    public Boolean isPv5() {
        return pv5;
    }

    public void setPv5(boolean pv5) {
        this.pv5 = pv5;
    }

    public Boolean isNpv1() {
        return npv1;
    }

    public void setNpv1(boolean npv1) {
        this.npv1 = npv1;
    }

    public Boolean isNpv2() {
        return npv2;
    }

    public void setNpv2(boolean npv2) {
        this.npv2 = npv2;
    }

    public Boolean isNpv3() {
        return npv3;
    }

    public void setNpv3(boolean npv3) {
        this.npv3 = npv3;
    }

    public Boolean isNpv4() {
        return npv4;
    }

    public void setNpv4(boolean npv4) {
        this.npv4 = npv4;
    }

    public Boolean isNpv5() {
        return npv5;
    }

    public void setNpv5(boolean npv5) {
        this.npv5 = npv5;
    }

    public float getV() {
        return v;
    }

    public void setV(float v) {
        this.v = v;
    }
}