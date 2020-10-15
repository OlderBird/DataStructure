#include <iostream>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<malloc.h>

using namespace std;
//栈的基本算法和使用

typedef struct Node
{
    int data;
    struct Node *next;
}NODE,*PNODE;

//栈的实现
typedef struct Stack
{
    PNODE top;      //栈顶
    PNODE bottom;   //栈底
}STACK,*PSTACK;

//初始化：将pTop和pBottom都指向一个无用的头节点，为空
void init(PSTACK);
//通过push操作，一个push造出一个节点，pTop指向这个节点
bool push(PSTACK, int);     //压栈
bool pop(PSTACK, int *);    //出栈
bool clear(PSTACK);
void show(PSTACK);          //遍历


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
        cout<<"动态内存分配失败"<<endl;
        exit(-1);
    }
    else {
        //top 和 bottom 都指向了同一个节点
        p->bottom = p->top;
        p->top->next = NULL;    //p->bottom->next = NULL;
    }
}

bool push(PSTACK p, int value) {
    PNODE pNew = (PNODE)malloc(sizeof(NODE));
    pNew->data = value;

    //新的节点指针域要指向下个节点
    //相当于新的节点放在了最上面的节点的上方
    pNew->next = p->top;
    //top 就是新的节点了
    p->top = pNew;
}

//出栈并保存出栈元素；
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


