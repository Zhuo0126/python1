package com.springboot.demo.model;

import java.io.Serializable;

public class Test1 implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.TEST1.test1
     *
     * @mbg.generated
     */
    private String test1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.TEST1.test2
     *
     * @mbg.generated
     */
    private String test2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dbo.TEST1
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.TEST1
     *
     * @mbg.generated
     */
    public Test1(String test1, String test2) {
        this.test1 = test1;
        this.test2 = test2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.TEST1
     *
     * @mbg.generated
     */
    public Test1() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.TEST1.test1
     *
     * @return the value of dbo.TEST1.test1
     *
     * @mbg.generated
     */
    public String getTest1() {
        return test1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.TEST1.test1
     *
     * @param test1 the value for dbo.TEST1.test1
     *
     * @mbg.generated
     */
    public void setTest1(String test1) {
        this.test1 = test1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.TEST1.test2
     *
     * @return the value of dbo.TEST1.test2
     *
     * @mbg.generated
     */
    public String getTest2() {
        return test2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.TEST1.test2
     *
     * @param test2 the value for dbo.TEST1.test2
     *
     * @mbg.generated
     */
    public void setTest2(String test2) {
        this.test2 = test2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.TEST1
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", test1=").append(test1);
        sb.append(", test2=").append(test2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}