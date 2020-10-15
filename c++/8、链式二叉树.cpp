#include <iostream>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<malloc.h>

using namespace std;
//��ʽ������

typedef struct Tree
{
    char data;
    struct Tree *LChild;    //����
    struct Tree *RChild;    //�Һ���
}TREE,*PTREE;


PTREE createTree(void);     //����������
void preTraverse(PTREE);    //ǰ�����
void inTraverse(PTREE);     //�������
void postTraverse(PTREE);   //�������

//=============================================================
int main(void)
{
    PTREE t = createTree();

    preTraverse(t);
    cout<<endl;
    inTraverse(t);
    cout<<endl;
    postTraverse(t);

    return 0;

    return 0;
}

//=============================================================

//����ֵ�Ǹ��ڵ�ĵ�ַ
PTREE createTree()
{
    PTREE A = (PTREE)malloc(sizeof(TREE)); A->data = 'A';
    PTREE B = (PTREE)malloc(sizeof(TREE)); B->data = 'B';
    PTREE C = (PTREE)malloc(sizeof(TREE)); C->data = 'C';
    PTREE D = (PTREE)malloc(sizeof(TREE)); D->data = 'D';
    PTREE E = (PTREE)malloc(sizeof(TREE)); E->data = 'E';

    A->LChild = B;
    A->RChild = C;
    B->LChild = B->RChild = NULL;
    C->LChild = D;
    C->RChild = NULL;
    D->LChild = NULL;
    D->RChild = E;
    E->LChild = E->RChild = NULL;

    return A;
}

void preTraverse(PTREE p) {
    if (p != NULL) {
        //p->LChild���Դ�������������
        //�ȷ��ʸ��ڵ�
        cout<<p->data;
        //���������������
        preTraverse(p->LChild);
        //���������������
        preTraverse(p->RChild);
    }
}

void inTraverse(PTREE p) {
    if (p != NULL) {
        //���������������
        preTraverse(p->LChild);
        //�ٷ��ʸ��ڵ�
        cout<<p->data;
        //���������������
        preTraverse(p->RChild);
    }
}

void postTraverse(PTREE p) {
    if (p != NULL) {
        //�Ⱥ������������
        preTraverse(p->LChild);
        //�ٺ������������
        preTraverse(p->RChild);
        //�ٷ��ʸ��ڵ�
        cout<<p->data;

    }
}


