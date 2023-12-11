package com.springboot.demo;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;
import org.apache.commons.math3.linear.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class AHPApplication {
	public static void main(String[] args) throws Exception {


		Scanner scanner =new Scanner(System.in);
		System.out.println("請輸入居住地:");

		//1.計算地點權重
		String location1 = scanner.nextLine();
		distance(location1);

		//2計算學費權重
		pay();

		//3計算學校排名權重
		rank();

		//4科系喜好程度
		System.out.println("請輸入科系喜好程度偏好程度資管,統計,資工,分數越高越重要(1~9),請使用逗號分隔");
		String choose = scanner.nextLine();
		String[] o = choose.split(",");
		double aa=Double.valueOf(o[0]);
		double bb=Double.valueOf(o[1]);
		double cc=Double.valueOf(o[2]);
		double[][] matrix0 = {
				{1,aa/bb,aa/cc},
				{bb/aa,1,bb/cc},
				{cc/aa,cc/bb,1}
		};
		AHP(matrix0);


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
		//AHP換算
		AHP(matrix);
	}

	private static void rank(){
		//台大:
		Double a= 1.00;
		//政大:
		Double b= 13.00;
		//元智:
		Double c= 21.00;
		double[][] matrix = {
				{1,b/a,c/a},
				{a/b,1,c/b},
				{a/c,b/c,1}
		};
		//AHP換算
		AHP(matrix);
	}

	private static void distance(String local){
		String location2 ;
		//台大:臺北市大安區羅斯福路四段1號
		String a="臺北市大安區羅斯福路四段1號";
		//政大:臺北市文山區指南路二段64號
		String b="臺北市文山區指南路二段64號";
		//元智:桃園縣遠東路135號
		String c="桃園縣遠東路135號";
		double distance_a= calculateDistance(local, a);
		double distance_b= calculateDistance(local, b);
		double distance_c= calculateDistance(local, c);
		double[][] matrix = {
				{1,distance_b/distance_a,distance_c/distance_a},
				{distance_a/distance_b,1,distance_c/distance_b},
				{distance_a/distance_c,distance_b/distance_c,1}
		};
		//AHP換算
		AHP(matrix);
	}

	private static void pay(){
		//台大:
		Double a= 34500.00;
		//政大:
		Double b= 28430.00;
		//元智:
		Double c= 68000.00;
		double[][] matrix = {
				{1,b/a,c/a},
				{a/b,1,c/b},
				{a/c,b/c,1}
		};
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

	private static double calculateDistance(String origin, String destination) {
		try {
			GeoApiContext context = new GeoApiContext.Builder()
					.apiKey("AIzaSyCp0qEeDIvhDCQ2Z-mgJhPrbsXyv63zlTI")
					.build();

			DirectionsApiRequest request = DirectionsApi.getDirections(context, origin, destination)
					.mode(TravelMode.DRIVING);  // 或者使用 TravelMode.WALKING, TravelMode.BICYCLING，TravelMode.TRANSIT

			DirectionsRoute[] routes = request.await().routes;
			if (routes.length > 0) {
				return routes[0].legs[0].distance.inMeters;
			} else {
				System.err.println("No route found between the locations.");
				return -1.0;
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return -1.0;
		} catch (ApiException e) {
			throw new RuntimeException(e);
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
