#include <iostream>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<malloc.h>

using namespace std;
//ջ�Ļ����㷨��ʹ��

typedef struct Node
{
    int data;
    struct Node *next;
}NODE,*PNODE;

//ջ��ʵ��
typedef struct Stack
{
    PNODE top;      //ջ��
    PNODE bottom;   //ջ��
}STACK,*PSTACK;

//��ʼ������pTop��pBottom��ָ��һ�����õ�ͷ�ڵ㣬Ϊ��
void init(PSTACK);
//ͨ��push������һ��push���һ���ڵ㣬pTopָ������ڵ�
bool push(PSTACK, int);     //ѹջ
bool pop(PSTACK, int *);    //��ջ
bool clear(PSTACK);
void show(PSTACK);          //����


//=============================================================

int main(void)
{
    int value;

    STACK s;

    init(&s);
    push(&s,1);
    push(&s,2);
    push(&s,3);
    pop(&s, &value);
    show(&s);
    clear(&s);
    show(&s);

    return 0;
}

//=============================================================
void init(PSTACK p) {
    p->top = (PNODE)malloc(sizeof(NODE));
    if ( p->top == NULL) {
        cout<<"��̬�ڴ����ʧ��"<<endl;
        exit(-1);
    }
    else {
        //top �� bottom ��ָ����ͬһ���ڵ�
        p->bottom = p->top;
        p->top->next = NULL;    //p->bottom->next = NULL;
    }
}

bool push(PSTACK p, int value) {
    PNODE pNew = (PNODE)malloc(sizeof(NODE));
    pNew->data = value;

    //�µĽڵ�ָ����Ҫָ���¸��ڵ�
    //�൱���µĽڵ������������Ľڵ���Ϸ�
    pNew->next = p->top;
    //top �����µĽڵ���
    p->top = pNew;
}

//��ջ�������ջԪ�أ�
bool pop(PSTACK pS, int *value) {
    PNODE p = pS->top;
    *value = p->data;
    pS->top = p->next;
    free(p);
}

bool clear(PSTACK pS) {
    PNODE p = pS->top;
    PNODE q = NULL;

    while(p != pS->bottom) {
        q = p->next;
        free(p);
        p = q;
    }
    pS->top = pS->bottom;
}

void show(PSTACK pS) {
    PNODE p = pS->top;

    while (p != pS->bottom) {
        cout<<p->data<<endl;
        p = p->next;
    }
}


