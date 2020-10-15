#include <iostream>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<malloc.h>

using namespace std;
//连续存储数组的算法

//定义了一个数据类型，该数据类型的名字叫做 struct Arr，且含有三个成员
typedef struct Arr
{
    int *pBase;    //存储的是数组第一个元素的地址
    int len;        //数组所能容纳的最大元素的个数
    int cnt;       //当前数组有效元素的个数
}A; //若是 *A，A等价于 struct Student *，使用：A a = &st

void init(Arr *, int);        //初始化，不是对外界提供的
bool append(Arr *, int);      //追加
bool insert(Arr *, int, int);      //插入
bool remove(Arr *, int, int *);      //删除
int get();          //获取
bool is_empty(Arr *);    //判断为空
bool is_full(Arr *);     //判断满
void sort();        //排序
void show(Arr *);        //打印
void inversion(Arr *);   //倒置
//=============================================================

int main(void)
{
    //用数据类型 struct Arr 定义了一个变量叫 arr
    struct Arr arr; //等价于 A arr
    int val;

    init(&arr, 6);
    show(&arr);
    append(&arr,1);
    append(&arr,2);
    append(&arr,3);
    append(&arr,4);
    append(&arr,5);
    show(&arr);
    inversion(&arr);
    show(&arr);

    return 0;
}

//=============================================================
void init(struct Arr *pArr, int length) {
    //(*pArr).len = 99;

    //pArr 指针变量指向的是结构体中的变量pBase
    pArr->pBase = (int *)malloc(sizeof(int) *length);
    //地址分配失败
    if (pArr->pBase == NULL) {
        cout<<"动态内存分配失败！\n"<<endl;
        exit(-1);   //终止整个程序
    }
    else {
        pArr->len = length;
        pArr->cnt = 0;
    }
    return;
}

bool append(struct Arr *pArr, int val) {
    //数组满时返回false
    if( is_full(pArr) )
        return false;
    //数组不满时追加
    pArr->pBase[pArr->cnt] = val;
    (pArr->cnt)++;
}

bool insert(struct Arr *pArr, int index, int val) {
    //判断是否为满，或是否越界
    if ( is_full(pArr) || index < 1 || index > pArr->len)
        return false;

    (pArr->cnt)++;
    for (int i = (pArr->cnt)-1 ; i >= index-1 ; i--) {
        pArr->pBase[i] = pArr->pBase[i-1];
    }
    pArr->pBase[index-1] = val;

    return true;

}

bool remove(struct Arr *pArr, int index, int *pVal) {
    if ( is_empty(pArr) || index < 1 || index > pArr->len)
        return false;

    //通过元素值找到元素位置
    *pVal = pArr->pBase[index-1];
    for (int i = index-1 ; i <= (pArr->cnt)-1 ; i++) {
        pArr->pBase[i] = pArr->pBase[i+1];
    }
    (pArr->cnt)--;
    return true;
}

bool is_empty(struct Arr *pArr) {
    if (pArr->cnt == 0)
        return true;
    else
        return false;
}

bool is_full(struct Arr *pArr) {
    if (pArr->cnt == pArr->len)
        return true;
    else
        return false;
}

void show(struct Arr *pArr) {
    //pArr：结构体变量地址，所以不用加取地址符&
    if ( is_empty(pArr) )
        cout<<"数组为空！\n"<<endl;
    else {
        for (int i = 0; i < pArr->cnt; i++)
            cout<<pArr->pBase[i]<<endl;
        cout<<"\n"<<endl;
    }
}

void inversion(struct Arr *pArr) {
    if ( is_empty(pArr) )
        cout<<"数组为空！\n"<<endl;
    int i = 0;
    int j = pArr->cnt - 1;
    int temp = 0;
        while ( i < j){
            temp = pArr->pBase[i];
            pArr->pBase[i] = pArr->pBase[j];
            pArr->pBase[j] = temp;
            i++;
            j--;
        }
}
