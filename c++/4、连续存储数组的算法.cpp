#include <iostream>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<malloc.h>

using namespace std;
//�����洢������㷨

//������һ���������ͣ����������͵����ֽ��� struct Arr���Һ���������Ա
typedef struct Arr
{
    int *pBase;    //�洢���������һ��Ԫ�صĵ�ַ
    int len;        //�����������ɵ����Ԫ�صĸ���
    int cnt;       //��ǰ������ЧԪ�صĸ���
}A; //���� *A��A�ȼ��� struct Student *��ʹ�ã�A a = &st

void init(Arr *, int);        //��ʼ�������Ƕ�����ṩ��
bool append(Arr *, int);      //׷��
bool insert(Arr *, int, int);      //����
bool remove(Arr *, int, int *);      //ɾ��
int get();          //��ȡ
bool is_empty(Arr *);    //�ж�Ϊ��
bool is_full(Arr *);     //�ж���
void sort();        //����
void show(Arr *);        //��ӡ
void inversion(Arr *);   //����
//=============================================================

int main(void)
{
    //���������� struct Arr ������һ�������� arr
    struct Arr arr; //�ȼ��� A arr
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

    //pArr ָ�����ָ����ǽṹ���еı���pBase
    pArr->pBase = (int *)malloc(sizeof(int) *length);
    //��ַ����ʧ��
    if (pArr->pBase == NULL) {
        cout<<"��̬�ڴ����ʧ�ܣ�\n"<<endl;
        exit(-1);   //��ֹ��������
    }
    else {
        pArr->len = length;
        pArr->cnt = 0;
    }
    return;
}

bool append(struct Arr *pArr, int val) {
    //������ʱ����false
    if( is_full(pArr) )
        return false;
    //���鲻��ʱ׷��
    pArr->pBase[pArr->cnt] = val;
    (pArr->cnt)++;
}

bool insert(struct Arr *pArr, int index, int val) {
    //�ж��Ƿ�Ϊ�������Ƿ�Խ��
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

    //ͨ��Ԫ��ֵ�ҵ�Ԫ��λ��
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
    //pArr���ṹ�������ַ�����Բ��ü�ȡ��ַ��&
    if ( is_empty(pArr) )
        cout<<"����Ϊ�գ�\n"<<endl;
    else {
        for (int i = 0; i < pArr->cnt; i++)
            cout<<pArr->pBase[i]<<endl;
        cout<<"\n"<<endl;
    }
}

void inversion(struct Arr *pArr) {
    if ( is_empty(pArr) )
        cout<<"����Ϊ�գ�\n"<<endl;
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
