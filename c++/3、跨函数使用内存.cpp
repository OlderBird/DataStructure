#include <iostream>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>

using namespace std;
//�纯��ʹ���ڴ�

struct Student
{
    int age;
};

struct Student * CreateStudent(void);
void ShowStudent(struct Student *pst);

int main(void)
{
    struct Student *st;
    //����
    st = CreateStudent();
    //���
    ShowStudent(st);

    return 0;
}

//�����Ǵ�����һ������Ϊһ�� struct student ���ڴ�ռ�
//�� main ���棬��� st ���ܵ���������ص��׵�ַ
struct Student * CreateStudent(void)
{
    //ͨ��malloc��ֵ���ڴ棬���������겻����ʧ
    struct Student *p = (struct Student *)malloc(sizeof(struct Student));
    p -> age = 18;
    return p;
}
//�� showstudent �����ڣ�ֻ��һ�����ã��������ռ��ڵ� sid �� age ��Ա��ֵ
void ShowStudent(struct Student *pst)
{
    cout<<pst->age<<endl;
}
