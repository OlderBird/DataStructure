#include <iostream>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>

using namespace std;
//跨函数使用内存

struct Student
{
    int age;
};

struct Student * CreateStudent(void);
void ShowStudent(struct Student *pst);

int main(void)
{
    struct Student *st;
    //创建
    st = CreateStudent();
    //输出
    ShowStudent(st);

    return 0;
}

//作用是创建课一个长度为一个 struct student 的内存空间
//在 main 里面，这个 st 接受到了这个返回的首地址
struct Student * CreateStudent(void)
{
    //通过malloc赋值的内存，函数调用完不会消失
    struct Student *p = (struct Student *)malloc(sizeof(struct Student));
    p -> age = 18;
    return p;
}
//在 showstudent 函数内，只有一个作用，输出这个空间内的 sid 和 age 成员的值
void ShowStudent(struct Student *pst)
{
    cout<<pst->age<<endl;
}
