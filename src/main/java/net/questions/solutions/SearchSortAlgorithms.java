package net.questions.solutions;

import java.util.Stack;

public class SearchSortAlgorithms {
    public int tripleStepR(int stepCount){
        if (stepCount<0)return 0;
        else if (stepCount==0) return 1;
        else
            return tripleStepR(stepCount-1)+tripleStepR(stepCount-2)+tripleStepR(stepCount-3);
    }

    public int tripleStep(int stepCount){
        int possibleWays=0;
        int stepTypes =3;

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while (!stack.isEmpty()){
            int current = stack.pop();
            if (current==stepCount){
                possibleWays++;
            }
            for (int i = 0; i < stepTypes && current+i<stepCount; i++) {
                stack.push(current+i+1);
            }
        }
        return possibleWays;
    }

    public int circularSortedSearch(int[] arr, int x){
        int low=0;
        int high = arr.length-1;
        while (low<=high){
            int middle = (low+high)/2;
            if (arr[middle] == x)return middle;
            else if (arr[middle]<= arr[high]){
                if (arr[middle]<x && x<=arr[high]){
                    low = middle+1;
                }else {
                    high = middle-1;
                }
            }else {
                if (arr[low]<=x && x<arr[middle]){
                    high = middle-1;
                }else {
                    low = middle+1;
                }
            }
        }
        return -1;
    }

    public void sortedMerge(int[] A, int[] B){
        int bLength = B.length;
        for (int i = bLength-1; i >=0 ; i--) {
            int aLength = A.length-1 -i;
            while (aLength>0 && B[i]<A[aLength-1]){
                A[aLength]=A[aLength-1];
                aLength--;
            }
            A[aLength] = B[i];
        }
    }

    public int binarySearchFirstLast(int[] nums, int target,boolean isFirst){
        int low = 0;
        int high = nums.length-1;
        int result = -1;

        while (low<=high){
            int middle = low + (high-low)/2;
            if (nums[middle] == target) {
                result = middle;
                if(isFirst) high = middle-1;
                else low = middle+1;
            }
            else if(nums[middle]<target) low = middle+1;
            else high = middle-1;
        }
        return result;
    }

    public int[] binarySearchFirstLast(int[] nums, int target){
        int low = 0;
        int high = nums.length-1;
        int[] result = new int[]{-1,-1};

        while (low<=high){
            int middle = low + (high-low)/2;
            if (nums[middle] == target) {
                result[0] = middle;
                result[1] = middle;
                while (result[0]-1 >=0 && nums[result[0]-1] == target) result[0]--;
                while (result[1]+1 <nums.length && nums[result[1]+1] == target) result[1]++;
                return result;
            }
            else if(nums[middle]<target) low = middle+1;
            else high = middle-1;
        }
        return result;
    }

    public int binarySearchR(int[] arr, int x){
        return binarySearchR(arr,x,0,arr.length-1);
    }

    public int binarySearchR(int[] arr, int x,int low, int high){
        if (low>high) return -1;
        int mid = (low+high)/2;
        if (x== arr[mid])return mid;
        else if (x<arr[mid]) return binarySearchR(arr,x,low,mid-1);
        else return binarySearchR(arr,x,mid+1,high);
    }

    public int binarySearch(int[] arr, int x){
//        int n = arr.length;
        int low = 0;
        int high = arr.length-1;
        while (low<=high){
            int middle = (low+high)/2;
            if (arr[middle] == x) return middle;
            else if (x < arr[middle]) high = middle-1;
            else low = middle+1;
        }
        return -1;
    }

    public void quickSort(int[] arr){
        quickSort(arr,0, arr.length-1);
    }

    public void quickSort(int[] arr, int start, int end){
        if (start>=end) return;
        int pivotIndex = partition(arr,start, end);
        quickSort(arr,start,pivotIndex-1);
        quickSort(arr,pivotIndex+1,end);
    }

    private int partition(int[] arr, int start, int end){
        int pivot = choosePivot(start, end);
        int pivotIndex = start;
        for (int i = start; i < end; i++) {
            if (arr[i]<=arr[pivot])
                swap(arr,i,pivotIndex++);
        }
        swap(arr,pivot,pivotIndex);

        return pivotIndex;
    }

    private int choosePivot(int start, int end){
        return end;
    }

    private void swap(int[] arr,int i1,int i2){
        int temp = arr[i1];
        arr[i1] =  arr[i2];
        arr[i2] =  temp;
    }

    public void mergeSort(int[] arr){
        int n = arr.length;
        if (n==1) return;
        int mid = n/2;
        int[] left = new int[mid];
        int[] right = new int[n-mid];

        for (int i = 0; i < mid; i++)
            left[i] = arr[i];
        for (int i = 0; i < n-mid; i++)
            right[i] = arr[i+mid];
        mergeSort(left);
        mergeSort(right);
        mergeArrays(left,right,arr);
    }

    private void mergeArrays(int[] left,int[] right, int[] arr){
        int l=0,r=0,i=0,ln = left.length,rn=right.length;
        while (l<ln && r<rn)
            arr[i++]= left[l]<right[r] ? left[l++] :right[r++];

        while (l<ln)
            arr[i++]=left[l++];
        while (r<rn)
            arr[i++]=right[r++];
    }
}
