#include <iostream>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<malloc.h>

using namespace std;
//快速排序

void quickSort(int *, int, int);
int getIndex(int *, int, int);
//=============================================================
int main(void)
{
    int a[6] = {2,1,0,5,4,3};

    //第二个参数表示第一个元素的下标，第三个元素表示最后 元素的下标
    quickSort(a, 0, 5);

    for (int i = 0; i < 6; i++)
        cout<<a[i];


    return 0;
}

//=============================================================
void quickSort(int *a, int low, int high) {
    int index;

    if (low < high) {
        //寻找基准数的正确索引
        index = getIndex(a, low, high);

        //通过迭代对 index 之前和之后的 数组进行相同的操作
        quickSort(a, low, index - 1);
        quickSort(a, index + 1, high);
    }
}

int getIndex(int *a, int low, int high) {
    //基准数据
    int temp = a[low];
    while (low < high) {
        //当队尾的元素大于等于基准数据时，向前挪动 high 指针
        while (low < high && a[high] >= temp) {
            high--;
        }
        //如果队尾元素小于 temp 了，需要将其赋值给 low
        a[low] = a[high];

        //当队首元素小于等于 temp 时，向前挪动 low 指针
        while (low < high && a[low] <= temp) {
            low++;
        }
        //如果队首元素大于 temp 了，需要将其赋值给 high
        a[high] = a[low];
    }

    //跳出循环时，high 和 low 相等，此时 low 和 high 的 值就是 temp 的索引位置
    a[low] = temp;
    return low;

}
