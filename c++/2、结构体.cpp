#include <iostream>
#include<stdio.h>
#include<string.h>

using namespace std;
//结构体的使用

struct Student
{
    int sid;
    char name[200];
    int age;
};  //分号不能省

void f(struct Student * pst);
void show(struct Student * pst);
int main(void)
{
    //1、定义:struct Student st = {1,"aq",18};
    struct Student st;
    //赋值
    st.sid = 1;
    //st.name = "aq";   //此条语句错误，不能直接赋值
    strcpy(st.name,"aq");
    st.age = 18;
    cout<<st.name<<endl;
//=====================
    //2、第二种方式，定义指针变量，存放Student的变量地址
    struct Student *pst;
    pst = &st;
    pst->sid = 99;  //等价于 (*pst).sid 等价于 st.sid
//=====================
    //3、通过函数赋值
    struct Student st_p;
    f(&st_p);
    cout<<st_p.name<<endl;
    //4、通过函数输出
    show(&st_p);

    return 0;
}

//3、通过函数赋值
void f(struct Student * pst)
{
    (*pst).sid = 99;
    strcpy(pst -> name, "zhangsan");
    pst -> age = 22;
}
void show(struct Student * pst)
{
    cout<<pst->age<<endl;
}

