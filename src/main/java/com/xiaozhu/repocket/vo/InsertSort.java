package com.xiaozhu.repocket.vo;

public class InsertSort {

    public static void insertSort(int[] arr) {
        int len = arr.length;

        for(int j = 1; j <  len;j++){
            int preIdx = j - 1;
            int current = arr[j];
            while (preIdx >= 0 && arr[preIdx] > current){
                arr[preIdx+1] = arr[preIdx];
                preIdx--;
            }
            arr[preIdx+1 ] = current;
        }
    }

    //9 10 0 7 11 12 6
    //
    public static void main(String[] args) {
        int arr[] = {9, 10, 0, 7, 11, 12 ,6};
        insertSort(arr);
        System.out.println("arr = [" + arr + "]");
    }
}
