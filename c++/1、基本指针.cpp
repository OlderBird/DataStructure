#include <iostream>

using namespace std;
//基本指针的使用

void f(int **);
int main(void)
{
    int *p; //p是个指针变量，int *表示该p变量只能存储int类型变量的地址
    int i = 10;
    int j;
   //j = *p;
    //cout << j << endl;   //运行错误


    p = &i; //把 i 的地址发送给 p，p保存了i的地址，p就指向了i
    j = *p; //等价于 j = i
    //*P = i; //等价于 i = i
    //p = 10;  //error：10是整数不是地址0.

    cout<<"\n=====================\n"<<endl;
    cout<<"如何通过函数修改实参的值\n"<<endl;

    int k = 9;
    int *kl = &k;

    cout<<kl<<endl;
    f(&kl);
    cout<<kl<<endl;


    return 0;
}

//如何通过函数修改实参的值（不安全）
void f(int **q) {
    *q = (int *)0xFFFFFFF;
}

