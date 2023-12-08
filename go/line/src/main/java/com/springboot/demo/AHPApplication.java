package com.springboot.demo;

import org.apache.commons.math3.linear.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class AHPApplication {
	public static void main(String[] args) throws Exception {
		// 以下矩阵表示的是判定矩阵，你需要根据你的具体问题进行修改
//		double[][] matrix = {
//				{1, 2, 5},
//				{1.0 / 2, 1, 4},
//				{1.0 / 5, 1.0 / 4, 1}
//		};
		// [7,5,3,1] / [16]
		Scanner scanner =new Scanner(System.in);
		System.out.println("請輸入地點,學費,學校排名,科系喜好程度偏好程度,分數越高越重要(1~9),請使用逗號分隔");
		String point = scanner.nextLine();
		String[] t = point.split(",");
		double a=Double.valueOf(t[0]);
		double b=Double.valueOf(t[1]);
		double c=Double.valueOf(t[2]);
		double d=Double.valueOf(t[3]);

		double[][] matrix = {
				{1,a/b,a/c,a/d},
				{b/a,1,b/c,b/d},
				{c/a,c/b,1,c/d},
				{d/a,d/b,d/c,1}
		};

//		double[][] matrix = {
//				{1		 , 7.0/ 5.0, 7.0/3.0 , 7},
//				{5.0/7.0 , 1	   , 5.0/3.0 , 5},
//				{3.0/7.0 ,  3.0/5.0, 1       , 3 },
//				{1.0/7.0  , 1.0/5.0 ,1.0/ 3.0 , 1 }
//		};

		//AHP換算
		AHP(matrix);
	}

	private static void AHP(double[][] matrix){
		AHPApplication ahp = new AHPApplication();
		double[] weights = ahp.calculateWeights(matrix);

		// 輸出計算得到的權重
		System.out.println("Calculated Weights:");
		for (double weight : weights) {
			System.out.println(weight);
		}
	}

	// 計算權重
	private double[] calculateWeights(double[][] matrix) {
		int n = matrix.length;

		// 将判定矩阵转换为实数矩阵
		RealMatrix realMatrix = new Array2DRowRealMatrix(matrix);

		// 计算特征向量
		double[] eigenVector = calculateEigenVector(realMatrix);

		// 计算一致性指标
		double consistencyIndex = calculateConsistencyIndex(realMatrix, eigenVector);

		// 如果一致性指标超过某个阈值，可能需要重新考虑输入的判定矩阵
		System.out.println("Consistency Index: " + consistencyIndex);

		// 归一化特征向量得到权重
		normalizeWeights(eigenVector);

		return eigenVector;
	}

	// 计算特征向量
	private double[] calculateEigenVector(RealMatrix matrix) {
		EigenDecomposition decomposition = new EigenDecomposition(matrix);
		// 获取特征值对应的索引
		int indexOfMaxEigenvalue = findIndexOfMaxValue(decomposition.getRealEigenvalues());

		// 获取特征向量
		double[] eigenVector = decomposition.getEigenvector(indexOfMaxEigenvalue).toArray();

		return eigenVector;
	}
	// 辅助方法：找到数组中最大值的索引
	private int findIndexOfMaxValue(double[] array) {
		int maxIndex = 0;
		double maxValue = array[0];

		for (int i = 1; i < array.length; i++) {
			if (array[i] > maxValue) {
				maxIndex = i;
				maxValue = array[i];
			}
		}

		return maxIndex;
	}
	// 计算一致性指标
	private double calculateConsistencyIndex(RealMatrix matrix, double[] eigenVector) {
		double lambdaMax = eigenVector[eigenVector.length - 1];
		int n = matrix.getRowDimension();
		return (lambdaMax - n) / (n - 1);
	}

	// 归一化特征向量得到权重
	private void normalizeWeights(double[] eigenVector) {
		double sum = 0;
		for (double value : eigenVector) {
			sum += value;
		}

		// 归一化
		for (int i = 0; i < eigenVector.length; i++) {
			eigenVector[i] /= sum;
		}
	}
}
