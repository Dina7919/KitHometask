import java.util.Arrays;
public class Solution {

        public static void main(String[] args) {

            int[][] matrix = new int[5][5];

            for (int i = 0; i < matrix.length; i++) {

                matrix[i][i] = 1;

                matrix[i][matrix.length - 1 - i] = 1;

            }

            for (int i = 0; i < matrix.length; i++) {

                for (int j = 0; j < matrix.length; j++) {

                    System.out.print(matrix[i][j] + " ");

                }

                System.out.println();

            }

            System.out.println(checkBalance(new int[] {10,1,2,3,4} ));

        }

// Задача: Написать метод, в который передается не пустой одномерный целочисленный массив,

// метод должен вернуть true если в массиве есть место, в котором сумма левой и правой части массива равны.

// Примеры: checkBalance([1, 1, 1, || 2, 1]) → true,

// checkBalance([2, 1, 1, 2, 1]) → false,

// checkBalance([10, || 1, 2, 3, 4]) → true.

// Абстрактная граница показана символами ||, эти символы в массив не входят.

        public static boolean checkBalance(int[] array) {

            int leftSum = array[0];

            int rightSum = 0;

            for (int i = 1; i < array.length; i++) {

                rightSum += array[i];

            }

            if (leftSum == rightSum) return true;

            for (int i = 1; i < array.length; i++) {

                leftSum += array[i];

                rightSum -= array[i];

                if (leftSum == rightSum) return true;

            }

            return false;

        }

    }
