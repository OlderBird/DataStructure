#include <iostream>
#include<stdio.h>
#include<string.h>

using namespace std;
//�ṹ���ʹ��

struct Student
{
    int sid;
    char name[200];
    int age;
};  //�ֺŲ���ʡ

void f(struct Student * pst);
void show(struct Student * pst);
int main(void)
{
    //1������:struct Student st = {1,"aq",18};
    struct Student st;
    //��ֵ
    st.sid = 1;
    //st.name = "aq";   //���������󣬲���ֱ�Ӹ�ֵ
    strcpy(st.name,"aq");
    st.age = 18;
    cout<<st.name<<endl;
//=====================
    //2���ڶ��ַ�ʽ������ָ����������Student�ı�����ַ
    struct Student *pst;
    pst = &st;
    pst->sid = 99;  //�ȼ��� (*pst).sid �ȼ��� st.sid
//=====================
    //3��ͨ��������ֵ
    struct Student st_p;
    f(&st_p);
    cout<<st_p.name<<endl;
    //4��ͨ���������
    show(&st_p);

    return 0;
}

//3��ͨ��������ֵ
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

