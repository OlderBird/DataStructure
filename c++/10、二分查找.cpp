#include <iostream>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<malloc.h>

using namespace std;
//二分查找

int binary_search(int [], int, int);
//=============================================================
int main(void)
{
    int arr[7] = {100,101,102,103,104,105,106};

    cout<<binary_search(arr, 103, 7)<<endl;
    return 0;
}

//=============================================================
int binary_search(int arr[], int value, int n) {
    int mid = -1;
    int min = 0;
    int max = n;

    while (min < max) {
        mid = (max + min)/2;    //找到中间位置

        if (value < arr[mid])
            max = mid - 1;      //确定左子表范围
        else if (value > arr[mid])
            min = mid + 1;      //确定右子表范围
        else if (value = arr[mid])
            break;
    }
    return mid+1;
}
