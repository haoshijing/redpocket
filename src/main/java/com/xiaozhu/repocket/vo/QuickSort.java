package com.xiaozhu.repocket.vo;

import java.util.Random;

public class QuickSort {

    public static  int getMid(Integer[] arr,int low , int high){
        int temp = arr[low];
        while(low < high){
            while (low < high && arr[high] >= temp){
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= temp){
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }

    public static void quickSort(Integer [] arr,int low,int high){
        if(low < high){
            int mIdx = getMid(arr,low, high);
            quickSort(arr,low,mIdx);
            quickSort(arr,mIdx+1 , high);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[1000000];
        for(int i = 0 ; i < arr.length;i++){
            arr[i] = new Random().nextInt(400000000);
        }
        Long t1 = System.currentTimeMillis();
        quickSort(arr,0 , arr.length -1);
        Long t2 = System.currentTimeMillis();
        System.out.println("args = [" + (t2-t1) + "]");
    }
}
