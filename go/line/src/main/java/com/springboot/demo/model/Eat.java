package com.springboot.demo.model;

import java.io.Serializable;

public class Eat implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.EAT.NAME
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.EAT.isOPEN
     *
     * @mbg.generated
     */
    private Integer isopen;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dbo.EAT
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.EAT
     *
     * @mbg.generated
     */
    public Eat(String name, Integer isopen) {
        this.name = name;
        this.isopen = isopen;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.EAT
     *
     * @mbg.generated
     */
    public Eat() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.EAT.NAME
     *
     * @return the value of dbo.EAT.NAME
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.EAT.NAME
     *
     * @param name the value for dbo.EAT.NAME
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.EAT.isOPEN
     *
     * @return the value of dbo.EAT.isOPEN
     *
     * @mbg.generated
     */
    public Integer getIsopen() {
        return isopen;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.EAT.isOPEN
     *
     * @param isopen the value for dbo.EAT.isOPEN
     *
     * @mbg.generated
     */
    public void setIsopen(Integer isopen) {
        this.isopen = isopen;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.EAT
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", name=").append(name);
        sb.append(", isopen=").append(isopen);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}